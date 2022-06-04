package com.example.tourplanner.controllers;

import com.example.tourplanner.models.TourModel;
import com.example.tourplanner.viewmodel.CreateTourViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {
    private final CreateTourViewModel viewModel = new CreateTourViewModel();

    @FXML
    private Button editTourButton;
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
    private Label errorEmptyFields;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(viewModel.getTourName());
        tourDescription.textProperty().bindBidirectional(viewModel.getTourDescription());
        tourFrom.textProperty().bindBidirectional(viewModel.getTourFrom());
        tourTo.textProperty().bindBidirectional(viewModel.getTourTo());
        tourTransportType.textProperty().bindBidirectional(viewModel.getTourTransportType());
        tourDistance.textProperty().bindBidirectional(viewModel.getTourDistance());
    }

    public void initData(TourModel Item) {

      viewModel.id = Item.getTourId();
      tourName.setText(Item.getTourName());
      tourDescription.setText(Item.getTourDescription());
      tourFrom.setText(Item.getTourFrom());
      tourTo.setText(Item.getTourTo());
      tourTransportType.setText(Item.getTourTransportType());
      tourDistance.setText(Item.getTourDistance());
    }


    @FXML
    protected void onEditTourButtonClick() {

        if(tourName.getText().isBlank() || tourDescription.getText().isBlank() || tourFrom.getText().isBlank() || tourTo.getText().isBlank() || tourTransportType.getText().isBlank() || tourDistance.getText().isBlank()){
            errorEmptyFields.setVisible(true);
        }else{
            viewModel.EditTour();
            Stage stage = (Stage) editTourButton.getScene().getWindow();
            stage.close();
        }


    }

}