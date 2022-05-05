package com.example.tourplanner;

import com.example.tourplanner.database.MongoDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/createTour.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setResizable(false);
        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();
        //TODO Database!
        MongoDB dbTest = new MongoDB();
        dbTest.createDb();
    }


    public static void main(String[] args) {
        launch();
    }
}