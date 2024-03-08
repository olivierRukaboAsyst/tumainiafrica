package anubis.lab.anubisproject.features.utilisateur.web;

import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.service.RoleResolver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleResolver roleResolver;

    public RoleController(RoleResolver roleResolver) {
        this.roleResolver = roleResolver;
    }

    @PostMapping
    public RoleDTO addRole(@RequestBody Role role){
        return roleResolver.addRole(role);
    }

    @PutMapping
    RoleDTO updateRole(@PathVariable Long idRole){
        return roleResolver.updateRole(idRole);
    }

    @GetMapping
    public Role getRole(@PathVariable Long idRole){
        return roleResolver.getRole(idRole);
    }

    @GetMapping
    public List<RoleDTO> getRoles(){
        return roleResolver.getRoles();
    }

    @DeleteMapping
    Boolean deleteRole(Long idRole){
        return roleResolver.deleteRole(idRole);
    }

}
