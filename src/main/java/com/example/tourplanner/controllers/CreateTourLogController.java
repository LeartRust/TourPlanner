package com.example.tourplanner.controllers;

import com.example.tourplanner.viewmodel.CreateTourLogViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTourLogController implements Initializable {
    private final CreateTourLogViewModel viewModel = new CreateTourLogViewModel();

    @FXML
    private Button createTourLogButton;

    @FXML
    private TextField dateTime;
    @FXML
    private TextField comment;
    @FXML
    private TextField difficulty;
    @FXML
    private TextField  totalTime;
    @FXML
    private TextField rating;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateTime.textProperty().bindBidirectional(viewModel.getDateTime());
        comment.textProperty().bindBidirectional(viewModel.getComment());
        difficulty.textProperty().bindBidirectional(viewModel.getDifficulty());
        totalTime.textProperty().bindBidirectional(viewModel.getTotalTime());
        rating.textProperty().bindBidirectional(viewModel.getRating());
    }


    @FXML
    public void onCreateTourLogButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) createTourLogButton.getScene().getWindow();

        //TODO get tourName through title???
        viewModel.saveTourLog(stage.getTitle());
        stage.close();
    }
}
