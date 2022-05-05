package com.example.tourplanner.database;

import com.example.tourplanner.models.ListTourModel;
import com.example.tourplanner.models.TourModel;
import com.mongodb.client.*;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

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
        System.out.println("ADD TOUR");
        Document tour = new Document()
                .append("tourName", tourName)
                .append("tourDescription", tourDescription)
                .append("tourFrom", tourFrom)
                .append("tourTo", tourTo)
                .append("tourTransportType", tourTransportType)
                .append("tourDistance", tourDistance)
                .append("ages", new Document("min", 5));
        ObjectId id = tours.insertOne(tour).getInsertedId().asObjectId().getValue();
        getTours();

    }

    public ArrayList<TourModel> getTours(){
        ArrayList<TourModel> toursList = new ArrayList<>();

        MongoCursor<Document> cursor = tours.find().cursor();
        //TODO refactor!
        while(cursor.hasNext())
        {
            ArrayList<String> list = new ArrayList();
            cursor.next().values().stream().forEach(val -> list.add(val.toString()));
            toursList.add(new TourModel(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)));
        }

        return toursList;
    }
}
