package com.example.tourplanner.businessLogic;

import com.example.tourplanner.dataAccessLayer.database.IMongoDB;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;
import com.example.tourplanner.models.TourModel;

import java.util.ArrayList;

public class BusinessLogicLayer {

    private final IMongoDB database;

    public void addTour(tourInfo tour){
       this.database.addTour(tour.tourName(), tour.tourDescription(), tour.tourFrom(), tour.tourTo(), tour.tourTransportType(), tour.tourDistance());
    }

    public ArrayList<TourModel> getTours(){
        return this.database.getTours();
    }

    public void deleteTour(String item) {
        this.database.deleteTour(item);
    }

    public BusinessLogicLayer(){
        this.database = MongoDB.getDatabase();
    }


    public TourModel getTour(String tour) {
        return this.database.getTour(tour);
    }
}
