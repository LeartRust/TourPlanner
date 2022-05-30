package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.reports.DetailsReport;
import com.example.tourplanner.viewmodel.CreateTourViewModel;
import com.example.tourplanner.viewmodel.DetailsViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    private final DetailsViewModel viewModel = new DetailsViewModel();

    @FXML
    private Label tourName;
    @FXML
    private Label tourDescription;
    @FXML
    private Label tourFrom;
    @FXML
    private Label tourTo;
    @FXML
    private Label  tourTransportType;
    @FXML
    private Label tourDistance;

    static BusinessLogicLayer bl = new BusinessLogicLayer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(viewModel.getTourName());
        //tourName.setText("test");
        tourDescription.textProperty().bindBidirectional(viewModel.getTourDescription());
        tourFrom.textProperty().bindBidirectional(viewModel.getTourFrom());
        tourTo.textProperty().bindBidirectional(viewModel.getTourTo());
        tourTransportType.textProperty().bindBidirectional(viewModel.getTourTransportType());
        tourDistance.textProperty().bindBidirectional(viewModel.getTourDistance());

    }

    public void initData(String selectedItem) {
        viewModel.setTourDetails(selectedItem);
        //tourName.setText("test2");
        System.out.println("TEST");
    }

    public void onReportButtonClick(ActionEvent actionEvent) {
        DetailsReport report = new DetailsReport();
        try {
            report.createPdf(tourName.getText(), tourDescription.getText(), tourFrom.getText(), tourTo.getText(), tourTransportType.getText(), tourDistance.getText());
            //report.createPdf("Trip to Madrid", "this is the tour description", "Vienna", "Madrid", "Plane", "1000km");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onFileDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            System.out.println(db.getFiles().toString());
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    public void onFileDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        bl.importTours();
        event.consume();
    }

    public void onAddTourlogButtonClick(ActionEvent actionEvent) {
    }

    public void onRefreshButtonClick(ActionEvent actionEvent) {
    }
}
