package com.example.tourplanner.viewmodel;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.models.TourModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetailsViewModel {
    BusinessLogicLayer bl = new BusinessLogicLayer();

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

    public void setTourDetails(String newTourName){
        TourModel detailsTour = bl.getTour(newTourName);

        tourName.set(detailsTour.getTourName());
        tourDescription.set(detailsTour.getTourDescription());
        tourFrom.set(detailsTour.getTourFrom());
        tourTo.set(detailsTour.getTourTo());
        tourTransportType.set(detailsTour.getTourTransportType());
        tourDistance.set(detailsTour.getTourDistance());

        System.out.println("tstt: " + tourName);
        System.out.println("tstt: " + tourTo);
        System.out.println("tstt: " + tourDistance);
    }

}
