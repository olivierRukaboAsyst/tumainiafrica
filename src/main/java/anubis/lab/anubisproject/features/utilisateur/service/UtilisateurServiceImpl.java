package anubis.lab.anubisproject.features.utilisateur.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import anubis.lab.anubisproject.exceptions.BadRequestExeption;
import anubis.lab.anubisproject.exceptions.NotFoundException;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.repository.RoleRepository;
import anubis.lab.anubisproject.helpers.Constant;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import anubis.lab.anubisproject.features.utilisateur.mapper.UtilisateurMapper;
import anubis.lab.anubisproject.features.utilisateur.repository.UtilisateurRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static anubis.lab.anubisproject.helpers.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurResolver {

    private UtilisateurRepository utilisateurRepository;
    private UtilisateurMapper mapper;
    private RoleResolver roleResolver;
    private RoleRepository roleRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper mapper, RoleResolver roleResolver, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.mapper = mapper;
        this.roleResolver = roleResolver;
        this.roleRepository = roleRepository;
    }

    @Override
    public UtilisateurDTO addUtilisateur(Utilisateur utilisateur, List<Long> idRoles) throws BadRequestExeption, NotFoundException {

//        try {
            if (idRoles == null || idRoles.isEmpty()){
                List<Long> ar = new ArrayList<>();
                ar.add(1L);
                List<Role> defaultRole = roleRepository.findAllById(ar);
                utilisateur.setRoles(defaultRole);
                log.info("Dans le if si idRoles est null, "+defaultRole.size());
            }else {
                List<Role> roles = new ArrayList<>();
                Role list;
                for (Long idRole : idRoles) {
                    list = roleResolver.getRole(idRole);
                    if (Objects.isNull(list)) {
                        throw new NotFoundException("Le role avec " + list.getIdRole() + " n'xiste pas dans le systeme");
                    }
                    roles.add(list);
                }
                utilisateur.setRoles(roles);
                log.info("Dans le else si le idRoles n'est pas null "+roles);
            }
            utilisateur.setCreatedAt(new Constant().dateFormated());

            Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
            return mapper.fromUtilisateur(savedUtilisateur);
//        }catch (Exception e){
//            throw new BadRequestExeption("Erreur lors de la sauvegarde");
//        }

    }

    @Override
    public UtilisateurDTO updateUtilisateur(Long idUtilisateur, Utilisateur requestUtilisateur, List<Role> roles) throws BadRequestExeption {

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
            utilisateur.setUpdatedAt(new Constant().dateFormated());
            Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
            return mapper.fromUtilisateur(savedUtilisateur);
        } catch (Exception e) {
            throw new BadRequestExeption("Erreur lors de la modification");
        }
    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateur() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty()) {
            throw new EntityNotFoundException(String.format("Pas d'Utilisateur pour le moment"));
        }
        List<UtilisateurDTO> utilisateurDTOs = utilisateurs.stream().map(u -> mapper.fromUtilisateur(u))
                .collect(Collectors.toList());
        return utilisateurDTOs;
    }

    @Override
    public Utilisateur getUtilisateur(Long idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cet utilisateur n'existe pas")));
        return utilisateur;
    }

    @Override
    public Boolean deleteUtilisateur(Long idUtilisateur) {
        getUtilisateur(idUtilisateur);
        utilisateurRepository.deleteById(idUtilisateur);

        return true;
    }

    @Override
    public String uploadImage(Long idUser, MultipartFile file) {
        try {
            log.info("Téléchargement de l'image ID: {}", idUser);
            Utilisateur utilisateur = getUtilisateur(idUser);
            utilisateur.setUpdatedAt(new Constant().dateFormated());
            String imageUrl = photoFunction.apply(idUser, file);
            utilisateur.setImageUrl(imageUrl);

            utilisateurRepository.save(utilisateur);
            log.info("Image sauvergardee");
            return imageUrl;
        }catch (Exception e){
            return e.getMessage();
        }

    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name->name.contains("."))
            .map(name->"."+name.substring(filename.lastIndexOf(".")+1)).orElse(".png");
    private final BiFunction<Long, MultipartFile, String> photoFunction = (idUser, image) -> {
        String fileName = idUser + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)){Files.createDirectories(fileStorageLocation);}
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(fileName), REPLACE_EXISTING);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/users/image/"+fileName).toUriString();
        }catch (Exception e){
            throw new RuntimeException("Impossible d'enregistrer l'image");
        }

    };


}
