package com.example.lunatech.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "countries")
public class Country {

    @Id
    ObjectId databaseId;

    private int id;

    private String code;

    private String name;

    private String continent;

    private String wikipedia;

    private String keywords;

    // code to Airport iso_country
    // one to 0...N
//    private Set<String> airports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

//    public Set<String> getAirports() {
//        return airports;
//    }
//
//    public void setAirports(Set<String> airports) {
//        this.airports = airports;
//    }
}
