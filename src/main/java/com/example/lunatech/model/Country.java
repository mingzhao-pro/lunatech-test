package com.example.lunatech.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "countries")
public class Country {

    @Id
    ObjectId databaseId;

    private int id;

    private int nbAirport;

    private String code;

    private String name;

    private String continent;

    private String wikipedia;

    private String keywords;

    private Set<String> airports;

    private Set<String> surfacesOfRunways;


    public ObjectId getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(ObjectId databaseId) {
        this.databaseId = databaseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbAirport() {
        return nbAirport;
    }

    public void setNbAirport(int nbAirport) {
        this.nbAirport = nbAirport;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Set<String> getAirports() {
        return airports;
    }

    public void setAirports(Set<String> airports) {
        this.airports = airports;
    }

    public Set<String> getSurfacesOfRunways() {
        return surfacesOfRunways;
    }

    public void setSurfacesOfRunways(Set<String> surfacesOfRunways) {
        this.surfacesOfRunways = surfacesOfRunways;
    }
}
