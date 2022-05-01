package com.example.tourplanner.database;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import org.bson.Document;

public class MongoDB {
    MongoClient client = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = client.getDatabase("TourPlanner");
    MongoCollection<Document> tours = database.getCollection("Tours");

    public void createDb(){
        //TODO manage mongodb imports, somehow not working sometimes

        //DB database = mongoClient.getDB("TheDatabaseName");

        /*Document tour = new Document("name", "test1").append("ages", new Document("min", 5));
        ObjectId id = tours.insertOne(tour).getInsertedId().asObjectId().getValue();


        Document test1 = tours.find(new Document("name", "test1")).first();
        System.out.println(test1.toJson());*/
    }

    public void addTour(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance){
        Document tour = new Document()
                .append("tourName", tourName)
                .append("tourDescription", tourDescription)
                .append("tourFrom", tourFrom)
                .append("tourTo", tourTo)
                .append("tourTransportType", tourTransportType)
                .append("tourDistance", tourDistance)
                .append("ages", new Document("min", 5));
        ObjectId id = tours.insertOne(tour).getInsertedId().asObjectId().getValue();
    }


}
