package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
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
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private DetailsController detailsController;

    @FXML
    private ListController listController;

    static BusinessLogicLayer bl = new BusinessLogicLayer();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listController.subscribe(s->detailsController.initData(listController.getSelectedTour()));
    }

    public void onCreateSummarizeClick(ActionEvent actionEvent) {
        AllToursReport allReport = new AllToursReport();
            allReport.createPdf();
    }

    public void onImportToursClick(ActionEvent actionEvent){
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/importTours.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 180);
        } catch (IOException e) {
            logger.error("MainViewController.java onImportToursClick IOException");
            throw new RuntimeException(e);
        }
        stage.setResizable(false);
        stage.setTitle("Import Tours");
        stage.setScene(scene);
        stage.show();
    }

    public void onExportToursClick(ActionEvent actionEvent){
        bl.exportTours();
    }

    public void onImportTourLogsClick(ActionEvent actionEvent){
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/importTourLogs.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 180);
        } catch (IOException e) {
            logger.error("MainViewController.java onImportTourLogsClick IOException");
            throw new RuntimeException(e);
        }
        stage.setResizable(false);
        stage.setTitle("Import Tour Logs");
        stage.setScene(scene);
        stage.show();
    }

    public void onExportTourLogsClick(ActionEvent actionEvent){
        bl.exportTourLogs();
    }
}
