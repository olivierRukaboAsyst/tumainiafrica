package anubis.lab.anubisproject.features.utilisateur.dto;

import java.time.LocalDate;
import java.util.List;

import anubis.lab.anubisproject.features.article.dto.ArticleDTO;
import lombok.Data;

@Data
public class UtilisateurDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String roles;
    private String description;
    private LocalDate birthDate;
    private List<ArticleDTO> articles;

}
