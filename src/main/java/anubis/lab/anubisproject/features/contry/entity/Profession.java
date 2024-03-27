package anubis.lab.anubisproject.features.contry.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String professsionName;
    private String miniDescription;
    private boolean isCurrent;
    @ManyToOne
    private Personality personality;
    private String createdAt;
    private String updatedAt;

    public Profession() {
    }

    public Profession(Long id, String professsionName, String miniDescription, boolean isCurrent, Personality personality, String createdAt, String updatedAt) {
        this.id = id;
        this.professsionName = professsionName;
        this.miniDescription = miniDescription;
        this.isCurrent = isCurrent;
        this.personality = personality;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfesssionName() {
        return professsionName;
    }

    public void setProfesssionName(String professsionName) {
        this.professsionName = professsionName;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        this.miniDescription = miniDescription;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public Personality getPersonality() {
        return personality;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
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
