package com.example.tourplanner.reports;

import com.example.tourplanner.logger.ILoggerWrapper;
import com.example.tourplanner.logger.LoggerFactory;
import com.example.tourplanner.models.TourLogModel;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class DetailsReport {


    private static final ILoggerWrapper logger = LoggerFactory.getLogger();
    public static final String PDF_DIRECTORY = "../TourPlanner/src/main/resources/com/example/tourplanner/reports/";

    public void createPdf(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance, ArrayList<TourLogModel> tourLogs){
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(PDF_DIRECTORY + tourName+".pdf");
        } catch (FileNotFoundException e) {
            logger.error("DetailsReport.java createPdf FileNotFoundException");
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph tourHeader = null;
        try {
            tourHeader = new Paragraph("Detailed PDF about a tour")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(14)
                    .setBold()
                    .setFontColor(ColorConstants.RED);
        } catch (IOException e) {
            logger.error("DetailsReport.java createPdf IOException");
            throw new RuntimeException(e);
        }
        document.add(tourHeader);

        Paragraph headerTourName = new Paragraph("Tour Name");
        headerTourName.setBold();
        Paragraph headerDescription = new Paragraph("Description");
        headerDescription.setBold();
        Paragraph headerFrom = new Paragraph("From");
        headerFrom.setBold();
        Paragraph headerTo = new Paragraph("To");
        headerTo.setBold();
        Paragraph headerTransportType = new Paragraph("Transport Type");
        headerTransportType.setBold();
        Paragraph headerDistance = new Paragraph("Distance");
        headerDistance.setBold();


        document.add(headerTourName);
        document.add(new Paragraph(tourName));
        document.add(headerDescription);
        document.add(new Paragraph( tourDescription));
        document.add(headerFrom);
        document.add(new Paragraph(tourFrom));
        document.add(headerTo);
        document.add(new Paragraph( tourTo));
        document.add(headerTransportType);
        document.add(new Paragraph(tourTransportType));
        document.add(headerDistance);
        document.add(new Paragraph( tourDistance));

        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create("https://www.mapquestapi.com/staticmap/v5/map?key=IwA2M7w326xoylLFZ6PHlaIGdfGS5Ktg&size=@2x&start="+ tourFrom +"&end="+ tourTo);
        } catch (MalformedURLException e) {
            logger.error("DetailsReport.java createPdf MalformedURLException");
            throw new RuntimeException(e);
        }
        document.add(new Image(imageData));

        document.add(new Paragraph("____________"));

        document.add(new Paragraph(""));

        Paragraph headerLogs = new Paragraph("Tour Log:");
        headerLogs.setBold();
        for (TourLogModel log : tourLogs) {
            document.add(headerLogs);
            document.add(new Paragraph(log.getDateTime()));
            document.add(new Paragraph(log.getComment()));
            document.add(new Paragraph(log.getDifficulty()));
            document.add(new Paragraph(log.getTotalTime()));
            document.add(new Paragraph(log.getRating()));
            document.add(new Paragraph(log.getDistance()));
            document.add(new Paragraph("____________"));
        }

        document.close();
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setBold().setBackgroundColor(ColorConstants.GRAY);
    }


}
