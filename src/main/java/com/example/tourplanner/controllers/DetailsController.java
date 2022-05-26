package com.example.tourplanner.controllers;

import com.example.tourplanner.viewmodel.CreateTourViewModel;
import com.example.tourplanner.viewmodel.DetailsViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
}
