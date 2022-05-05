package com.example.tourplanner.controllers;

import com.example.tourplanner.database.MongoDB;
import com.example.tourplanner.main;
import com.example.tourplanner.models.TourModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    private ListView tourListView;

    MongoDB db = new MongoDB();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addToList();
    }

    public void addToList(){
        tourListView.getItems().clear();
        ArrayList<TourModel> toursList = db.getTours();
        toursList.stream().forEach(tour -> tourListView.getItems().add(tour.getTourName()));
    }

    @FXML
    protected void onAddTourButtonClick() throws IOException {
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/createTour.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setResizable(false);
        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();
    }

    public void onRefreshButtonClick(ActionEvent actionEvent) {
        addToList();
    }
}
