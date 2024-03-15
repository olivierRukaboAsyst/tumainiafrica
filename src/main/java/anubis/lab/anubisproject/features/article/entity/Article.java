package anubis.lab.anubisproject.features.article.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import anubis.lab.anubisproject.features.tag.entity.Tag;
import anubis.lab.anubisproject.features.utilisateur.entity.UtilisateurRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int views;
    private boolean isPublished;
    private boolean isFrontPage;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Utilisateur> utilisateurs;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categorie_article", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tag_article", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "idTag"))
    private Set<Tag> tags;
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    private String createdAt;
    private String updatedAt;

    public Article() {
    }

    public Article(Long id, String title, String content, int views, boolean isPublished, boolean isFrontPage, List<Utilisateur> utilisateurs, Set<Category> categories, Set<Tag> tags, List<Comment> comments, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.views = views;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean isFrontPage() {
        return isFrontPage;
    }

    public void setFrontPage(boolean frontPage) {
        isFrontPage = frontPage;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
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
                ", views=" + views +
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