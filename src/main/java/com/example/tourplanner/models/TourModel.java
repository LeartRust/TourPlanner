package com.example.tourplanner.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TourModel {
    private String tourId;
    private String tourName;
    private String tourDescription;
    private String tourFrom;
    private String tourTo;
    private String tourTransportType;
    private String tourDistance;

    private  String isFavorite;

    public TourModel(String tourId, String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance, String isFavorite){
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourFrom = tourFrom;
        this.tourTo = tourTo;
        this.tourTransportType = tourTransportType;
        this.tourDistance = tourDistance;
        this.isFavorite = isFavorite;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getTourFrom() {
        return tourFrom;
    }

    public void setTourFrom(String tourFrom) {
        this.tourFrom = tourFrom;
    }

    public String getTourTo() {
        return tourTo;
    }

    public void setTourTo(String tourTo) {
        this.tourTo = tourTo;
    }

    public String getTourTransportType() {
        return tourTransportType;
    }

    public void setTourTransportType(String tourTransportType) {
        this.tourTransportType = tourTransportType;
    }

    public String getTourDistance() {
        return tourDistance;
    }

    public void setTourDistance(String tourDistance) {
        this.tourDistance = tourDistance;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }
}
