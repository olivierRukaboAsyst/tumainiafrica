package anubis.lab.anubisproject.features.utilisateur.mapper;

import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.repository.RoleRepository;
import anubis.lab.anubisproject.features.utilisateur.repository.UtilisateurRepository;
import anubis.lab.anubisproject.features.utilisateur.service.RoleResolver;
import anubis.lab.anubisproject.features.utilisateur.service.RoleServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurMapper {
    private RoleRepository roleRepository;

    public UtilisateurMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UtilisateurDTO fromUtilisateur(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        List<Long> idRoles = new ArrayList<>();
        for(Role role: utilisateur.getRoles()){
            Optional<Role> optionalRole = roleRepository.findById(role.getIdRole());
            optionalRole.ifPresent(value -> idRoles.add(value.getIdRole()));
        }

        List<Role> roleList = roleRepository.findAllById(idRoles);
        utilisateurDTO.setRoles(roleList.stream().map(this::fromRole).collect(Collectors.toList()));
        BeanUtils.copyProperties(utilisateur, utilisateurDTO);

        return utilisateurDTO;
    }

    public List<UtilisateurDTO> fromUtilisateurList(List<Utilisateur> utilisateurs){
        List<UtilisateurDTO> utilisateurDTOS = new ArrayList<>();
        BeanUtils.copyProperties(utilisateurs, utilisateurDTOS);

        return utilisateurDTOS;
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
    public List<RoleDTO> fromRoles(List<Role> roles){
        List<RoleDTO> roleDTOS = new ArrayList<>();
        BeanUtils.copyProperties(roles, roleDTOS);

        return roleDTOS;
    }
    public Role fromRoleDTO(RoleDTO roleDTO){
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);

        return role;
    }
    
}
