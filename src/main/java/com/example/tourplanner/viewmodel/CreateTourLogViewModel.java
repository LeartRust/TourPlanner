package com.example.tourplanner.viewmodel;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.logEditinfo;
import com.example.tourplanner.dataAccessLayer.database.loginfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Date;

public class CreateTourLogViewModel {

    private String id;
    private final StringProperty comment = new SimpleStringProperty("");
    private final StringProperty difficulty = new SimpleStringProperty("");
    private final StringProperty totalTime = new SimpleStringProperty("");
    private final StringProperty rating = new SimpleStringProperty("");
    private final StringProperty distance = new SimpleStringProperty("");

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
    public void setId(String id){this.id=id;}
    public String getId(){return id;}


    public void saveTourLog(String tourName){

        Date date = new Date(System.currentTimeMillis());

        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTourLog(new loginfo(tourName, date.toString(), comment.get(), difficulty.get(), totalTime.get(), rating.get(), distance.get()));
        comment.set("");
        difficulty.set("");
        totalTime.set("");
        rating.set("");
        distance.set("");
    }

    public void editTourLog(String tourName){

        Date date = new Date(System.currentTimeMillis());

        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.editTourLog(new logEditinfo(id, tourName, date.toString(), comment.get(), difficulty.get(), totalTime.get(), rating.get(), distance.get()));
        comment.set("");
        difficulty.set("");
        totalTime.set("");
        rating.set("");
        distance.set("");
    }
}
