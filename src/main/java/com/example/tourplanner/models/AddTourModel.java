package com.example.tourplanner.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddTourModel {

    private final StringProperty tourName;
    private final StringProperty tourDescription;
    private final StringProperty tourFrom;
    private final StringProperty tourTo;
    private final StringProperty tourTransportType;
    private final StringProperty tourDistance;

    public AddTourModel(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance){
        this.tourName = new SimpleStringProperty(tourName);
        this.tourDescription = new SimpleStringProperty(tourDescription);
        this.tourFrom = new SimpleStringProperty(tourFrom);
        this.tourTo = new SimpleStringProperty(tourTo);
        this.tourTransportType = new SimpleStringProperty(tourTransportType);
        this.tourDistance = new SimpleStringProperty(tourDistance);
    }

    public String getTourName() {
        return tourName.get();
    }

    public StringProperty tourNameProperty() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName.set(tourName);
    }

    public String getTourDescription() {
        return tourDescription.get();
    }

    public StringProperty tourDescriptionProperty() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription.set(tourDescription);
    }

    public String getTourFrom() {
        return tourFrom.get();
    }

    public StringProperty tourFromProperty() {
        return tourFrom;
    }

    public void setTourFrom(String tourFrom) {
        this.tourFrom.set(tourFrom);
    }

    public String getTourTo() {
        return tourTo.get();
    }

    public StringProperty tourToProperty() {
        return tourTo;
    }

    public void setTourTo(String tourTo) {
        this.tourTo.set(tourTo);
    }

    public String getTourTransportType() {
        return tourTransportType.get();
    }

    public StringProperty tourTransportTypeProperty() {
        return tourTransportType;
    }

    public void setTourTransportType(String tourTransportType) {
        this.tourTransportType.set(tourTransportType);
    }

    public String getTourDistance() {
        return tourDistance.get();
    }

    public StringProperty tourDistanceProperty() {
        return tourDistance;
    }

    public void setTourDistance(String tourDistance) {
        this.tourDistance.set(tourDistance);
    }
}
