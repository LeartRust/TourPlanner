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

    public TourModel(String tourId, String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance){
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourFrom = tourFrom;
        this.tourTo = tourTo;
        this.tourTransportType = tourTransportType;
        this.tourDistance = tourDistance;
    }

}
