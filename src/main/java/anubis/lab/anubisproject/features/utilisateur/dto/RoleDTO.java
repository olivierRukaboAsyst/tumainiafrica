package anubis.lab.anubisproject.features.utilisateur.dto;

import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class RoleDTO {
    private Long idRole;
    private String roleName;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
