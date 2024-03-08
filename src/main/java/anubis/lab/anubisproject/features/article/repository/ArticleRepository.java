package anubis.lab.anubisproject.features.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anubis.lab.anubisproject.features.article.entity.Article;


public interface ArticleRepository extends JpaRepository<Article, Long> {

}
