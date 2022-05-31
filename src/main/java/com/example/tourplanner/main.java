package com.example.tourplanner;

import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setResizable(false);
        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
//        DetailsReport report = new DetailsReport();
//        try {
//            report.createPdf();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Logger logger = LogManager.getLogger(main.class.getName());
        logger.error("erroor");

        launch();
    }
}