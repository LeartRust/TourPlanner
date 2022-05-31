package com.example.tourplanner.viewmodel;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.loginfo;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class CreateTourLogViewModel {

    private final StringProperty dateTime = new SimpleStringProperty("");
    private final StringProperty comment = new SimpleStringProperty("");
    private final StringProperty difficulty = new SimpleStringProperty("");
    private final StringProperty totalTime = new SimpleStringProperty("");
    private final StringProperty rating = new SimpleStringProperty("");
    private final StringProperty distance = new SimpleStringProperty("");

    public StringProperty getDateTime() {
        return dateTime;
    }
    public StringProperty getComment() {
        return comment;
    }
    public StringProperty getDifficulty() {
        return difficulty;
    }
    public StringProperty getTotalTime() {
        return totalTime;
    }
    public StringProperty getRating() {
        return rating;
    }
    public StringProperty getDistance() {
        return distance;
    }


    public void saveTourLog(String tourName){

        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTourLog(new loginfo(tourName, dateTime.get(), comment.get(), difficulty.get(), totalTime.get(), rating.get(), distance.get()));
        dateTime.set("");
        comment.set("");
        difficulty.set("");
        totalTime.set("");
        rating.set("");
        distance.set("");
    }
}
