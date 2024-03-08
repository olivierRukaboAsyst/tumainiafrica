package anubis.lab.anubisproject.features.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anubis.lab.anubisproject.features.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
