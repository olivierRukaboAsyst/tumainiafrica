package anubis.lab.anubisproject.features.utilisateur.service;

import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;

import java.util.List;

public interface RoleResolver {

    RoleDTO addRole(Role role);
    RoleDTO updateRole(Long idRole, Role role);
    Role getRole(Long idRole);
    List<RoleDTO> getRoles();
    List<RoleDTO> getRolesByIds(List<Long> ids);
    Boolean deleteRole(Long idRole);
}
