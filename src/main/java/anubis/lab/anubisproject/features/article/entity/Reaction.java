package anubis.lab.anubisproject.features.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int likeCount;
    private int laugh;
    private int dislike;
    @ManyToOne
    @JoinColumn(name = "article_id")
    @JsonIgnore
    private Article article;
    private String createdAt;
    private String updatedAt;

    public Reaction() {
    }

    public Reaction(Long id, int likeCount, int laugh, int dislike, Article article, String createdAt, String updatedAt) {
        this.id = id;
        this.likeCount = likeCount;
        this.laugh = laugh;
        this.dislike = dislike;
        this.article = article;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLike() {
        return likeCount;
    }

    public void setLike(int like) {
        this.likeCount = like;
    }

    public int getLaugh() {
        return laugh;
    }

    public void setLaugh(int laugh) {
        this.laugh = laugh;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
