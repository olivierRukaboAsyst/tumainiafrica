package anubis.lab.anubisproject.features.utilisateur.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    private String roleName;
    @ManyToOne()
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UtilisateurRole> utilisateurRoles = new HashSet<>();
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

}
