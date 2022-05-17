package com.example.tourplanner.businessLogic;

import com.example.tourplanner.dataAccessLayer.database.IMongoDB;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;

public class BusinessLogicLayer {

    private final IMongoDB database;

    public void addTour(tourInfo tour){
       this.database.addTour(tour.tourName(), tour.tourDescription(), tour.tourFrom(), tour.tourTo(), tour.tourTransportType(), tour.tourDistance());
    }

    public BusinessLogicLayer(){
        this.database = MongoDB.getDatabase();
    }
}
