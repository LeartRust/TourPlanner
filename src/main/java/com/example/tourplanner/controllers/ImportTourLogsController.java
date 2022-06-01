package com.example.tourplanner.controllers;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class ImportTourLogsController {
    static BusinessLogicLayer bl = new BusinessLogicLayer();

    @FXML
    Label importLabel;
    public void onFileDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    public void onFileDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            System.out.println(db.getFiles().toString());
            bl.importTourLogs(db.getFiles().toString().replace("\\", "/").replace("[", "").replace("]", ""));
            success = true;
        }
        /* let the source know whether the string was successfully
         * transferred and used */

        event.setDropCompleted(success);

        event.consume();
        Stage stage = (Stage) importLabel.getScene().getWindow();
        stage.close();
    }
}
