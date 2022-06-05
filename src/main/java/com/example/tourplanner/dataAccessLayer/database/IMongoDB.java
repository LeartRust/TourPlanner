package com.example.tourplanner.dataAccessLayer.database;

import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;

import java.io.IOException;
import java.util.ArrayList;

public interface IMongoDB {

    //Tour Methods
    void addTour(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance);

    ArrayList<TourModel> getTours();

    void deleteTour(String item);

    TourModel getTourByName(String tourName);
    TourModel getTourById(String tour);

    void exportTours() throws IOException;

    void exportTourLogs() throws IOException;

    void importTours(String path);

    void changeFavorite(String id, String isFavorite);

    
    //TourLog Methods
    void addTourLog(String tourName, String dateTime, String comment, String difficulty, String totalTime, String rating, String distance);

    void deleteTourLog(String item);

    ArrayList<TourLogModel> getTourLogs();

    void deleteTourLogs(String item);

    ArrayList<TourLogModel> getTourLogsByTourName(String tourName);

    void editTour(String id, String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance);

    void editTourLog(String id, String tourName, String dateTime, String comment, String difficulty, String totalTime, String rating, String distance);

    void importTourLogs(String path);

    void editManyTourLogs(String oldTourName, String newTourName);

}
