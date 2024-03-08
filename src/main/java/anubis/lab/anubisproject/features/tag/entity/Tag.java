package anubis.lab.anubisproject.features.tag.entity;

import anubis.lab.anubisproject.features.article.entity.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTag;
    private String name;
    private String description;
    private String iconUrl;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Article> articles;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}