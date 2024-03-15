package anubis.lab.anubisproject.features.article.service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import anubis.lab.anubisproject.exceptions.BadRequestExeption;
import anubis.lab.anubisproject.exceptions.NotFoundException;
import anubis.lab.anubisproject.features.article.dto.ResponseArticleDTO;
import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.mapper.CategoryMapper;
import anubis.lab.anubisproject.features.category.repository.CategoryRepository;
import anubis.lab.anubisproject.features.tag.Repository.TagRepository;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.mapper.TagMapper;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.mapper.UtilisateurMapper;
import anubis.lab.anubisproject.features.utilisateur.repository.UtilisateurRepository;
import anubis.lab.anubisproject.features.utilisateur.service.UtilisateurResolver;
import anubis.lab.anubisproject.helpers.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.mapper.ArticleMapper;
import anubis.lab.anubisproject.features.article.repository.ArticleRepository;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleResolver {

    private final ArticleRepository articleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurResolver utilisateurService;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ArticleMapper mapper;
    private final CategoryMapper catMapper;
    private final UtilisateurMapper userMapper;
    private final TagMapper tagMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UtilisateurRepository utilisateurRepository, UtilisateurResolver utilisateurService,
                              CategoryRepository categoryRepository, TagRepository tagRepository, ArticleMapper mapper,
                              CategoryMapper catMapper, UtilisateurMapper userMapper, TagMapper tagMapper) {
        this.articleRepository = articleRepository;
        this.utilisateurService = utilisateurService;
        this.utilisateurRepository = utilisateurRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;
        this.catMapper = catMapper;
        this.userMapper = userMapper;
        this.tagMapper = tagMapper;
    }

    @Override
    public ResponseArticleDTO addArticle(Article article, Long idUtilisateur, List<Long> idCategories, List<Long> idTags) throws BadRequestExeption {

        UtilisateurDTO utilisateur = utilisateurService.getUtilisateur(idUtilisateur);
        List<Category> categories = categoryRepository.findAllById(idCategories);
        List<Tag> tagList = tagRepository.findAllById(idTags);

        Set<Category> categorySet = new HashSet<>(categories);
        Set<Tag> tagSet = new HashSet<>(tagList);

        if (idUtilisateur == null || categories.isEmpty() || tagList.isEmpty()){
            throw new BadRequestExeption("Verifier si la categorie, tag ou l'Utilisateur sont présents");
        }
        article.setPublished(true);
        log.info("SIZE TAG "+tagList.size());
        List<Utilisateur> requestUsers = new ArrayList<>();
        requestUsers.add(userMapper.fromUtilisateurDTO(utilisateur));
        article.setUtilisateurs(requestUsers);
        article.setCategories(categorySet);
        article.setTags(tagSet);
        article.setCreatedAt(new Constant().dateFormated());
        Article savedArticle = articleRepository.save(article);

        CompletableFuture.delayedExecutor(2, TimeUnit.MINUTES).execute(()->{
            ArticleDTO oldArticle = getArticle(savedArticle.getId());
            oldArticle.setFrontPage(false);
            articleRepository.save(mapper.fromArticleDTO(oldArticle));
        });
        return mapper.fromArticleResponse(savedArticle);
    }

    @Override
    public ArticleDTO getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("L'id %s cet article n'existe pas", id)));
        return mapper.fromArticleReduced(article);
    }

    @Override
    public ResponseArticleDTO getArticleById(Long idArticle) {
        Article article = articleRepository.findById(idArticle).map(a ->{
                    a.setViews(a.getViews() +1);
                    return articleRepository.save(a);
                })
                .orElseThrow(() -> new RuntimeException(String.format("L'id %s cet article n'existe pas", idArticle)));
        return mapper.fromArticleResponse(article);
    }

    @Override
    public List<ArticleDTO> getTopArticles() {
        return mapper.fromArticleReducedList(articleRepository.findAll(Sort.by(Sort.Direction.DESC, "views")));
    }

    @Override
    public ArticleDTO updateArticle(Article requestArticle, Long idArticle, List<Long> idCategories, List<Long> idTags, List<Long> idUtilisateurs) throws NotFoundException {
        ArticleDTO article = getArticle(idArticle);
        List<Utilisateur> utilisateurs = utilisateurRepository.findAllById(idUtilisateurs);
        List<CategoryDTO> categories = catMapper.fromCategoryList(categoryRepository.findAllById(idCategories));
        Set<CategoryDTO> categorySet = new HashSet<>(categories);
        List<TagDTO> tagList = tagMapper.fromTagList(tagRepository.findAllById(idTags));
        try {
            if (Objects.nonNull(requestArticle)){
                if(!(idCategories.isEmpty())){
                    article.setCategories(categorySet);
                }
                if (!(idTags).isEmpty()){
                    article.setTags(tagList);
                }
                if (!(idUtilisateurs).isEmpty()){
                    article.setUtilisateurs(userMapper.fromUtilisateurList(utilisateurs));
                }
                if (Objects.nonNull(requestArticle.getTitle())){
                    article.setTitle(requestArticle.getTitle());
                }
                if (Objects.nonNull(requestArticle.getContent())){
                    article.setContent(requestArticle.getContent());
                }
            }
            article.setUpdatedAt(new Constant().dateFormated());
            Article article1;
            article1 = mapper.fromArticleDTO(article);
            Article savedArticle = articleRepository.save(article1);
            return mapper.fromArticle(savedArticle, catMapper.fromCategoryDTOList(categories), tagMapper.fromTagDTOS(tagList), utilisateurs);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            throw new RuntimeException(String.format("Pas d'Article pour le moment"));
        }

        List<ArticleDTO> articleDTO = articles.stream().map(mapper::fromArticleReduced).collect(Collectors.toList());

        return articleDTO;
    }
    @Override
    public ArticleDTO addUserOnArticle(Long idArticle, Long idUtilisateur) throws BadRequestExeption {
        UtilisateurDTO utilisateur = utilisateurService.getUtilisateur(idUtilisateur);
        ArticleDTO article = getArticle(idArticle);
        try {
            article.getUtilisateurs().add(utilisateur);
            Article article1 = articleRepository.save(mapper.fromArticleDTO(article));
            return mapper.fromArticleReduced(article1);
        }catch (Exception e){
            throw new BadRequestExeption("Erreur lors de l'ajout de l'Utilisateur");
        }
    }
    @Override
    public ArticleDTO changeArticleStatus(Long idArticle, Boolean isPublished) {
        ArticleDTO article = getArticle(idArticle);
        article.setPublished(isPublished);
        article.setUpdatedAt(new Constant().dateFormated());
        Article savedArticle = articleRepository.save(mapper.fromArticleDTO(article));

        return mapper.fromArticleReduced(savedArticle);
    }


    @Override
    public Boolean deleteArticle(Long idArticle) {
        getArticle(idArticle);
        articleRepository.deleteById(idArticle);

        return true;
    }

}
