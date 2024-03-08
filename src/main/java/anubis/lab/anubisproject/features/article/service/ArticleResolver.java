package anubis.lab.anubisproject.features.article.service;

import java.util.List;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

public interface ArticleResolver extends GraphQLQueryResolver, GraphQLMutationResolver {

    ArticleDTO addArticle(Article article, List<Long> idUtilisateurs, List<Long> idCategories, List<Long> idTags);
    Article getArticle(Long id);
    ArticleDTO updateArticle(Long idArticle, List<Long> idCategories, List<Long> idTags, Long idUtilisateur);
    List<ArticleDTO> getAllArticles();
    Boolean deleteArticle(Long id);
}
