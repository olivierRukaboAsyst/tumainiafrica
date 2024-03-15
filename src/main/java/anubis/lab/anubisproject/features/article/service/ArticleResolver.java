package anubis.lab.anubisproject.features.article.service;

import java.util.List;

import anubis.lab.anubisproject.exceptions.BadRequestExeption;
import anubis.lab.anubisproject.exceptions.NotFoundException;
import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.dto.ResponseArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

public interface ArticleResolver extends GraphQLQueryResolver, GraphQLMutationResolver {

    ResponseArticleDTO addArticle(Article article, Long idUtilisateurs, List<Long> idCategories, List<Long> idTags) throws BadRequestExeption;
    ArticleDTO getArticle(Long id);
    ResponseArticleDTO getArticleById(Long idArticle);
    List<ArticleDTO> getTopArticles();
    ArticleDTO updateArticle(Article requestArticle, Long idArticle, List<Long> idCategories, List<Long> idTags, List<Long> idUtilisateur) throws NotFoundException;
    List<ArticleDTO> getAllArticles();
    ArticleDTO addUserOnArticle(Long idArticle, Long idUtilisateur) throws BadRequestExeption;
    ArticleDTO changeArticleStatus(Long idArticle, Boolean isPublished);
    Boolean deleteArticle(Long id);
}
