package com.example.tourplanner.businessLogic;

import com.example.tourplanner.dataAccessLayer.database.*;
import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;
import java.io.IOException;
import java.util.ArrayList;

public class BusinessLogicLayer {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger();
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

    public void exportTours(){
        try {
            this.database.exportTours();
        } catch (IOException e) {
            logger.error("BusinessLogicLayer.java exportTours IOException");
            throw new RuntimeException(e);
        }
    };

    public void exportTourLogs(){
        try {
            this.database.exportTourLogs();
        } catch (IOException e) {
            logger.error("BusinessLogicLayer.java exportTourLogs IOException");
            throw new RuntimeException(e);
        }
    };

    public void importTours(String path){
        this.database.importTours(path);
    }

    public void editTour(tourEditInfo tour){
        this.database.editTour(tour.id(), tour.tourName(), tour.tourDescription(), tour.tourFrom(), tour.tourTo(), tour.tourTransportType(), tour.tourDistance());
    }

    public void changeFavorite(String id, String isFavorite) {this.database.changeFavorite(id, isFavorite);}


    // TourLog methods
    public void addTourLog(loginfo log){
        this.database.addTourLog(log.tourName(), log.dateTime(), log.comment(), log.difficulty(), log.totalTime(), log.rating(), log.distance());
    }

    public void deleteTourLog(String item) {
        this.database.deleteTourLog(item);
    }

    public ArrayList<TourLogModel> getTourLogs() {
        return this.database.getTourLogs();
    }

    public ArrayList<TourLogModel> getTourLogsByTourName(String tourName) {
        return this.database.getTourLogsByTourName(tourName);
    }

    public void deleteTourLogs(String item) {
        this.database.deleteTourLogs(item);
    }

    public void importTourLogs(String path){
        this.database.importTourLogs(path);
    }

    public void editTourLog(logEditinfo log){
        this.database.editTourLog(log.id(), log.tourName(), log.dateTime(), log.comment(), log.difficulty(), log.totalTime(), log.rating(), log.distance());
    }


    //If a Tour is changed this method is called to change the TourName reference in all TourLogs of the edited Tour
    public void editManyTourLog(String oldTourName, String newTourName){
        this.database.editManyTourLogs(oldTourName, newTourName);
    }
}
