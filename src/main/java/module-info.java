module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires io;
    requires kernel;
    requires layout;
    requires org.apache.logging.log4j;

    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    exports com.example.tourplanner.dataAccessLayer.database;
    opens com.example.tourplanner.dataAccessLayer.database to javafx.fxml;
    exports com.example.tourplanner.controllers;
    opens com.example.tourplanner.controllers to javafx.fxml;
}