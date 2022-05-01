package com.example.tourplanner.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateTourModel {


    private final StringProperty tourName;
    private final StringProperty tourDescription;
    private final StringProperty tourFrom;
    private final StringProperty tourTo;
    private final StringProperty tourTransportType;
    private final StringProperty tourDistance;

    public CreateTourModel(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance){
        this.tourName = new SimpleStringProperty(tourName);
        this.tourDescription = new SimpleStringProperty(tourDescription);
        this.tourFrom = new SimpleStringProperty(tourFrom);
        this.tourTo = new SimpleStringProperty(tourTo);
        this.tourTransportType = new SimpleStringProperty(tourTransportType);
        this.tourDistance = new SimpleStringProperty(tourDistance);
    }

}
