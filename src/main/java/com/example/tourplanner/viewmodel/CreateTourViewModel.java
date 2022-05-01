package com.example.tourplanner.viewmodel;

import com.example.tourplanner.database.MongoDB;
import com.example.tourplanner.models.TourModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateTourViewModel {

    private final StringProperty tourName = new SimpleStringProperty("");
    private final StringProperty tourDescription = new SimpleStringProperty("");
    private final StringProperty tourFrom = new SimpleStringProperty("");
    private final StringProperty tourTo = new SimpleStringProperty("");
    private final StringProperty tourTransportType = new SimpleStringProperty("");
    private final StringProperty tourDistance = new SimpleStringProperty("");

    public StringProperty getTourName() {
        return tourName;
    }
    public StringProperty getTourDescription() {
        return tourDescription;
    }
    public StringProperty getTourFrom() {
        return tourFrom;
    }
    public StringProperty getTourTo() {
        return tourTo;
    }
    public StringProperty getTourTransportType() {
        return tourTransportType;
    }
    public StringProperty getTourDistance() {
        return tourDistance;
    }




    public void saveTour(){
       //TourModel tour = new TourModel(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get());
       MongoDB db = new MongoDB();
       db.addTour(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get());
        tourName.set("");
        tourDescription.set("");
        tourFrom.set("");
        tourTo.set("");
        tourTransportType.set("");
        tourDistance.set("");
    }

}
