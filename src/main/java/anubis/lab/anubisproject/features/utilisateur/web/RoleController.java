package anubis.lab.anubisproject.features.utilisateur.web;

import anubis.lab.anubisproject.exceptions.ErrorEntity;
import anubis.lab.anubisproject.features.utilisateur.dto.RoleDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.service.RoleResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleResolver roleResolver;

    public RoleController(RoleResolver roleResolver){
        this.roleResolver = roleResolver;
    }

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Role role){
        try {
            return ResponseEntity.ok(roleResolver.addRole(role));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @PutMapping("/{idRole}")
    public ResponseEntity updateRole(@PathVariable Long idRole, @RequestBody Role role){
        try {
            return ResponseEntity.ok(roleResolver.updateRole(idRole, role));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @GetMapping("/{idRole}")
    public ResponseEntity getRole(@PathVariable Long idRole){
        try {
            return ResponseEntity.ok(roleResolver.getRole(idRole));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity getRoles(){
        try {
            return ResponseEntity.ok(roleResolver.getRoles());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @GetMapping("/rolesByIds")
    public ResponseEntity getRolesByIds(@RequestParam List<Long> ids){
        try {
            return ResponseEntity.ok(roleResolver.getRolesByIds(ids));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @DeleteMapping
    Boolean deleteRole(Long idRole){
        return roleResolver.deleteRole(idRole);
    }

}
