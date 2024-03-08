package anubis.lab.anubisproject.features.utilisateur.service;

import java.util.List;

import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import anubis.lab.anubisproject.features.utilisateur.entity.Role;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

public interface UtilisateurResolver extends GraphQLQueryResolver, GraphQLMutationResolver {

    UtilisateurDTO addUtilisateur(Utilisateur utilisateur, List<Long> idRoles);

    Utilisateur getUtilisateur(Long id);

    UtilisateurDTO updateUtilisateur(Long idUtilisateur, Utilisateur utilisateur, List<Role> roles);

    List<UtilisateurDTO> getAllUtilisateur();

    Boolean deleteUtilisateur(Long idUtilisateur);

}