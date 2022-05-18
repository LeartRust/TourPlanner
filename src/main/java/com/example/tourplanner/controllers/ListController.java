package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.main;
import com.example.tourplanner.models.TourModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    private ListView<String> tourListView = new ListView<String>();

    //MongoDB db = new MongoDB();
    static BusinessLogicLayer bl = new BusinessLogicLayer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addToList();
    }

    public void addToList(){
        tourListView.getItems().clear();

        ArrayList<TourModel> toursList = bl.getTours();
        toursList.stream().forEach(tour -> tourListView.getItems().add(tour.getTourName()));
        tourListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ButtonCell();
            }
        });

    }

    @FXML
    protected void onAddTourButtonClick() throws IOException {
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/createTour.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setResizable(false);
        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle("Tour-Planner");
        stage.setScene(scene);
        stage.show();
    }

    public void onRefreshButtonClick(ActionEvent actionEvent) {
        addToList();
    }



    //Callback to add text and button to the list with an delete method
    static class ButtonCell extends ListCell<String>{

        @Override
        public void updateItem(final String item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox root = new HBox(10);
                root.setAlignment(Pos.CENTER_LEFT);
                root.setPadding(new Insets(5, 10, 5, 10));


                root.getChildren().add(new Label(item));

                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                root.getChildren().add(region);

                Button button = new Button("delete");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        bl.deleteTour(item);
                    }
                });
                root.getChildren().add( button);
                setGraphic(root);
            }
        }

    }




}
