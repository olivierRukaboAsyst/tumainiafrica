package anubis.lab.anubisproject.features.tag.entity;

import anubis.lab.anubisproject.features.article.entity.Article;
import jakarta.persistence.*;
import lombok.Builder;
import java.util.List;
import java.util.Set;

@Entity
@Builder
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Article> articles;
    private String createdAt;
    private String updatedAt;

    public Tag() {
    }

    public Tag(Long idTag, String name, String description, Set<Article> articles, String createdAt, String updatedAt) {
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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
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