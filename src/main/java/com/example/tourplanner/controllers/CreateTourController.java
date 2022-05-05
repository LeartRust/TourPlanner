package com.example.tourplanner.controllers;

import com.example.tourplanner.viewmodel.CreateTourViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTourController implements Initializable {
    private final CreateTourViewModel viewModel = new CreateTourViewModel();

    @FXML
    private TextField tourName;
    @FXML
    private TextField tourDescription;
    @FXML
    private TextField tourFrom;
    @FXML
    private TextField tourTo;
    @FXML
    private TextField  tourTransportType;
    @FXML
    private TextField tourDistance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(viewModel.getTourName());
        tourDescription.textProperty().bindBidirectional(viewModel.getTourDescription());
        tourFrom.textProperty().bindBidirectional(viewModel.getTourFrom());
        tourTo.textProperty().bindBidirectional(viewModel.getTourTo());
        tourTransportType.textProperty().bindBidirectional(viewModel.getTourTransportType());
        tourDistance.textProperty().bindBidirectional(viewModel.getTourDistance());
    }


    @FXML
    protected void onCreateTourButtonClick() {
        viewModel.saveTour();

    }

}