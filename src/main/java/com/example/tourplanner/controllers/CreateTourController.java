package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.models.TourModel;
import com.example.tourplanner.viewmodel.CreateTourViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateTourController implements Initializable {

    private final CreateTourViewModel viewModel = new CreateTourViewModel();
    @FXML
    private Button createTourButton;
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
    @FXML
    private Label errorUniqueTourname;
    static BusinessLogicLayer bl = new BusinessLogicLayer();

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
        AtomicBoolean isUsed = new AtomicBoolean(false);

        if(tourName.getText().isBlank() || tourDescription.getText().isBlank() || tourFrom.getText().isBlank() || tourTo.getText().isBlank() || tourTransportType.getText().isBlank() || tourDistance.getText().isBlank()){
            errorEmptyFields.setVisible(true);
        }else{
            ArrayList<TourModel> toursList = bl.getTours();
            toursList.stream().forEach(tour ->
                    {
                         if(tour.getTourName().equals(tourName.getText())){
                             isUsed.set(true);
                         }
                    });
            if(isUsed.get()==false){
                viewModel.saveTour();
                Stage stage = (Stage) createTourButton.getScene().getWindow();
                stage.close();
            }else{
                errorUniqueTourname.setVisible(true);
            }
        }
    }

}