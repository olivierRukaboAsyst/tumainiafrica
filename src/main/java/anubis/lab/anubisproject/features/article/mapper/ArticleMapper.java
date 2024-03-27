package anubis.lab.anubisproject.features.article.mapper;

import anubis.lab.anubisproject.features.article.dto.ReactionDTO;
import anubis.lab.anubisproject.features.article.dto.ResponseArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Reaction;
import anubis.lab.anubisproject.features.article.repository.ArticleRepository;
import anubis.lab.anubisproject.features.article.repository.ReactionRepository;
import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.mapper.CategoryMapper;
import anubis.lab.anubisproject.features.category.repository.CategoryRepository;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.comment.mapper.CommentMapper;
import anubis.lab.anubisproject.features.comment.repository.CommentRepository;
import anubis.lab.anubisproject.features.tag.Repository.TagRepository;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.mapper.TagMapper;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import anubis.lab.anubisproject.features.utilisateur.mapper.UtilisateurMapper;
import anubis.lab.anubisproject.features.utilisateur.repository.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import lombok.AllArgsConstructor;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleMapper {

    final private UtilisateurRepository utilisateurRepository;
    final private CategoryRepository categoryRepository;
    final private TagRepository tagRepository;
    final private CommentRepository commentRepository;
    final private UtilisateurMapper userMapper;
    final private CategoryMapper catMapper;
    final private TagMapper tagMapper;
    final private CommentMapper commentMapper;
    final private ReactionRepository reactionRepository;
    final private ReactionMapper reactMapper;


    public ArticleMapper(UtilisateurRepository utilisateurRepository, CategoryRepository categoryRepository, TagRepository tagRepository,
                         CommentRepository commentRepository, UtilisateurMapper userMapper, CategoryMapper catMapper, TagMapper tagMapper,
                         CommentMapper commentMapper, ReactionRepository reactionRepository, ReactionMapper reactMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
        this.userMapper = userMapper;
        this.catMapper = catMapper;
        this.tagMapper = tagMapper;
        this.commentMapper = commentMapper;
        this.reactionRepository = reactionRepository;
        this.reactMapper = reactMapper;
    }

    public ArticleDTO fromArticle(Article article, List<Category> categories, List<Tag> tagList){
        ArticleDTO articleDTO = new ArticleDTO();
        Set<Tag> tagSet = new HashSet<>(tagList);
        article.setTags(tagSet);
//        article.setUtilisateurs(utilisateurs);
        Set<Category> categorySet = new HashSet<>(categories);
        article.setCategories(categorySet);
        BeanUtils.copyProperties(article, articleDTO);

        return articleDTO;
    }

    public ArticleDTO fromArticleReduced(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);

        return articleDTO;
    }

    public ResponseArticleDTO fromArticleRequest(Article article){
        ResponseArticleDTO articleDTO = new ResponseArticleDTO();
        List<Long> idCategories = new ArrayList<>();
        List<Long> idUsers = new ArrayList<>();
        for (Utilisateur utilisateur: article.getUtilisateurs()){
            Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateur.getId());
            optionalUtilisateur.ifPresent(user -> idUsers.add(user.getId()));
        }
        for (Category category: article.getCategories()){
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            optionalCategory.ifPresent(cat -> idCategories.add(cat.getId()));
        }
        List<Utilisateur> utilisateurList = utilisateurRepository.findAllById(idUsers);
        articleDTO.setUtilisateurs(utilisateurList.stream().map(userMapper::fromUtilisateur).toList());
        List<Category> categoryList = categoryRepository.findAllById(idCategories);
        Set<Category> categorySet = new HashSet<>(categoryList);
        articleDTO.setCategories(new HashSet<>(categorySet.stream().map(catMapper::fromCategory).toList()));

        List<Long> idTags = new ArrayList<>();
        for (Tag tag: article.getTags()){
            Optional<Tag> optionalTag = tagRepository.findById(tag.getIdTag());
            optionalTag.ifPresent(t -> idTags.add(t.getIdTag()));
        }
        List<Tag> tagList = tagRepository.findAllById(idTags);
        articleDTO.setTags(tagList.stream().map(tagMapper::fromTag).toList());


        List<Long> idComments = new ArrayList<>();
        for(Comment comment: article.getComments()){
            Optional<Comment> optionalComment = commentRepository.findById(comment.getId());
            optionalComment.ifPresent(c-> idComments.add(c.getId()));
        }
        List<Comment> commentList = commentRepository.findAllById(idComments);
        articleDTO.setComments(commentList.stream().map(commentMapper::fromCommentDTO).toList());
        articleDTO.setCommentNumber(commentList.size());

        List<Reaction> reactions = reactionRepository.findAllByArticleId(article.getId());
        articleDTO.setReactionsNumber(reactions.size());
        articleDTO.setNumberOflike(reactionRepository.getTotalLikes(article.getId()));
        articleDTO.setNumberOfLaugh(reactionRepository.getTotalLaughs(article.getId()));
        articleDTO.setNumberOfDislike(reactionRepository.getTotalDisLikes(article.getId()));
        StringTokenizer tokenizer = new StringTokenizer(article.getContent());
        int n = tokenizer.countTokens();
        double readingTime = (double) n / 200;
        DecimalFormat df = new DecimalFormat("#.##");
        String formatedTime = df.format(readingTime);
        articleDTO.setReadingTime(formatedTime);
        BeanUtils.copyProperties(article, articleDTO);

        return articleDTO;
    }

    public ResponseArticleDTO fromArticleDTOResponse(Article article){
        ResponseArticleDTO responseArticleDTO = new ResponseArticleDTO();
        responseArticleDTO.setId(article.getId());
        responseArticleDTO.setTitle(article.getTitle());
        responseArticleDTO.setContent(article.getContent());
        responseArticleDTO.setFrontPage(article.isFrontPage());
        responseArticleDTO.setPublished(article.isPublished());
        responseArticleDTO.setViews(article.getViews());
        responseArticleDTO.setCreatedAt(article.getCreatedAt());
        responseArticleDTO.setUpdatedAt(article.getUpdatedAt());
        responseArticleDTO.setUtilisateurs(userMapper.fromUtilisateurList(article.getUtilisateurs()));
        responseArticleDTO.setCategories(catMapper.fromCategorySet(article.getCategories()));
        responseArticleDTO.setTags(tagMapper.fromTagList(article.getTags().stream().toList()));


/*
        List<Long> idCategories = new ArrayList<>();
        List<Long> idUsers = new ArrayList<>();
        for (Utilisateur utilisateur: article.getUtilisateurs()){
            Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateur.getId());
            optionalUtilisateur.ifPresent(user -> idUsers.add(user.getId()));
        }
        for (Category category: article.getCategories()){
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            optionalCategory.ifPresent(cat -> idCategories.add(cat.getId()));
        }
        List<Utilisateur> utilisateurList = utilisateurRepository.findAllById(idUsers);
        article.setUtilisateurs(utilisateurList.stream().toList());
        List<Category> categoryList = categoryRepository.findAllById(idCategories);
        Set<Category> categorySet = new HashSet<>(categoryList);
        article.setCategories(new HashSet<>(categorySet.stream().toList()));

        List<Long> idTags = new ArrayList<>();
        for (Tag tag: article.getTags()){
            Optional<Tag> optionalTag = tagRepository.findById(tag.getIdTag());
            optionalTag.ifPresent(t -> idTags.add(t.getIdTag()));
        }
        List<Tag> tagList = tagRepository.findAllById(idTags);
        article.setTags(new HashSet<>(tagList.stream().toList()));

        BeanUtils.copyProperties(responseArticleDTO, article);
*/
        return responseArticleDTO;
    }

    public List<ArticleDTO> fromArticleReducedList(List<Article> articles){
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        BeanUtils.copyProperties(articles, articleDTOS);

        return articleDTOS;
    }
    
    public Article fromArticleDTO(ArticleDTO articleDTO){
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);

        return article;
    }

}
