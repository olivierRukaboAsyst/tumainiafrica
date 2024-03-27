package anubis.lab.anubisproject.features.comment.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class SignalComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullNameOfSignal;
    private String causeOfSignal;
    @ManyToOne
    private Comment comment;
    private String createdAt;
    private String updatedAt;

    public SignalComment() {
    }

    public SignalComment(String id, String fullNameOfSignal, String causeOfSignal, Comment comment, String createdAt, String updatedAt) {
        this.id = id;
        this.fullNameOfSignal = fullNameOfSignal;
        this.causeOfSignal = causeOfSignal;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullNameOfSignal() {
        return fullNameOfSignal;
    }

    public void setFullNameOfSignal(String fullNameOfSignal) {
        this.fullNameOfSignal = fullNameOfSignal;
    }

    public String getCauseOfSignal() {
        return causeOfSignal;
    }

    public void setCauseOfSignal(String causeOfSignal) {
        this.causeOfSignal = causeOfSignal;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
