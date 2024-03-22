package anubis.lab.anubisproject.features.article.dto;

import anubis.lab.anubisproject.features.category.dto.CategoryDTO;
import anubis.lab.anubisproject.features.comment.dto.CommentDTO;
import anubis.lab.anubisproject.features.tag.dto.TagDTO;
import anubis.lab.anubisproject.features.utilisateur.dto.UtilisateurDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

public class ResponseArticleDTO {
    private Long id;
    private String title;
    private String content;
    private int views;
    private int reactionsNumber;
    private int numberOflike;
    private int numberOfLaugh;
    private int numberOfDislike;
    private int commentNumber;
    private boolean isPublished;
    private boolean isFrontPage;
    private Set<CategoryDTO> categories;
    private List<UtilisateurDTO> utilisateurs;
    private List<TagDTO> tags;
    private List<CommentDTO> comments;
    @JsonIgnore
    private List<ReactionDTO> reactions;
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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public int getReactionsNumber() {
        return reactionsNumber;
    }

    public void setReactionsNumber(int reactionsNumber) {
        this.reactionsNumber = reactionsNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public List<ReactionDTO> getReactions() {
        return reactions;
    }

    public void setReactions(List<ReactionDTO> reactions) {
        this.reactions = reactions;
    }

    public int getNumberOflike() {
        return numberOflike;
    }

    public void setNumberOflike(int numberOflike) {
        this.numberOflike = numberOflike;
    }

    public int getNumberOfLaugh() {
        return numberOfLaugh;
    }

    public void setNumberOfLaugh(int numberOfLaugh) {
        this.numberOfLaugh = numberOfLaugh;
    }

    public int getNumberOfDislike() {
        return numberOfDislike;
    }

    public void setNumberOfDislike(int numberOfDislike) {
        this.numberOfDislike = numberOfDislike;
    }
}
