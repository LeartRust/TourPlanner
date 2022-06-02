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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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


    public static void main(String[] args) throws IOException {

        logger.debug("This is a debug message.");
        logger.fatal("This is a fatal message.");
        logger.warn("This is a warning message.");
        logger.error("This is an error message.");

/*
        URL url = new URL("https://www.mapquestapi.com/staticmap/v5/map?key=IwA2M7w326xoylLFZ6PHlaIGdfGS5Ktg&size=@2x");

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "src/main/java/com/example/tourplanner/file.json");

        String data = "{\n  \"location\": \"Broadway, New York\",\n  \"options\": {\n    \"thumbMaps\": true\n  }\n}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
*/


        launch();
    }
}