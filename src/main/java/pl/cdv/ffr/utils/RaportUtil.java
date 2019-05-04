package pl.cdv.ffr.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.cdv.ffr.model.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RaportUtil {

    public static ByteArrayInputStream generateInvoice(Tenat tenat, Property property, Bill bill) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            Element propertyTable = getPropertyTable(property);
            Element tenatTable = getTenatTable(tenat);
            Element mediaTable = getMediaTable(bill);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(propertyTable);
            document.add(tenatTable);
            document.add(mediaTable);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(RaportUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static Element getPropertyTable(Property property) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(60);
        try {
            table.setWidths(new int[]{3, 3, 3});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PdfPCell hcell;
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        hcell = new PdfPCell(new Phrase("ULICA", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("KOD POCZTOWY", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("MIASTO", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        PdfPCell cell2;

        cell2 = new PdfPCell(new Phrase(property.getStreet()));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(property.getPostCode()));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(property.getCity()));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        return table;
    }

    private static Element getTenatTable(Tenat tenat) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(60);
        try {
            table.setWidths(new int[]{3, 3, 3});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        PdfPCell hcell;
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        hcell = new PdfPCell(new Phrase("IMIE", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("NAZWISKO", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("EMAIL", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        PdfPCell cell2;

        cell2 = new PdfPCell(new Phrase(tenat.getFirstName()));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(tenat.getLastName()));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);

        cell2 = new PdfPCell(new Phrase(tenat.getEmail()));
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        return table;
    }

    private static Element getMediaTable(Bill bill) {
        List<BaseMedia> mediaList = new ArrayList<>();
        mediaList.add(bill.getColdWater());
        mediaList.add(bill.getHotWater());
        mediaList.add(bill.getElectricity());
        mediaList.add(bill.getHeating());
        mediaList.add(bill.getCommonPart());
        mediaList.add(bill.getRepairFund());
        mediaList.add(bill.getTrash());

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(60);
        try {
            table.setWidths(new int[]{3, 3, 3, 3, 3, 3});
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPCell hcell;
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        hcell = new PdfPCell(new Phrase("MEDIA", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Zuzycie", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Stawka", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Jednostka", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Cena", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Waluta", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        for (BaseMedia media : mediaList) {
            PdfPCell cell;

            String mediaName = "";
            if (media instanceof ColdWater)
                mediaName = "Zimna woda";
            if (media instanceof HotWater)
                mediaName = "Ciepla woda";
            if (media instanceof Electricity)
                mediaName = "Prad";
            if (media instanceof Heating)
                mediaName = "Ogrzewanie";
            if (media instanceof CommonPart)
                mediaName = "Czesci wspolne";
            if (media instanceof RepairFund)
                mediaName = "Fundusz naprawczy";
            if (media instanceof Trash)
                mediaName = "Smieci";

            cell = new PdfPCell(new Phrase(mediaName));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(media.getUsed()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(media.getRate()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(media.getUnit()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(media.getAmount()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(media.getCurrency()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);
        }
        return table;
    }
}
