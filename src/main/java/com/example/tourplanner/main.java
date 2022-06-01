package com.example.tourplanner;

import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class main extends Application {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger();
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

        logger.debug("This is a debug message.");
        logger.fatal("This is a fatal message.");
        logger.warn("This is a warning message.");
        logger.error("This is an error message.");

        launch();
    }
}