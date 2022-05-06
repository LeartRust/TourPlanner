package com.example.tourplanner.businessLogic;

import com.example.tourplanner.dataAccessLayer.database.IMongoDB;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;

public class BusinessLogicLayer {

    private final IMongoDB database;

    public void addTour(){
       this.database.addTour(new tourInfo());
    }

    public BusinessLogicLayer(){
        this.database = MongoDB.getDatabase();
    }
}
