package anubis.lab.anubisproject.features.article.mapper;

import anubis.lab.anubisproject.features.article.dto.ResponseArticleDTO;
import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.mapper.CategoryMapper;
import anubis.lab.anubisproject.features.category.repository.CategoryRepository;
import anubis.lab.anubisproject.features.tag.Repository.TagRepository;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.mapper.TagMapper;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleMapper {

    final private CategoryRepository categoryRepository;
    final private TagRepository tagRepository;
    final private CategoryMapper catMapper;
    final private TagMapper tagMapper;

    public ArticleMapper(CategoryRepository categoryRepository, TagRepository tagRepository,
                         CategoryMapper catMapper, TagMapper tagMapper) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.catMapper = catMapper;
        this.tagMapper = tagMapper;
    }

    public ArticleDTO fromArticle(Article article, List<Category> categories, List<Tag> tagList, List<Utilisateur> utilisateurs){
        ArticleDTO articleDTO = new ArticleDTO();
        Set<Tag> tagSet = new HashSet<>(tagList);
        article.setTags(tagSet);
        article.setUtilisateurs(utilisateurs);
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

    public ResponseArticleDTO fromArticleResponse(Article article){
        ResponseArticleDTO articleDTO = new ResponseArticleDTO();
        List<Long> idCategories = new ArrayList<>();
        for (Category category: article.getCategories()){
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            optionalCategory.ifPresent(cat -> idCategories.add(cat.getId()));
        }
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

        BeanUtils.copyProperties(article, articleDTO);

        return articleDTO;
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
