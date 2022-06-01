package com.example.tourplanner.reports;
import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class AllToursReport {
    public static final String PDF_DIRECTORY = "../TourPlanner/src/main/resources/com/example/tourplanner/reports/";

    BusinessLogicLayer bl = new BusinessLogicLayer();

    public void createPdf() throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(PDF_DIRECTORY + "AllTours.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        ArrayList<TourModel> toursList = bl.getTours();

        for (TourModel tour : toursList) {
            ArrayList<TourLogModel> tourLogs = bl.getTourLogsByTourName(tour.getTourName());
            Paragraph tourHeader = new Paragraph(tour.getTourName());
            tourHeader.setBold();
            tourHeader.setFontSize(20);
            document.add(tourHeader);

            double avgTime = 0, avgDistance = 0, avgRating = 0;
            for (TourLogModel log : tourLogs) {
                avgTime = avgTime + Integer.parseInt(log.getTotalTime());
                avgDistance = avgDistance + Integer.parseInt(log.getDistance());
                avgRating = avgRating + Integer.parseInt(log.getRating());
            }
            document.add(new Paragraph("average Time: " + (avgTime/tourLogs.size())));
            document.add(new Paragraph("average Distance: " + (avgDistance/tourLogs.size())));
            document.add(new Paragraph("average Rating: " + (avgRating/tourLogs.size())));
            document.add(new Paragraph("____________"));
        }


        document.close();
    }


}
