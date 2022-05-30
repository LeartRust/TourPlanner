package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.main;
import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;
import com.example.tourplanner.reports.DetailsReport;
import com.example.tourplanner.viewmodel.CreateTourViewModel;
import com.example.tourplanner.viewmodel.DetailsViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    private ListView<TourLogModel> tourLogListView = new ListView<TourLogModel>();

    static BusinessLogicLayer bl = new BusinessLogicLayer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(viewModel.getTourName());
        //tourName.setText("test");
        tourDescription.textProperty().bindBidirectional(viewModel.getTourDescription());
        tourFrom.textProperty().bindBidirectional(viewModel.getTourFrom());
        tourTo.textProperty().bindBidirectional(viewModel.getTourTo());
        tourTransportType.textProperty().bindBidirectional(viewModel.getTourTransportType());
        tourDistance.textProperty().bindBidirectional(viewModel.getTourDistance());

        addToList();
    }

    public void initData(String selectedItem) {
        viewModel.setTourDetails(selectedItem);
        //tourName.setText("test2");
        System.out.println("TEST");
    }

    public void onReportButtonClick(ActionEvent actionEvent) {
        DetailsReport report = new DetailsReport();
        try {
            report.createPdf(tourName.getText(), tourDescription.getText(), tourFrom.getText(), tourTo.getText(), tourTransportType.getText(), tourDistance.getText());
            //report.createPdf("Trip to Madrid", "this is the tour description", "Vienna", "Madrid", "Plane", "1000km");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onFileDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            System.out.println(db.getFiles().toString());
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);

        event.consume();
    }

    public void onFileDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        bl.importTours();
        event.consume();
    }

    public void addToList(){
        tourLogListView.getItems().clear();

        ArrayList<TourLogModel> tourLogsList = bl.getTourLogs();
        tourLogsList.stream().forEach(tourLog -> tourLogListView.getItems().add(tourLog));
        tourLogListView.setCellFactory(new Callback<ListView<TourLogModel>, ListCell<TourLogModel>>() {
            @Override
            public ListCell<TourLogModel> call(ListView<TourLogModel> stringListView) {
                return new ButtonCell();
            }
        });

    }

    public void onAddTourLogButtonClick(ActionEvent actionEvent) throws IOException {
        final Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("views/createTourLogView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setResizable(false);

        //TODO set Max width to Screen
        //stage.setMaxWidth(Screen);
        stage.setTitle(tourName.toString());
        stage.setScene(scene);
        stage.show();
    }



    public void onRefreshButtonClick(ActionEvent actionEvent) {
        addToList();
    }

    static class ButtonCell extends ListCell<TourLogModel>{
        @Override
        public void updateItem(final TourLogModel item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {

                HBox root = new HBox(10);
                root.setAlignment(Pos.CENTER_LEFT);
                root.setPadding(new Insets(5, 10, 5, 10));

                Label dateTimeLabel = new Label("Date/Time:");
                dateTimeLabel.setStyle("-fx-font-weight: bold;");
                root.getChildren().add(dateTimeLabel);
                root.getChildren().add(new Label(item.getTourName()));

                Label commentLabel = new Label("Comment:");
                commentLabel.setStyle("-fx-font-weight: bold;");
                root.getChildren().add(commentLabel);
                root.getChildren().add(new Label(item.getComment()));

                Label difficultyLabel = new Label("Difficulty:");
                difficultyLabel.setStyle("-fx-font-weight: bold;");
                root.getChildren().add(difficultyLabel);
                root.getChildren().add(new Label(item.getDifficulty()));

                Label totalTimeLabel = new Label("Total time:");
                totalTimeLabel.setStyle("-fx-font-weight: bold;");
                root.getChildren().add(totalTimeLabel);
                root.getChildren().add(new Label(item.getTotalTime()));

                Label ratingLabel = new Label("Rating:");
                ratingLabel.setStyle("-fx-font-weight: bold;");
                root.getChildren().add(ratingLabel);
                root.getChildren().add(new Label(item.getRating()));

                Region region = new Region();
                HBox.setHgrow(region, Priority.ALWAYS);
                root.getChildren().add(region);

                Button button = new Button("delete");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
                        bl.deleteTour(item.getTourName());
                    }
                });
                root.getChildren().add( button);
                setGraphic(root);
            }
        }

    }
}
