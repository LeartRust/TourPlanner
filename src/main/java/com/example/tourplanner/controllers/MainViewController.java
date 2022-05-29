package com.example.tourplanner.controllers;

import com.example.tourplanner.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainViewController {

@FXML
private DetailsController detailsController;

@FXML
private ListController listController;

 public void test(String test1){
     detailsController.initData("Test1q");
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        //listController.getSelectedTour();
        detailsController.initData(listController.getSelectedTour());
    }
}
