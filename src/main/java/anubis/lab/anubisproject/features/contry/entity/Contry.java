package anubis.lab.anubisproject.features.contry.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class Contry {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String Symbol;
    private String fullName;
    private String superficie;
    private String population;
    private String shortHisory;
    private String description;
    private String independanceDay;
    private String flagUrl;
    @OneToMany(mappedBy = "contry")
    private List<Personality> personalityList;

    public Contry() {
    }

    public Contry(String id, String symbol, String fullName, String superficie, String population, String shortHisory, String description, String independanceDay, String flagUrl, List<Personality> personalityList) {
        this.id = id;
        Symbol = symbol;
        this.fullName = fullName;
        this.superficie = superficie;
        this.population = population;
        this.shortHisory = shortHisory;
        this.description = description;
        this.independanceDay = independanceDay;
        this.flagUrl = flagUrl;
        this.personalityList = personalityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getShortHisory() {
        return shortHisory;
    }

    public void setShortHisory(String shortHisory) {
        this.shortHisory = shortHisory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndependanceDay() {
        return independanceDay;
    }

    public void setIndependanceDay(String independanceDay) {
        this.independanceDay = independanceDay;
    }

    public List<Personality> getPersonalityList() {
        return personalityList;
    }

    public void setPersonalityList(List<Personality> personalityList) {
        this.personalityList = personalityList;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
