package anubis.lab.anubisproject.features.utilisateur.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import anubis.lab.anubisproject.features.article.entity.Article;
import lombok.Builder;

@Entity
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
    private String description;
    private String imageUrl;
    private LocalDate birthDate;
    @OneToMany()
    private List<Role> roles;
    @ManyToMany(mappedBy = "utilisateurs")
    private List<Article> articles;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UtilisateurRole> utilisateurRoles = new HashSet<>();
    private String createdAt;
    private String updatedAt;

    public Utilisateur() {
    }

    public Utilisateur(Long id, String firstname, String lastname, String email, String phoneNumber, String password, String description, String imageUrl, LocalDate birthDate, List<Role> roles, List<Article> articles, Set<UtilisateurRole> utilisateurRoles, String createdAt, String updatedAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.description = description;
        this.imageUrl = imageUrl;
        this.birthDate = birthDate;
        this.roles = roles;
        this.articles = articles;
        this.utilisateurRoles = utilisateurRoles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Set<UtilisateurRole> getUtilisateurRoles() {
        return utilisateurRoles;
    }

    public void setUtilisateurRoles(Set<UtilisateurRole> utilisateurRoles) {
        this.utilisateurRoles = utilisateurRoles;
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
