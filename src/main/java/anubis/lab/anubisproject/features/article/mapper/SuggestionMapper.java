package anubis.lab.anubisproject.features.article.mapper;

import anubis.lab.anubisproject.features.article.dto.ArticleSuggestionDTO;
import anubis.lab.anubisproject.features.article.entity.ArticleSuggestion;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SuggestionMapper {

    ArticleSuggestionDTO fromArticleSuggestion(ArticleSuggestion suggestion){
        ArticleSuggestionDTO suggestionDTO = new ArticleSuggestionDTO();
        BeanUtils.copyProperties(suggestion, suggestionDTO);

        return  suggestionDTO;
    }

    ArticleSuggestion fromArticleSuggestionDTO(ArticleSuggestionDTO suggestionDTO){
        ArticleSuggestion suggestion = new ArticleSuggestion();
        BeanUtils.copyProperties(suggestionDTO, suggestion);

        return suggestion;
    }

}
