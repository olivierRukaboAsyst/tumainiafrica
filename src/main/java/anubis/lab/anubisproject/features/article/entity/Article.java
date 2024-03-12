package anubis.lab.anubisproject.features.article.entity;

import java.util.List;

import anubis.lab.anubisproject.features.tag.entity.Tag;
import jakarta.persistence.*;

import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import lombok.Builder;

@Entity
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Boolean isPublished;
    private Boolean isFrontPage = false;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurs;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "articles")
    private List<Tag> tags;
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    private String createdAt;
    private String updatedAt;

    public Article() {
    }

    public Article(Long id, String title, String content, Boolean isPublished, Boolean isFrontPage, List<Utilisateur> utilisateurs, List<Category> categories, List<Tag> tags, List<Comment> comments, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.isFrontPage = isFrontPage;
        this.utilisateurs = utilisateurs;
        this.categories = categories;
        this.tags = tags;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getFrontPage() {
        return isFrontPage;
    }

    public void setFrontPage(Boolean frontPage) {
        isFrontPage = frontPage;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isPublished=" + isPublished +
                ", isFrontPage=" + isFrontPage +
                ", utilisateurs=" + utilisateurs +
                ", categories=" + categories +
                ", tags=" + tags +
                ", comments=" + comments +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}