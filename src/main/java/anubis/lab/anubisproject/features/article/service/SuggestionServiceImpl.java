package anubis.lab.anubisproject.features.article.service;

import anubis.lab.anubisproject.features.article.dto.ArticleSuggestionDTO;
import anubis.lab.anubisproject.features.article.entity.ArticleSuggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Override
    public ArticleSuggestionDTO addSuggestion(ArticleSuggestion suggestion, Long idArticle) {
        return null;
    }

    @Override
    public ArticleSuggestion getSuggestionById(Long idSuggestion) {
        return null;
    }

    @Override
    public List<ArticleSuggestionDTO> getAllSuggestions() {
        return null;
    }

    @Override
    public boolean deleteSuggestion(String idSuggestion) {
        return false;
    }
}
