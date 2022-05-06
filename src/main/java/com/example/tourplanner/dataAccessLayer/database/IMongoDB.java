package com.example.tourplanner.dataAccessLayer.database;

import com.example.tourplanner.models.TourModel;

import java.util.ArrayList;

public interface IMongoDB {
    void addTour(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance);

    ArrayList<TourModel> getTours();
}
