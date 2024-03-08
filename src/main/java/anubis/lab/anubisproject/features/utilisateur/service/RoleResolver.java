package anubis.lab.anubisproject.features.utilisateur.service;

import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;

import java.util.List;

public interface RoleResolver {

    RoleDTO addRole(Role role);
    RoleDTO updateRole(Long idRole);
    Role getRole(Long idRole);
    List<RoleDTO> getRoles();
    Boolean deleteRole(Long idRole);
}
