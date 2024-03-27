package anubis.lab.anubisproject.features.article.service;

import anubis.lab.anubisproject.features.article.dto.ArticleSuggestionDTO;
import anubis.lab.anubisproject.features.article.entity.ArticleSuggestion;

import java.util.List;

public interface SuggestionService {

    ArticleSuggestionDTO addSuggestion(ArticleSuggestion suggestion, Long idArticle);
    ArticleSuggestion getSuggestionById(Long idSuggestion);
    List<ArticleSuggestionDTO> getAllSuggestions();
    boolean deleteSuggestion(String idSuggestion);
}
