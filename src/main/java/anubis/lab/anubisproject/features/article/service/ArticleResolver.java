package anubis.lab.anubisproject.features.article.service;

import java.util.List;

import anubis.lab.anubisproject.exceptions.BadRequestExeption;
import anubis.lab.anubisproject.exceptions.NotFoundException;
import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.dto.ReactionDTO;
import anubis.lab.anubisproject.features.article.dto.ResponseArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.article.entity.Reaction;

public interface ArticleResolver {

    ResponseArticleDTO addArticle(Article article, Long idUtilisateurs, List<Long> idCategories, List<Long> idTags) throws BadRequestExeption;
    ArticleDTO getArticle(Long id);
    ResponseArticleDTO getArticleById(Long idArticle);
    List<ArticleDTO> getTopArticles();
    ResponseArticleDTO updateArticle(Article requestArticle, Long idArticle, List<Long> idCategories, List<Long> idTags) throws NotFoundException;
    List<ArticleDTO> getAllArticles();
    ResponseArticleDTO addUserOnArticle(Long idArticle, List<Long> idUtilisateurs) throws BadRequestExeption;
    ArticleDTO changeArticleStatus(Long idArticle, Boolean isPublished);
    ReactionDTO addReaction(Long idArticle, Reaction reaction) throws NotFoundException;
    Boolean deleteArticle(Long id);
}
