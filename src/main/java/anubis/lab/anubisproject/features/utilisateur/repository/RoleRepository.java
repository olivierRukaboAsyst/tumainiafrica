package anubis.lab.anubisproject.features.utilisateur.repository;

import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
