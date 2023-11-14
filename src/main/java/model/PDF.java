package model;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;

public class PDF {
    public static void userToPDF(User user, File file) {
        try {
            Logger.getRootLogger().setLevel(Level.FATAL);
            BasicConfigurator.configure();
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("User:");
            contentStream.endText();

            String[] data = {"Name: " + user.getName(), "Age: " + user.getName(), "Email: " + user.getEmail(), "Image: " + user.getImageName()};

            float y = 650;
            for (String var : data) {
                contentStream.beginText();
                contentStream.newLineAtOffset(120, y);
                contentStream.showText("• " + var);
                contentStream.endText();
                y -= 20;
            }

            contentStream.close();

            document.save(file);
            document.close();

            System.out.println("PDF generado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void allUsersToPDF(Users users, File file) {
        try {
            Logger.getRootLogger().setLevel(Level.FATAL);
            BasicConfigurator.configure();
            
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);


            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Users:");
            contentStream.endText();

            float y = 650;
            for (User user : users.getAllUsers()) {
                String[] data = {"Name: " + user.getName(), "Age: " + user.getName(), "Email: " + user.getEmail(), "Image: " + user.getImageName()};
                for (String var : data) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(120, y);
                    contentStream.showText("• " + var);
                    contentStream.endText();
                    y -= 20;
                }

                contentStream.setLineWidth(1f);
                contentStream.moveTo(50, y + 10);
                contentStream.lineTo(550, y + 10);
                contentStream.stroke();
                y -= 20;
            }

            contentStream.close();

            document.save(file);
            document.close();

            System.out.println("PDF generado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
