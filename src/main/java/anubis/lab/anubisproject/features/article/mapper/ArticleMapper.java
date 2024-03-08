package anubis.lab.anubisproject.features.article.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import anubis.lab.anubisproject.features.article.entity.Article;
import lombok.AllArgsConstructor;

@Service
public class ArticleMapper {

    public ArticleDTO fromArticle(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);

        return articleDTO;
    }
    
    public Article fromArticleDTO(ArticleDTO articleDTO){
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);

        return article;
    }

}
