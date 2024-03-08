package anubis.lab.anubisproject.features.article.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.service.ArticleResolver;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private ArticleResolver articleService;

    public ArticleController(ArticleResolver articleService) {
        this.articleService = articleService;
    }

    @PostMapping()
    public ArticleDTO addArticle(@RequestBody Article article, @RequestParam List<Long> idUtilisateurs,
                                 @RequestParam List<Long> idCategories, @RequestParam List<Long> idTags) {
        try {
            return articleService.addArticle(article, idUtilisateurs, idCategories, idTags);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Echec d'Enregistrement, réessayer"));
        }
    }

    @PutMapping()
    public ArticleDTO updateArticle(@PathVariable Long idArticle, @RequestParam List<Long> idCategories, @RequestParam List<Long> tags, @PathVariable Long idUtilisateurs) {
        return articleService.updateArticle(idArticle, idCategories, tags, idUtilisateurs);
    }

    @GetMapping("/{idArticle}")
    public Article getArticle(@PathVariable Long idArticle) {

        return articleService.getArticle(idArticle);
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @DeleteMapping()
    public Boolean deleteArticle(@PathVariable Long idArticle) {
        return articleService.deleteArticle(idArticle);
    }

}