package anubis.lab.anubisproject.features.article.dto;

import java.util.List;
import java.util.Set;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ArticleDTO {

    private Long id;
    private String title;
    private String content;
    private int views;
    private boolean isPublished;
    private boolean isFrontPage;
    @JsonIgnore
    private Set<CategoryDTO> categories;
    @JsonIgnore
    private List<UtilisateurDTO> utilisateurs;
    @JsonIgnore
    private List<TagDTO> tags;
    private String createdAt;
    private String updatedAt;

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

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<UtilisateurDTO> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<UtilisateurDTO> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
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