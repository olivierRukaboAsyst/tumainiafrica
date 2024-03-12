package anubis.lab.anubisproject.features.article.dto;

import java.time.LocalDate;
import java.util.List;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import lombok.Data;

@Data
public class ArticleDTO {

    private Long id;
    private String title;
    private String content;
    private List<CategoryDTO> categories;
    private List<UtilisateurDTO> utilisateurs;
    private String createdAt;
    private String updatedAt;

}