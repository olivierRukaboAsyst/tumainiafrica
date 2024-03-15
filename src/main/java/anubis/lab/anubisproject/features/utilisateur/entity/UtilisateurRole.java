package anubis.lab.anubisproject.features.utilisateur.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "utilisateur_roles")
@Data
public class UtilisateurRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "roles_id_role")
    private Role role;
}
