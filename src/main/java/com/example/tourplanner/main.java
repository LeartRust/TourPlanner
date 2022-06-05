package com.example.tourplanner;

import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class main extends Application {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    @Override
    public void start(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/mainView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1600, 900);
        } catch (IOException e) {
            logger.error("main.java start IOException");
            throw new RuntimeException(e);
        }
        stage.setResizable(false);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
/*
        logger.debug("This is a debug message.");
        logger.fatal("This is a fatal message.");
        logger.warn("This is a warning message.");
        logger.error("This is an error message.");
*/

        launch();
    }
}