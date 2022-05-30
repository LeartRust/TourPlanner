package com.example.tourplanner.businessLogic;

import com.example.tourplanner.dataAccessLayer.database.IMongoDB;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.dataAccessLayer.database.loginfo;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;
import com.example.tourplanner.models.TourModel;

import java.io.IOException;
import java.util.ArrayList;

public class BusinessLogicLayer {

    private final IMongoDB database;

    public BusinessLogicLayer(){
        this.database = MongoDB.getDatabase();
    }

    // Tour Methods
    public void addTour(tourInfo tour){
       this.database.addTour(tour.tourName(), tour.tourDescription(), tour.tourFrom(), tour.tourTo(), tour.tourTransportType(), tour.tourDistance());
    }

    public TourModel getTour(String tour) {
        return this.database.getTour(tour);
    }

    public ArrayList<TourModel> getTours(){
        return this.database.getTours();
    }

    public void deleteTour(String item) {
        this.database.deleteTour(item);
    }

    public void exportTours() throws IOException {
        this.database.exportTours();
    };

    public void importTours(){
        this.database.importTours();
    }

    // TourLog methods

    public void addTourLog(loginfo log){
        this.database.addTourLog(log.tourName(), log.dateTime(), log.comment(), log.difficulty(), log.totalTime(), log.rating());
    }
    public void deleteTourLog(String item) {
        this.database.deleteTourLog(item);
    }


}
