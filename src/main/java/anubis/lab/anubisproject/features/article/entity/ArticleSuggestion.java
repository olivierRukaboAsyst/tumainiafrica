package anubis.lab.anubisproject.features.article.entity;

import jakarta.persistence.*;

@Entity
public class ArticleSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullNameOfRequested;
    private String emailOfRequested;
    private String numberOfRequested;
    @Column(columnDefinition = "TEXT")
    private String requestContent;
    private String professionOfRequested;
    private String countryOfRequested;
    private String townOfRequested;
    private boolean isrequestHasSent;
    private boolean isRequestHasConsidered;
    private boolean isRequestHasConsideredAndIncluded;
    @ManyToOne
    private Article article;
    private String requestCreatedAt;
    private String requestUpdatedAt;

    public ArticleSuggestion() {
    }

    public ArticleSuggestion(String id, String fullNameOfRequested, String emailOfRequested, String numberOfRequested, String requestContent, String professionOfRequested, String countryOfRequested, String townOfRequested, boolean isrequestHasSent, boolean isRequestHasConsidered, boolean isRequestHasConsideredAndIncluded, Article article, String requestCreatedAt, String requestUpdatedAt) {
        this.id = id;
        this.fullNameOfRequested = fullNameOfRequested;
        this.emailOfRequested = emailOfRequested;
        this.numberOfRequested = numberOfRequested;
        this.requestContent = requestContent;
        this.professionOfRequested = professionOfRequested;
        this.countryOfRequested = countryOfRequested;
        this.townOfRequested = townOfRequested;
        this.isrequestHasSent = isrequestHasSent;
        this.isRequestHasConsidered = isRequestHasConsidered;
        this.isRequestHasConsideredAndIncluded = isRequestHasConsideredAndIncluded;
        this.article = article;
        this.requestCreatedAt = requestCreatedAt;
        this.requestUpdatedAt = requestUpdatedAt;
    }

    @Override
    public String toString() {
        return "ArticleSuggestions{" +
                "id='" + id + '\'' +
                ", fullNameOfRequested='" + fullNameOfRequested + '\'' +
                ", emailOfRequested='" + emailOfRequested + '\'' +
                ", numberOfRequested='" + numberOfRequested + '\'' +
                ", requestContent='" + requestContent + '\'' +
                ", professionOfRequested='" + professionOfRequested + '\'' +
                ", countryOfRequested='" + countryOfRequested + '\'' +
                ", townOfRequested='" + townOfRequested + '\'' +
                ", isrequestHasSent=" + isrequestHasSent +
                ", isRequestHasConsidered=" + isRequestHasConsidered +
                ", isRequestHasConsideredAndIncluded=" + isRequestHasConsideredAndIncluded +
                ", article=" + article +
                ", requestCreatedAt='" + requestCreatedAt + '\'' +
                ", requestUpdatedAt='" + requestUpdatedAt + '\'' +
                '}';
    }
}
