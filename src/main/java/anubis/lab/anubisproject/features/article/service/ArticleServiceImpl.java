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
import anubis.lab.anubisproject.features.category.service.CategoryService;
import anubis.lab.anubisproject.features.tag.Repository.TagRepository;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.mapper.TagMapper;
import anubis.lab.anubisproject.features.tag.service.TagService;
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
    private final CategoryService categoryService;
    private final TagService tagService;
    private final ArticleMapper mapper;
    private final CategoryMapper catMapper;
    private final UtilisateurMapper userMapper;
    private final TagMapper tagMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UtilisateurRepository utilisateurRepository, UtilisateurResolver utilisateurService,
                              CategoryRepository categoryRepository, TagRepository tagRepository, ArticleMapper mapper,
                              CategoryMapper catMapper, UtilisateurMapper userMapper, TagMapper tagMapper, CategoryService categoryService, TagService tagService) {
        this.articleRepository = articleRepository;
        this.utilisateurService = utilisateurService;
        this.utilisateurRepository = utilisateurRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;
        this.catMapper = catMapper;
        this.userMapper = userMapper;
        this.tagMapper = tagMapper;
        this.categoryService = categoryService;
        this.tagService = tagService;
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
        List<Utilisateur> requestUsers = new ArrayList<>();
        requestUsers.add(userMapper.fromUtilisateurDTO(utilisateur));
        article.setUtilisateurs(requestUsers);
        article.setCategories(categorySet);
        article.setTags(tagSet);
        article.setCreatedAt(new Constant().dateFormated());
        Article savedArticle = articleRepository.save(article);

        Article newArticle = articleRepository.findById(savedArticle.getId()).orElseThrow();
        CompletableFuture.delayedExecutor(1, TimeUnit.MINUTES).execute(()->{
            newArticle.setFrontPage(false);
            articleRepository.save(newArticle);
        });
        return mapper.fromArticleRequest(newArticle);
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
        return mapper.fromArticleRequest(article);
    }

    public ArticleDTO getArticleByIds(Long idArticle) {
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new RuntimeException(String.format("L'id %s cet article n'existe pas", idArticle)));
        return mapper.fromArticleReduced(article);
    }

    @Override
    public List<ArticleDTO> getTopArticles() {
        return mapper.fromArticleReducedList(articleRepository.findAll(Sort.by(Sort.Direction.DESC, "views")));
    }

    @Override
    public ResponseArticleDTO updateArticle(Article requestArticle, Long idArticle, List<Long> idCategories, List<Long> idTags) throws NotFoundException {
        Article article = articleRepository.findById(idArticle).orElseThrow();
        List<Category> categories = null;
        List<Tag> tagList = null;

        try {
            if (Objects.nonNull(requestArticle)) {
                if (Objects.nonNull(requestArticle.getTitle())) {
                    article.setTitle(requestArticle.getTitle());
                }
                if (Objects.nonNull(requestArticle.getContent())) {
                    article.setContent(requestArticle.getContent());
                }
            }
            if (idCategories == null || idCategories.isEmpty()){
                Set<Category> olCategoriesSet = article.getCategories();
                article.setCategories(olCategoriesSet);
            }else{
                categories = categoryRepository.findAllById(idCategories);
                Set<Category> categorySet = new HashSet<>(categories);
                article.setCategories(categorySet);
            }
            if (idTags == null || idTags.isEmpty()){
                Set<Tag> oldTags = article.getTags();
                article.setTags(oldTags);
            }else {
                tagList = tagRepository.findAllById(idTags);
                Set<Tag> tagSet = new HashSet<>(tagList);
                article.setTags(tagSet);
            }

            if (article.getUtilisateurs() == null || article.getUtilisateurs().isEmpty()){
                List<Utilisateur> utilisateur = article.getUtilisateurs();
                article.setUtilisateurs(utilisateur);
            }

            Article savedArticle = articleRepository.save(article);
            return mapper.fromArticleDTOResponse(savedArticle);
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

        return articles.stream().map(mapper::fromArticleReduced).collect(Collectors.toList());
    }
    @Override
    public ResponseArticleDTO addUserOnArticle(Long idArticle, List<Long> idUtilisateurs) throws BadRequestExeption {
        Article article = articleRepository.findById(idArticle).orElseThrow();
        List<Utilisateur> utilisateurs = utilisateurRepository.findAllById(idUtilisateurs);
        try {
            article.setUtilisateurs(utilisateurs);// getUtilisateurs().add();
            Article article1 = articleRepository.save(article);
            return mapper.fromArticleRequest(article1);
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
