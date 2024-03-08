package anubis.lab.anubisproject.features.utilisateur.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.repository.RoleRepository;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import anubis.lab.anubisproject.features.utilisateur.mapper.UtilisateurMapper;
import anubis.lab.anubisproject.features.utilisateur.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurResolver {

    private UtilisateurRepository utilisateurRepository;
    private UtilisateurMapper mapper;
    private RoleResolver roleResolver;
    private RoleRepository roleRepository;

    @Override
    public UtilisateurDTO addUtilisateur(Utilisateur utilisateur, List<Long> idRoles) {

        for (Long idRole: idRoles){
            Optional<Role> roleOptional = roleRepository.findById(idRole);
            if (!roleOptional.isPresent()){
                new RuntimeException(String.format("Ce role est introuvable"));
            }
        }

        List<Role> roles = roleRepository.findAllById(idRoles);
        utilisateur.setRoles(roles);

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return mapper.fromUtilisateur(savedUtilisateur);
    }

    @Override
    public UtilisateurDTO updateUtilisateur(Long idUtilisateur, Utilisateur requestUtilisateur, List<Role> roles) {

        Utilisateur utilisateur = getUtilisateur(idUtilisateur);
        try {
            if (requestUtilisateur != null) {
                if (Objects.nonNull(requestUtilisateur.getFirstname())){
                    utilisateur.setFirstname(requestUtilisateur.getFirstname());
                }
                if (Objects.nonNull(requestUtilisateur.getLastname())){
                    utilisateur.setLastname(requestUtilisateur.getLastname());
                }
                if (Objects.nonNull(requestUtilisateur.getEmail())){
                    utilisateur.setEmail(requestUtilisateur.getEmail());
                }
                if (Objects.nonNull(requestUtilisateur.getPhoneNumber())){
                    utilisateur.setPhoneNumber(requestUtilisateur.getPhoneNumber());
                }
                if (Objects.nonNull(requestUtilisateur.getPassword())){
                    utilisateur.setPassword(requestUtilisateur.getPassword());
                }
                if (Objects.nonNull(requestUtilisateur.getDescription())){
                    utilisateur.setDescription(requestUtilisateur.getDescription());
                }
                if (Objects.nonNull(requestUtilisateur.getImageUrl())){
                    utilisateur.setImageUrl(requestUtilisateur.getImageUrl());
                }
                if (Objects.nonNull(requestUtilisateur.getBirthDate())){
                    utilisateur.setBirthDate(requestUtilisateur.getBirthDate());
                }
                if (!(requestUtilisateur.getRoles().isEmpty())){
                    utilisateur.setRoles(requestUtilisateur.getRoles());
                }
            }
            Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
            return mapper.fromUtilisateur(savedUtilisateur);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erreur lors de la modification"));
        }
    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateur() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty()) {
            throw new RuntimeException(String.format("Pas d'Utilisateur pour le moment"));
        }
        List<UtilisateurDTO> utilisateurDTOs = utilisateurs.stream().map(u -> mapper.fromUtilisateur(u))
                .collect(Collectors.toList());
        return utilisateurDTOs;
    }

    @Override
    public Utilisateur getUtilisateur(Long idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new RuntimeException(String.format("Cet utilisateur n'existe pas")));
        return utilisateur;
    }

    @Override
    public Boolean deleteUtilisateur(Long idUtilisateur) {
        getUtilisateur(idUtilisateur);
        utilisateurRepository.deleteById(idUtilisateur);

        return true;
    }

}
