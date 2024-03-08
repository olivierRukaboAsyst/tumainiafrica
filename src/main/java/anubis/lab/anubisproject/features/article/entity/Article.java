package anubis.lab.anubisproject.features.article.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import anubis.lab.anubisproject.features.tag.entity.Tag;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import anubis.lab.anubisproject.features.category.entity.Category;
import anubis.lab.anubisproject.features.comment.entity.Comment;
import anubis.lab.anubisproject.features.utilisateur.entity.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}