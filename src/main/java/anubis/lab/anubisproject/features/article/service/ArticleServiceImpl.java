package anubis.lab.anubisproject.features.article.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.tag.service.TagService;
import anubis.lab.anubisproject.features.utilisateur.service.UtilisateurResolver;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.mapper.ArticleMapper;
import anubis.lab.anubisproject.features.article.repository.ArticleRepository;
import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.category.service.CategoryService;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleResolver {

    private ArticleRepository articleRepository;
    private UtilisateurResolver utilisateurService;
    private CategoryService categoryService;
    private TagService tagService;
    private ArticleMapper mapper;

    @Override
    public ArticleDTO addArticle(Article article, List<Long> idUtilisateurs, List<Long> idCategories, List<Long> idTags) {
        Utilisateur utilisateur = new Utilisateur();
        for (int i = 0; i<idUtilisateurs.size(); i++){
            utilisateur = utilisateurService.getUtilisateur(idUtilisateurs.get(i));
        }

        System.out.println("ID USER "+utilisateur);

        idCategories.forEach(idCat ->{
            Category category = categoryService.getCategory(idCat);
        });
        idTags.forEach(id ->{
            Tag tag = tagService.getTag(id);
        });
        Article savedArticle = new Article();
        article.setPublished(true);

        savedArticle = articleRepository.save(article);

        return mapper.fromArticle(savedArticle);
    }

    @Override
    public Article getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("L'id %s cet article n'existe pas", id)));
        return article;
    }

    @Override
    public ArticleDTO updateArticle(Long idArticle, List<Long> idCategories, List<Long> idTags, Long idUtilisateur) {

         // category = new Category();
        Article article = getArticle(idArticle);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(idUtilisateur);
        idCategories.forEach(c ->{
            final Category category = categoryService.getCategory(c);
        });
        try {
            if (article != null) {
                if (!(idCategories.isEmpty())) {
//                    article.setCategory(category);
                }
                if (idUtilisateur != null) {
//                    article.setUtilisateur(utilisateur);
                }


            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }
        Article savedArticle = articleRepository.save(article);
        return mapper.fromArticle(savedArticle);

    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            throw new RuntimeException(String.format("Pas d'Article pour le moment"));
        }

        List<ArticleDTO> articleDTO = articles.stream().map(a -> mapper.fromArticle(a)).collect(Collectors.toList());

        return articleDTO;
    }

    @Override
    public ArticleDTO addUserOnArticle(Long idArticle, Long idUtilisateur) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(idUtilisateur);
        Article article = getArticle(idArticle);
        try {
            article.getUtilisateurs().add(utilisateur);
            articleRepository.save(article);
        }catch (Exception e){

        }
        return null;
    }


    @Override
    public Boolean deleteArticle(Long idArticle) {
        getArticle(idArticle);
        articleRepository.deleteById(idArticle);

        return true;
    }

}
