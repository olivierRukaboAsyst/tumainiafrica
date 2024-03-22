package anubis.lab.anubisproject.features.article.repository;

import anubis.lab.anubisproject.features.article.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findAllByArticleId(Long idArticle);
    @Query(value = "SELECT SUM(r.like_count) FROM reaction r WHERE article_id = :idArticle", nativeQuery = true)
    Integer getTotalLikes(@Param("idArticle") Long idArticle);

    @Query(value = "SELECT SUM(r.laugh) FROM reaction r WHERE article_id = :idArticle", nativeQuery = true)
    Integer getTotalLaughs(@Param("idArticle") Long idArticle);

    @Query(value = "SELECT SUM(r.dislike) FROM reaction r WHERE article_id = :idArticle", nativeQuery = true)
    Integer getTotalDisLikes(@Param("idArticle") Long idArticle);
}
