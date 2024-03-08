package anubis.lab.anubisproject.features.utilisateur.web;

import java.util.List;

import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.service.UtilisateurResolver;
import org.springframework.web.bind.annotation.*;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/utilisateur")
public class UserController {

    private UtilisateurResolver utilisateurService;

    public UserController(UtilisateurResolver utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public UtilisateurDTO addUtilisateur(@RequestBody Utilisateur utilisateur, @RequestParam List<Long> idRoles) {
        return utilisateurService.addUtilisateur(utilisateur, idRoles);
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateur(@PathVariable Long idUtilisateur) {
        return utilisateurService.getUtilisateur(idUtilisateur);
    }

    @GetMapping
    public List<UtilisateurDTO> getAllUtilisateur() {
        return utilisateurService.getAllUtilisateur();
    }

    @PutMapping
    public UtilisateurDTO updateUtilisateur(@PathVariable Long idUtilisateur, @RequestParam List<Role> roles, @RequestBody Utilisateur utilisateur
            ) {
        return utilisateurService.updateUtilisateur(idUtilisateur,
                utilisateur, roles);
    }

    @DeleteMapping
    public Boolean deleteUtilisateur(@PathVariable Long idUtilisateur) {
        return utilisateurService.deleteUtilisateur(idUtilisateur);
    }


}