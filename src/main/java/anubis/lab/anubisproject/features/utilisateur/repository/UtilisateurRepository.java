package anubis.lab.anubisproject.features.utilisateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    
}