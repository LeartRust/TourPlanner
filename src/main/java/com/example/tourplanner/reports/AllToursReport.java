package com.example.tourplanner.reports;

import com.example.tourplanner.businessLogic.BusinessLogicLayer;
import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import com.example.tourplanner.models.TourLogModel;
import com.example.tourplanner.models.TourModel;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AllToursReport {
    public static final String PDF_DIRECTORY = "../TourPlanner/src/main/resources/com/example/tourplanner/reports/";
    BusinessLogicLayer bl = new BusinessLogicLayer();

    private static final ILoggerWrapper logger = LoggerFactory.getLogger();

    public void createPdf(){
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(PDF_DIRECTORY + "AllTours.pdf");
        } catch (FileNotFoundException e) {
            logger.error("AllToursReport.java createPdf FileNotFoundException");
            throw new RuntimeException(e);
        }
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
