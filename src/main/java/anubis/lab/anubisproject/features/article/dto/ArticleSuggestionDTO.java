package anubis.lab.anubisproject.features.article.dto;

import jakarta.persistence.Column;

public class ArticleSuggestionDTO {
    private String id;
    private String fullNameOfRequested;
    private String emailOfRequested;
    private String numberOfRequested;
    private String requestContent;
    private String professionOfRequested;
    private String countryOfRequested;
    private String townOfRequested;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullNameOfRequested() {
        return fullNameOfRequested;
    }

    public void setFullNameOfRequested(String fullNameOfRequested) {
        this.fullNameOfRequested = fullNameOfRequested;
    }

    public String getEmailOfRequested() {
        return emailOfRequested;
    }

    public void setEmailOfRequested(String emailOfRequested) {
        this.emailOfRequested = emailOfRequested;
    }

    public String getNumberOfRequested() {
        return numberOfRequested;
    }

    public void setNumberOfRequested(String numberOfRequested) {
        this.numberOfRequested = numberOfRequested;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getProfessionOfRequested() {
        return professionOfRequested;
    }

    public void setProfessionOfRequested(String professionOfRequested) {
        this.professionOfRequested = professionOfRequested;
    }

    public String getCountryOfRequested() {
        return countryOfRequested;
    }

    public void setCountryOfRequested(String countryOfRequested) {
        this.countryOfRequested = countryOfRequested;
    }

    public String getTownOfRequested() {
        return townOfRequested;
    }

    public void setTownOfRequested(String townOfRequested) {
        this.townOfRequested = townOfRequested;
    }
}
