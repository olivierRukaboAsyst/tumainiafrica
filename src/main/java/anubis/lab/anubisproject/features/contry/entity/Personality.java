package anubis.lab.anubisproject.features.contry.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private String birthDate;
    @OneToMany
    private List<Profession> professions;
    private String miniBiography;
    private boolean isDead;
    private String deadDate;
    @ManyToOne
    private Contry contry;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;

    public Personality() {
    }

    public Personality(String id, String fullName, String birthDate, List<Profession> professions, String miniBiography, boolean isDead, String deadDate, Contry contry, String imageUrl, String createdAt, String updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.professions = professions;
        this.miniBiography = miniBiography;
        this.isDead = isDead;
        this.deadDate = deadDate;
        this.contry = contry;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<Profession> professions) {
        this.professions = professions;
    }

    public String getMiniBiography() {
        return miniBiography;
    }

    public void setMiniBiography(String miniBiography) {
        this.miniBiography = miniBiography;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public String getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(String deadDate) {
        this.deadDate = deadDate;
    }

    public Contry getContry() {
        return contry;
    }

    public void setContry(Contry contry) {
        this.contry = contry;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
