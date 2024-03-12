package anubis.lab.anubisproject.features.tag.entity;

import anubis.lab.anubisproject.features.article.entity.Article;
import jakarta.persistence.*;
import lombok.Builder;
import java.util.List;

@Entity
@Builder
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Article> articles;
    private String createdAt;
    private String updatedAt;

    public Tag() {
    }

    public Tag(Long idTag, String name, String description, List<Article> articles, String createdAt, String updatedAt) {
        this.idTag = idTag;
        this.name = name;
        this.description = description;
        this.articles = articles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}