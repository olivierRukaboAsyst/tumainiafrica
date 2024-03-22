package anubis.lab.anubisproject.features.article.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.service.ArticleResolver;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleResolver articleService;

    public ArticleController(ArticleResolver articleService) {
        this.articleService = articleService;
    }

    @PostMapping()
    public ResponseEntity<?> addArticle(@RequestBody Article article,
                                        @RequestParam(value = "utilisateurs") Long idUtilisateur, @RequestParam(value = "categories") List<Long> idCategories,
                                        @RequestParam(value = "tags") List<Long> idTags) {
        try {
            return ResponseEntity.ok(this.articleService.addArticle(article, idUtilisateur, idCategories, idTags));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{idArticle}")
    public ResponseEntity<?> updateArticle(@RequestBody Article article, @PathVariable Long idArticle, @RequestParam(value = "categories", required = false) List<Long> idCategories,
                                           @RequestParam(value = "tags", required = false) List<Long> tags, @RequestParam(value = "utilisateurs", required = false) List<Long> idUtilisateurs) {
        try {
            return ResponseEntity.ok(this.articleService.updateArticle(article, idArticle, idCategories, tags));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idArticle}")
    public ResponseEntity<?> getArticle(@PathVariable Long idArticle) {
        try {
            return ResponseEntity.ok(this.articleService.getArticleById(idArticle));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopArticles(){
        try {
            return ResponseEntity.ok(this.articleService.getTopArticles());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllArticles() {
        try {
            return ResponseEntity.ok(this.articleService.getAllArticles());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("adduser/{idArticle}")
    public ResponseEntity<?> addUserOnArticle (@PathVariable Long idArticle, @RequestParam(name = "utilisateur") List<Long> idUtilisateurs){
        try {
            return ResponseEntity.ok(this.articleService.addUserOnArticle(idArticle, idUtilisateurs));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/changestatus/{idArticle}")
    public ResponseEntity<?> chandeArticleStatus(@PathVariable Long idArticle, @RequestParam Boolean isPublished){
        try {
            return ResponseEntity.ok(articleService.changeArticleStatus(idArticle, isPublished));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping()
    public Boolean deleteArticle(@PathVariable Long idArticle) {
        return this.articleService.deleteArticle(idArticle);
    }

}