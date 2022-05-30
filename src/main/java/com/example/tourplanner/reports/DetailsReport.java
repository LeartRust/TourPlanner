package com.example.tourplanner.reports;

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

import java.io.IOException;

public class DetailsReport {
    //TODO add image and logs to report
    //TODO style report

    public static final String LOREM_IPSUM_TEXT = "Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static final String GOOGLE_MAPS_PNG = "./google_maps.png";
    public static final String TARGET_PDF = "target.pdf";

    public void createPdf(String tourName, String tourDescription, String tourFrom, String tourTo, String tourTransportType, String tourDistance) throws IOException {
        System.out.println("TEST PDF:" + tourName);
        PdfWriter writer = new PdfWriter(TARGET_PDF);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph tourHeader = new Paragraph("Detailed PDF about a tour")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.RED);
        document.add(tourHeader);
        document.add(new Paragraph("Tour Name: " + tourName));
        document.add(new Paragraph("Description: " + tourDescription));
        document.add(new Paragraph("From: " +tourFrom));
        document.add(new Paragraph("To: " + tourTo));
        document.add(new Paragraph("Transport Type: " +tourTransportType));
        document.add(new Paragraph("Distance: " + tourDistance));
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
/*
        ImageData imageData = ImageDataFactory.create(GOOGLE_MAPS_PNG);
        document.add(new Image(imageData));
*/

        document.close();
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setBold().setBackgroundColor(ColorConstants.GRAY);
    }


}
