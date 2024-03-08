package anubis.lab.anubisproject.features.utilisateur.mapper;

import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;

@Service
public class UtilisateurMapper {

    public UtilisateurDTO fromUtilisateur(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        BeanUtils.copyProperties(utilisateur, utilisateurDTO);

        return utilisateurDTO;
    }

    public Utilisateur fromUtilisateurDTO(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur = new Utilisateur();
        BeanUtils.copyProperties(utilisateurDTO, utilisateur);

        return utilisateur;
    }

    public RoleDTO fromRole(Role role){
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);

        return roleDTO;
    }

    public Role fromRoleDTO(RoleDTO roleDTO){
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);

        return role;
    }
    
}
