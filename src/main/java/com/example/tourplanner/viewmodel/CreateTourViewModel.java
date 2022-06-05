package com.example.tourplanner.viewmodel;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.dataAccessLayer.database.tourEditInfo;
import com.example.tourplanner.dataAccessLayer.database.tourInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateTourViewModel {

    private String id;
    private  String oldTourName;
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
    public void setId(String id){this.id=id;}
    public String getId(){return id;}

    public void setOldTourName(String oldTourName){this.oldTourName=oldTourName;}
    public String getOldTourName(){return oldTourName;}


    public void saveTour(){
        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.addTour(new tourInfo(tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get()));
        tourName.set("");
        tourDescription.set("");
        tourFrom.set("");
        tourTo.set("");
        tourTransportType.set("");
        tourDistance.set("");
    }

    public void EditTour(){
        BusinessLogicLayer bl = new BusinessLogicLayer();
        bl.editTour(new tourEditInfo(id ,tourName.get(), tourDescription.get(), tourFrom.get(), tourTo.get(), tourTransportType.get(), tourDistance.get()));
        bl.editManyTourLog(oldTourName, tourName.get());
        tourName.set("");
        tourDescription.set("");
        tourFrom.set("");
        tourTo.set("");
        tourTransportType.set("");
        tourDistance.set("");
    }

}
