package anubis.lab.anubisproject.features.utilisateur.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

import anubis.lab.anubisproject.features.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
    private String description;
    private String imageUrl;
    private LocalDate birthDate;
    @OneToMany()
    private List<Role> roles;
    @ManyToMany(mappedBy = "utilisateurs")
    private List<Article> articles;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UtilisateurRole> utilisateurRoles = new HashSet<>();
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
