package anubis.lab.anubisproject.features.utilisateur.web;

import java.util.List;

import anubis.lab.anubisproject.exceptions.ErrorEntity;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.service.UtilisateurResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {

    private UtilisateurResolver utilisateurService;

    public UserController(UtilisateurResolver utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<?> addUtilisateur(@RequestBody Utilisateur utilisateur, @RequestParam(value = "roles", required = false) List<Long> idRoles) {
        try {
            return ResponseEntity.ok(utilisateurService.addUtilisateur(utilisateur, idRoles));
        }catch (Exception p){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, p.getMessage()));
        }
    }

    @GetMapping("/{idUtilisateur}")
    public ResponseEntity<?> getUtilisateur(@PathVariable Long idUtilisateur) {
        try {
            return ResponseEntity.ok(utilisateurService.getUtilisateur(idUtilisateur));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }
    }

    @GetMapping("/usersByIds")
    public ResponseEntity<?> getUtilisateursByIds(@RequestParam(value = "users", required = false) List<Long> list){
        try {
            return ResponseEntity.ok(utilisateurService.getAllByIds(list));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUtilisateur() {
        try {
            return ResponseEntity.ok(utilisateurService.getAllUtilisateur());
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ErrorEntity(null, e.getMessage()));
        }

    }

    @PutMapping("/{idUtilisateur}")
    public ResponseEntity updateUtilisateur(@PathVariable Long idUtilisateur, @RequestParam(required = false, name = "roles") List<Role> roles, @RequestBody Utilisateur utilisateur) {
        try {
            return ResponseEntity.ok(utilisateurService.updateUtilisateur(idUtilisateur,
                    utilisateur, roles));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(new ErrorEntity(null, e.getMessage()));
        }

    }

    @PutMapping("/image")
    public ResponseEntity<String> uploadProfileImage(@RequestParam("idUser") Long idUser, @RequestParam("file") MultipartFile file){
        try {
            return ResponseEntity.ok().body(utilisateurService.uploadImage(idUser, file));
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping
    public Boolean deleteUtilisateur(@PathVariable Long idUtilisateur) {
        return utilisateurService.deleteUtilisateur(idUtilisateur);
    }


}