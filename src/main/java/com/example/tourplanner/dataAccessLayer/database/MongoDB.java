package com.example.tourplanner.dataAccessLayer.database;

import com.example.tourplanner.models.TourModel;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.*;

import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB implements IMongoDB {

    Properties prop=new Properties();

    FileInputStream ip;

    {
        try {
            ip = new FileInputStream("src/main/resources/com/example/tourplanner/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    MongoClient client = MongoClients.create(prop.getProperty("connection"));
    MongoDatabase database = client.getDatabase(prop.getProperty("databaseName"));
    MongoCollection<Document> tours = database.getCollection(prop.getProperty("collectionName"));

    public void createDb(){
        //TODO manage mongodb imports, somehow not working sometimes
        //TODO make tourName unique https://www.educba.com/mongodb-unique/

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

    public void exportTours() throws IOException {
        Runtime.getRuntime().exec("mongoexport --host localhost --port 27017 --db TourPlanner --collection Tours --out ../TourPlanner/src/main/resources/com/example/tourplanner/exports/Tours.json");
    }

    public void importTours(){
        try {
            //drop previous import
            tours.drop();

            //Bulk Approach:
            int count = 0;
            int batch = 100;
            List<InsertOneModel<Document>> docs = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/tourplanner/dataAccessLayer/database/Tours.json"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    docs.add(new InsertOneModel<>(Document.parse(line)));
                    System.out.println(docs.toString());
                    count++;
                    if (count == batch) {
                        tours.bulkWrite(docs, new BulkWriteOptions().ordered(false));
                        docs.clear();
                        count = 0;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (count > 0) {
                tours.bulkWrite(docs, new BulkWriteOptions().ordered(false));
            }

        } catch (MongoWriteException e) {
            System.out.println("Error");
        }

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

