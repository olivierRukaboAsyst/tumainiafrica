package anubis.lab.anubisproject.features.article.controller;

import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.tag.entity.Tag;
import jakarta.persistence.*;

@Entity
@Table(name = "tag_article")
public class TagArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}