package com.example.tourplanner.reports;

import com.example.tourplanner.models.TourLogModel;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.IOException;
import java.util.ArrayList;

public class DetailsReport {
    //TODO add image and logs to report
    //TODO style report

    public static final String LOREM_IPSUM_TEXT = "Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static final String GOOGLE_MAPS_PNG = "./google_maps.png";
    public static final String PDF_DIRECTORY = "../TourPlanner/src/main/resources/com/example/tourplanner/reports/";

    public void createPdf(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance, ArrayList<TourLogModel> tourLogs) throws IOException {
        PdfWriter writer = new PdfWriter(PDF_DIRECTORY + tourName+".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph tourHeader = new Paragraph("Detailed PDF about a tour")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.RED);
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

        ImageData imageData = ImageDataFactory.create("https://www.mapquestapi.com/staticmap/v5/map?key=IwA2M7w326xoylLFZ6PHlaIGdfGS5Ktg&size=@2x&start="+ tourFrom +"&end="+ tourTo);
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
        /*Paragraph p = new Paragraph("dasd");
        p.setBackgroundColor();*/
/*        Paragraph listHeader = new Paragraph("Lorem Ipsum ...")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD))
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.BLUE);*/
/*        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_BOLD));
        list.add(new ListItem("lorem ipsum 1"))
                .add(new ListItem("lorem ipsum 2"))
                .add(new ListItem("lorem ipsum 3"))
                .add(new ListItem("lorem ipsum 4"))
                .add(new ListItem("lorem ipsum 5"))
                .add(new ListItem("lorem ipsum 6"));*/
        //document.add(listHeader);
/*        document.add(list);

        Paragraph tableHeader = new Paragraph("Lorem Ipsum Table ...")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(18)
                .setBold()
                .setFontColor(ColorConstants.GREEN);
        document.add(tableHeader);
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        table.addHeaderCell(getHeaderCell("Ipsum 1"));
        table.addHeaderCell(getHeaderCell("Ipsum 2"));
        table.addHeaderCell(getHeaderCell("Ipsum 3"));
        table.addHeaderCell(getHeaderCell("Ipsum 4"));
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addCell("lorem 1");
        table.addCell("lorem 2");
        table.addCell("lorem 3");
        table.addCell("lorem 4");
        document.add(table);*/

/*        document.add(new AreaBreak());

        Paragraph imageHeader = new Paragraph("Lorem Ipsum Image ...")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(18)
                .setBold()
                .setFontColor(ColorConstants.GREEN);
        document.add(imageHeader);*/

        document.close();
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setBold().setBackgroundColor(ColorConstants.GRAY);
    }


}
