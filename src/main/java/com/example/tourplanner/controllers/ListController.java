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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ListController implements Initializable {

    private Consumer<String> onSelectTour;


    @FXML
    private TextField searchfield;
    @FXML
    private ListView<TourModel> tourListView = new ListView<TourModel>();

    //MongoDB db = new MongoDB();
    static BusinessLogicLayer bl = new BusinessLogicLayer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //bl.importTours();
        addToList();

        //export
        /*try {
            bl.exportTours();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void addToList(){
        tourListView.getItems().clear();

        ArrayList<TourModel> toursList = bl.getTours();
        toursList.stream().forEach(tour -> tourListView.getItems().add(tour));
        tourListView.setCellFactory(new Callback<ListView<TourModel>, ListCell<TourModel>>() {
            @Override
            public ListCell<TourModel> call(ListView<TourModel> stringListView) {
                return new ButtonCell();
            }
        });

    }

    public void filterList(String searchName){
        tourListView.getItems().clear();

        ArrayList<TourModel> toursList = bl.getTours();
        toursList.stream().forEach(tour ->
        {
            if(searchName.isEmpty()==false){
                if(tour.getTourName().toLowerCase(Locale.ROOT).contains(searchName.toLowerCase(Locale.ROOT))){
                    tourListView.getItems().add(tour);
                }
            }else{
                tourListView.getItems().add(tour);
            }

        });
        tourListView.setCellFactory(new Callback<ListView<TourModel>, ListCell<TourModel>>() {
            @Override
            public ListCell<TourModel> call(ListView<TourModel> stringListView) {
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
        filterList(searchfield.getText());
    }

    public void handleMouseClick(MouseEvent mouseEvent) throws IOException {

       System.out.println("clicked on " + tourListView.getSelectionModel().getSelectedItem().getTourName());
       this.onSelectTour.accept(tourListView.getSelectionModel().getSelectedItem().getTourName());
        //DetailsController dc = new DetailsController();
        //detailsController.initData(tourListView.getSelectionModel().getSelectedItem());
   }

   public void subscribe(Consumer<String> consumer){
        this.onSelectTour=consumer;
   }

    public String getSelectedTour(){
        return tourListView.getSelectionModel().getSelectedItem().getTourName();
    }

    public void onSearchButtonClick(ActionEvent actionEvent) {
        filterList(searchfield.getText());
    }


    //Callback to add text and button to the list with an delete method
    static class ButtonCell extends ListCell<TourModel>{

        @Override
        public void updateItem(final TourModel item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox root = new HBox(10);
                root.setAlignment(Pos.CENTER_LEFT);
                root.setPadding(new Insets(5, 10, 5, 10));


                root.getChildren().add(new Label(item.getTourName()));

                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                root.getChildren().add(region);

                Button button = new Button("delete");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        bl.deleteTour(item.getTourName());
                        bl.deleteTourLogs(item.getTourName());
                    }
                });
                root.getChildren().add( button);
                setGraphic(root);
            }
        }

    }




}
