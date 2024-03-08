package anubis.lab.anubisproject.features.article.dto;

import java.time.LocalDate;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import lombok.Data;

@Data
public class ArticleDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private CategoryDTO idCategory;
    private UtilisateurDTO idUtilisateur;

}