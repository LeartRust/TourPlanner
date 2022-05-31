package com.example.tourplanner.controllers;

import com.example.tourplanner.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

@FXML
private DetailsController detailsController;

@FXML
private ListController listController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listController.subscribe(s->detailsController.initData(listController.getSelectedTour()));
    }
}
