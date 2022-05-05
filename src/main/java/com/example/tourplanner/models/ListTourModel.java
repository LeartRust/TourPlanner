package com.example.tourplanner.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListTourModel {
    private final StringProperty id;
    private final StringProperty tourName;


    public ListTourModel(String id, String tourName){
        this.id = new SimpleStringProperty(id);
        this.tourName = new SimpleStringProperty(tourName);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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
}
