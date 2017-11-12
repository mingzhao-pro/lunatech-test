package com.example.lunatech.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "runways")
public class Runway {

    @Id
    ObjectId databaseId;

    private int id;
    private String airportRef;
    @Field("airport_ident")
    private String airportIdent;
    private String lengthFt;
    private String widthFt;
    private String surface;
    private String lighted;
    private String closed;
    @Field("le_ident")
    private String leIdent;
    private String leLatitudeDeg;
    private String leLongitudeDeg;
    private String leElevationFt;
    private String leHeadingDegT;
    private String leDisplacedThresholdFt;
    private String heIdent;
    private String heLatitudeDeg;
    private String heLongitudeDeg;
    private String heElevationFt;
    private String heHeadingDegT;
    private String heDisplacedThresholdFt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirportRef() {
        return airportRef;
    }

    public void setAirportRef(String airportRef) {
        this.airportRef = airportRef;
    }

    public String getAirportIdent() {
        return airportIdent;
    }

    public void setAirportIdent(String airportIdent) {
        this.airportIdent = airportIdent;
    }

    public String getLengthFt() {
        return lengthFt;
    }

    public void setLengthFt(String lengthFt) {
        this.lengthFt = lengthFt;
    }

    public String getWidthFt() {
        return widthFt;
    }

    public void setWidthFt(String widthFt) {
        this.widthFt = widthFt;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getLighted() {
        return lighted;
    }

    public void setLighted(String lighted) {
        this.lighted = lighted;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getLeIdent() {
        return leIdent;
    }

    public void setLeIdent(String leIdent) {
        this.leIdent = leIdent;
    }

    public String getLeLatitudeDeg() {
        return leLatitudeDeg;
    }

    public void setLeLatitudeDeg(String leLatitudeDeg) {
        this.leLatitudeDeg = leLatitudeDeg;
    }

    public String getLeLongitudeDeg() {
        return leLongitudeDeg;
    }

    public void setLeLongitudeDeg(String leLongitudeDeg) {
        this.leLongitudeDeg = leLongitudeDeg;
    }

    public String getLeElevationFt() {
        return leElevationFt;
    }

    public void setLeElevationFt(String leElevationFt) {
        this.leElevationFt = leElevationFt;
    }

    public String getLeHeadingDegT() {
        return leHeadingDegT;
    }

    public void setLeHeadingDegT(String leHeadingDegT) {
        this.leHeadingDegT = leHeadingDegT;
    }

    public String getLeDisplacedThresholdFt() {
        return leDisplacedThresholdFt;
    }

    public void setLeDisplacedThresholdFt(String leDisplacedThresholdFt) {
        this.leDisplacedThresholdFt = leDisplacedThresholdFt;
    }

    public String getHeIdent() {
        return heIdent;
    }

    public void setHeIdent(String heIdent) {
        this.heIdent = heIdent;
    }

    public String getHeLatitudeDeg() {
        return heLatitudeDeg;
    }

    public void setHeLatitudeDeg(String heLatitudeDeg) {
        this.heLatitudeDeg = heLatitudeDeg;
    }

    public String getHeLongitudeDeg() {
        return heLongitudeDeg;
    }

    public void setHeLongitudeDeg(String heLongitudeDeg) {
        this.heLongitudeDeg = heLongitudeDeg;
    }

    public String getHeElevationFt() {
        return heElevationFt;
    }

    public void setHeElevationFt(String heElevationFt) {
        this.heElevationFt = heElevationFt;
    }

    public String getHeHeadingDegT() {
        return heHeadingDegT;
    }

    public void setHeHeadingDegT(String heHeadingDegT) {
        this.heHeadingDegT = heHeadingDegT;
    }

    public String getHeDisplacedThresholdFt() {
        return heDisplacedThresholdFt;
    }

    public void setHeDisplacedThresholdFt(String heDisplacedThresholdFt) {
        this.heDisplacedThresholdFt = heDisplacedThresholdFt;
    }
}
