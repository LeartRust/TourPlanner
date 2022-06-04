package com.example.tourplanner.dataAccessLayer.database;

public record tourEditInfo(String id, String tourName, String tourDescription, String tourFrom, String tourTo,
                           String tourTransportType, String tourDistance) {
}

