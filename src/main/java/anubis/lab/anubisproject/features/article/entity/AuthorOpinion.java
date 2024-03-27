package anubis.lab.anubisproject.features.article.entity;

import jakarta.persistence.*;

@Entity
public class AuthorOpinion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private String fonction;
    private String imageUrl;
    @ManyToOne
    private Article article;

    public AuthorOpinion() {
    }

    public AuthorOpinion(String id, String fullName, String fonction, String imageUrl, Article article) {
        this.id = id;
        this.fullName = fullName;
        this.fonction = fonction;
        this.imageUrl = imageUrl;
        this.article = article;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
