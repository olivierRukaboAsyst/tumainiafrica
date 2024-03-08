package anubis.lab.anubisproject.features.utilisateur.service;

import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.mapper.UtilisateurMapper;
import anubis.lab.anubisproject.features.utilisateur.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleResolver{

    private RoleRepository roleRepository;
    private UtilisateurMapper mapper;
    @Override
    public RoleDTO addRole(Role role) {
        try {
            Role savedRole = roleRepository.save(role);
            return mapper.fromRole(savedRole);
        }catch (Exception e){
            throw new RuntimeException(String.format("Erreur lors de la sauvegarde du role"));
        }
    }

    @Override
    public RoleDTO updateRole(Long idRole) {
        Role role = getRole(idRole);

        try {
            if (role != null){
            }
            Role savedRole = roleRepository.save(role);
            return mapper.fromRole(savedRole);
        }catch (Exception e){
            throw new RuntimeException(String.format("Erreur lors de la modification du role"));
        }
    }
    @Override
    public Role getRole(Long idRole){
        Role role = roleRepository.findById(idRole).orElseThrow(()->
                new RuntimeException(String.format("Ce role est introuvable")));
        return role;
    }
    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()){
            throw new RuntimeException(String.format("Pas des roles enregistrés"));
        }
        List<RoleDTO> roleDTOS = roles.stream().map(r -> mapper.fromRole(r)).collect(Collectors.toList());
        return roleDTOS;
    }

    @Override
    public Boolean deleteRole(Long idRole) {
        getRole(idRole);
        try {
            roleRepository.deleteById(idRole);
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
