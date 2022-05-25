package com.example.tourplanner.dataAccessLayer.database;

import com.example.tourplanner.models.TourModel;
import com.mongodb.MongoException;
import com.mongodb.client.*;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB implements IMongoDB {
    MongoClient client = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = client.getDatabase("TourPlanner");
    MongoCollection<Document> tours = database.getCollection("Tours");

    public void createDb(){
        //TODO manage mongodb imports, somehow not working sometimes
        //TODO make tourName unique

        //DB database = mongoClient.getDB("TheDatabaseName");

        /*Document tour = new Document("name", "test1").append("ages", new Document("min", 5));
        ObjectId id = tours.insertOne(tour).getInsertedId().asObjectId().getValue();


        Document test1 = tours.find(new Document("name", "test1")).first();
        System.out.println(test1.toJson());*/
    }

/*
    public void addTour(tourInfo tourInfo){
        Document tour = new Document()
                .append("tourName", tourInfo.tourName())
                .append("tourDescription", tourInfo.tourDescription())
                .append("tourFrom", tourInfo.tourFrom())
                .append("tourTo", tourInfo.tourTo())
                .append("tourTransportType", tourInfo.tourTransportType())
                .append("tourDistance", tourInfo.tourDistance())
                .append("ages", new Document("min", 5));
        ObjectId id = tours.insertOne(tour).getInsertedId().asObjectId().getValue();
    }
*/


    @Override
    public void addTour(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance) {
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

    @Override
    public ArrayList<TourModel> getTours(){
        ArrayList<TourModel> toursList = new ArrayList<>();

        tours.find().forEach(document ->  toursList.add(new TourModel(
                document.get("_id").toString(),
                document.get("tourName").toString(),
                document.get("tourDescription").toString(),
                document.get("tourFrom").toString(),
                document.get("tourTo").toString(),
                document.get("tourTransportType").toString(),
                document.get("tourDistance").toString())));

        getTour("CL-Final");

        return toursList;
    }

    @Override
    public void deleteTour(String item) {
        //tours.find().forEach(document -> System.out.println("TESTT " + document.get("tourName")));

        //document.remove("tourName", item)

        Bson query = eq("tourName", item);
        try {
            DeleteResult result = tours.deleteOne(query);
            System.out.println("Deleted document count: " + result.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }
    }



    public static IMongoDB getDatabase(){

         return new MongoDB();
    }

    @Override
    public TourModel getTour(String tourName){

            TourModel emptyTour = new TourModel("","","","","","","");
            Bson projectionFields = Projections.fields(
                    Projections.include("_id", "tourName", "tourDescription", "tourFrom", "tourTo", "tourTransportType", "tourDistance"),
                    Projections.excludeId());

            Document doc = tours.find(eq("tourName", tourName))
                    .projection(projectionFields)
                    .sort(Sorts.descending("tourName"))
                    .first();

            if (doc == null) {
                System.out.println("No results found.");
            } else {
                System.out.println("chose: " + doc.toJson());
                doc.values().stream().forEach(data -> System.out.println(data));
                TourModel tour = getValuesFromObject(doc);
                return tour;
            }
/*        ArrayList<TourModel> searchedTours = new ArrayList<>();
        tours.find().forEach(document ->  searchedTours.add(new TourModel(
                document.get("_id").toString(),
                document.get("tourName").toString(),
                document.get("tourDescription").toString(),
                document.get("tourFrom").toString(),
                document.get("tourTo").toString(),
                document.get("tourTransportType").toString(),
                document.get("tourDistance").toString())));
        searchedTours.stream().forEach( tour -> System.out.println("Searched: " + tour.getTourName()));
   */
        return emptyTour;
        //return new TourModel(doc.get(1).toString(), doc.get(2).toString(), doc.get(3).toString(), doc.get(4).toString(), doc.get(5).toString(), doc.get(6).toString(), doc.get(7).toString());
    }

    private TourModel getValuesFromObject(Document doc) {
        ArrayList<String> tourValues = new ArrayList<>();
        doc.values().stream().forEach(value -> tourValues.add(value.toString()));
        return new TourModel("", tourValues.get(0), tourValues.get(1), tourValues.get(2), tourValues.get(3), tourValues.get(4), tourValues.get(5));
    }


}

