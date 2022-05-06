package com.example.tourplanner.viewmodel;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.MongoDB;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;
import com.example.tourplanner.models.AddTourModel;
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
       AddTourModel tour = new AddTourModel(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get());
       MongoDB db = new MongoDB();
        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTour(new tourInfo(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get()));
        tourName.set("");
        tourDescription.set("");
        tourFrom.set("");
        tourTo.set("");
        tourTransportType.set("");
        tourDistance.set("");
    }

}
