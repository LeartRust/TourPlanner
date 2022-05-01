package com.example.tourplanner.viewmodel;

import com.example.tourplanner.database.MongoDB;
import com.example.tourplanner.models.CreateTourModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateTourViewModel {

    private final StringProperty tourName = new SimpleStringProperty("");
    private final StringProperty tourDescription = new SimpleStringProperty("");
    private final StringProperty tourFrom = new SimpleStringProperty("");
    private final StringProperty tourTo = new SimpleStringProperty("");
    private final StringProperty tourTransportType = new SimpleStringProperty("");
    private final StringProperty tourDistance = new SimpleStringProperty("");


    public void saveTour(){
       CreateTourModel tour = new CreateTourModel(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get());
        MongoDB db = new MongoDB();
        db.addTour(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get());
    }

}
