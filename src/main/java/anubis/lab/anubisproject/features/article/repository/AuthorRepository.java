package anubis.lab.anubisproject.features.article.repository;

import anubis.lab.anubisproject.features.article.entity.AuthorOpinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorOpinion, String> {
}
