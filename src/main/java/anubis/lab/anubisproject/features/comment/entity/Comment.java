package anubis.lab.anubisproject.features.comment.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import anubis.lab.anubisproject.features.article.entity.Article;
import anubis.lab.anubisproject.features.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @Transient
    private String customerName;
    private boolean isDisplay;
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "comment")
    private List<SignalComment> signalComments;
    private String createdAt;
    private String updatedAt;

    public Comment() {
    }

    public Comment(Long id, String content, String customerName, boolean isDisplay, Article article, Customer customer, List<SignalComment> signalComments, String createdAt, String updatedAt) {
        this.id = id;
        this.content = content;
        this.customerName = customerName;
        this.isDisplay = isDisplay;
        this.article = article;
        this.customer = customer;
        this.signalComments = signalComments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SignalComment> getSignalComments() {
        return signalComments;
    }

    public void setSignalComments(List<SignalComment> signalComments) {
        this.signalComments = signalComments;
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
