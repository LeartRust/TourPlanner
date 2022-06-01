package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.main;
import com.example.tourplanner.reports.AllToursReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

@FXML
private DetailsController detailsController;

@FXML
private ListController listController;

    static BusinessLogicLayer bl = new BusinessLogicLayer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listController.subscribe(s->detailsController.initData(listController.getSelectedTour()));
    }

    public void onCreateSummarizeClick(ActionEvent actionEvent) {
        AllToursReport allReport = new AllToursReport();
        try {
            allReport.createPdf();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void onImportToursClick(ActionEvent actionEvent) throws IOException {
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/importTours.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 180);
        stage.setResizable(false);



        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle("Import Tours");
        stage.setScene(scene);
        stage.show();
    }

    public void onExportToursClick(ActionEvent actionEvent) throws IOException {
        bl.exportTours();
    }

    public void onImportTourLogsClick(ActionEvent actionEvent) throws IOException {
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/importTourLogs.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 180);
        stage.setResizable(false);



        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle("Import Tour Logs");
        stage.setScene(scene);
        stage.show();
    }

    public void onExportTourLogsClick(ActionEvent actionEvent) throws IOException {
        bl.exportTourLogs();
    }
}
