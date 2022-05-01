package com.example.tourplanner.controllers;

import com.example.tourplanner.viewmodel.CreateTourViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateTourController {
    private final CreateTourViewModel viewModel = new CreateTourViewModel();

    @FXML
    private Label welcomeText;
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

    @FXML
    protected void onCreateTourButtonClick() {
        viewModel.saveTour();

    }

}