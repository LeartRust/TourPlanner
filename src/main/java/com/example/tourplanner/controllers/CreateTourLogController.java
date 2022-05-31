package com.example.tourplanner.controllers;

import com.example.tourplanner.viewmodel.CreateTourLogViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTourLogController implements Initializable {
    private final CreateTourLogViewModel viewModel = new CreateTourLogViewModel();

    @FXML
    private Button createTourLogButton;


    @FXML
    private TextField comment;
    @FXML
    private TextField difficulty;
    @FXML
    private TextField  totalTime;
    @FXML
    private TextField rating;
    @FXML
    private TextField distance;
    @FXML
    private Label errorDistanceLabel;
    @FXML
    private Label errorRatingLabel;
    @FXML
    private Label errorTimeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comment.textProperty().bindBidirectional(viewModel.getComment());
        difficulty.textProperty().bindBidirectional(viewModel.getDifficulty());
        totalTime.textProperty().bindBidirectional(viewModel.getTotalTime());
        rating.textProperty().bindBidirectional(viewModel.getRating());
        distance.textProperty().bindBidirectional(viewModel.getDistance());

        distance.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    distance.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        rating.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    rating.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        totalTime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    totalTime.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }



    @FXML
    public void onCreateTourLogButtonClick(ActionEvent actionEvent) {

        try {
            Integer.parseInt(distance.getText());
            Integer.parseInt(totalTime.getText());
            Integer.parseInt(rating.getText());
            Stage stage = (Stage) createTourLogButton.getScene().getWindow();

            //TODO get tourName through title???
            viewModel.saveTourLog(stage.getTitle());
            stage.close();
        } catch(NumberFormatException e){
            //TODO logger
            errorDistanceLabel.setVisible(true);
            errorTimeLabel.setVisible(true);
            errorRatingLabel.setVisible(true);
        }

    }
}
