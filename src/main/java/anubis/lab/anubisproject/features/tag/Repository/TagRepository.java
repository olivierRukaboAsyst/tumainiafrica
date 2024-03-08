package anubis.lab.anubisproject.features.tag.Repository;

import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
