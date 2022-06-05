package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import com.example.tourplanner.main;
import com.example.tourplanner.models.TourModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ListController implements Initializable {

    private Consumer<String> onSelectTour;
    @FXML
    private  ToggleButton FavoriteFilterButton;
    @FXML
    private TextField searchfield;
    @FXML
    private ListView<TourModel> tourListView = new ListView<TourModel>();
    @FXML
    private Button refreshButton;
    static BusinessLogicLayer bl = new BusinessLogicLayer();
    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addToList();
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
                if(FavoriteFilterButton.isSelected()==true){
                    if(tour.getTourName().toLowerCase(Locale.ROOT).contains(searchName.toLowerCase(Locale.ROOT)) || tour.getIsFavorite().equals("true")){
                        tourListView.getItems().add(tour);
                    }
                }else{
                    if(tour.getTourName().toLowerCase(Locale.ROOT).contains(searchName.toLowerCase(Locale.ROOT))){
                        tourListView.getItems().add(tour);
                    }
                }

            }else if(FavoriteFilterButton.isSelected()==true){
                if(tour.getIsFavorite().equals("true")){
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
    protected void onAddTourButtonClick(){
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/createTour.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1600, 900);
        } catch (IOException e) {
            logger.error("ListController.java onAddTourButtonClick IOException");
            throw new RuntimeException(e);
        }
        stage.setResizable(false);

        stage.setTitle("Create Tour");
        stage.setScene(scene);
        stage.show();

        //Updates the ListView after the AddWindow is finished
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        addToList();
                        filterList(searchfield.getText());
                    }
                });
            }
        });
    }

    public void onRefreshButtonClick(ActionEvent actionEvent) {
        addToList();
        filterList(searchfield.getText());
    }

    public void handleMouseClick(MouseEvent mouseEvent){
       this.onSelectTour.accept(tourListView.getSelectionModel().getSelectedItem().getTourName());
   }

    public void subscribe(Consumer<String> consumer){
        this.onSelectTour=consumer;
   }

    public TourModel getSelectedTour(){
        return tourListView.getSelectionModel().getSelectedItem();
    }

    public void onSearchButtonClick(ActionEvent actionEvent) {
        filterList(searchfield.getText());
    }

    public void onFavoriteFilterButtonClick(ActionEvent actionEvent) {
                if(FavoriteFilterButton.isSelected() == true){
                    ImageView starimg = new ImageView("https://cdn-icons-png.flaticon.com/512/1828/1828614.png");
                    starimg.setFitHeight(15);
                    starimg.setFitWidth(15);
                    FavoriteFilterButton.setGraphic(starimg);
                }else if(FavoriteFilterButton.isSelected() == false){
                    ImageView starimg = new ImageView("https://cdn-icons-png.flaticon.com/512/1828/1828970.png");
                    starimg.setFitHeight(15);
                    starimg.setFitWidth(15);
                    FavoriteFilterButton.setGraphic(starimg);
                }
    }

    //Callback to add text and buttons to the list with own methods
    class ButtonCell extends ListCell<TourModel>{

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


                Button buttonFavorite = new Button();

                if(item.getIsFavorite().equals("false")){
                    ImageView starimg = new ImageView("https://cdn-icons-png.flaticon.com/512/1828/1828970.png");
                    starimg.setFitHeight(15);
                    starimg.setFitWidth(15);
                    buttonFavorite.setGraphic(starimg);
                }else{
                    ImageView starimg = new ImageView("https://cdn-icons-png.flaticon.com/512/1828/1828614.png");
                    starimg.setFitHeight(15);
                    starimg.setFitWidth(15);
                    buttonFavorite.setGraphic(starimg);
                }

                buttonFavorite.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        if(item.getIsFavorite().equals("false")){
                            item.setIsFavorite("true");
                            bl.changeFavorite(item.getTourId(), item.getIsFavorite());

                            ImageView changeImg = new ImageView("https://cdn-icons-png.flaticon.com/512/1828/1828614.png");

                            changeImg.setFitHeight(15);
                            changeImg.setFitWidth(15);
                            System.out.println(item.getIsFavorite());
                            buttonFavorite.setGraphic(changeImg);
                        }else{
                            item.setIsFavorite("false");
                            bl.changeFavorite(item.getTourId(), item.getIsFavorite());

                            ImageView changeImg = new ImageView("https://cdn-icons-png.flaticon.com/512/1828/1828970.png");

                            changeImg.setFitHeight(15);
                            changeImg.setFitWidth(15);
                            System.out.println(item.getIsFavorite());
                            buttonFavorite.setGraphic(changeImg);
                        }

                    }
                });
                root.getChildren().add( buttonFavorite);


                Button buttonEdit = new Button("Edit");
                buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {

                        final Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/editTour.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 1600, 900);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.setResizable(false);

                        stage.setTitle("Edit Tour");
                        stage.setScene(scene);
                        EditTourController controller = fxmlLoader.getController();
                        controller.initData(item);
                        stage.show();

                        //Updates the ListView after the EditWindow is finished
                        stage.setOnHiding(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        addToList();
                                        filterList(searchfield.getText());
                                    }
                                });
                            }
                        });


                    }
                });
                root.getChildren().add( buttonEdit);


                Button buttonDelete = new Button("delete");
                buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        bl.deleteTour(item.getTourName());
                        bl.deleteTourLogs(item.getTourName());
                        addToList();
                        filterList(searchfield.getText());
                    }
                });
                root.getChildren().add( buttonDelete);
                setGraphic(root);
            }
        }

    }
}
