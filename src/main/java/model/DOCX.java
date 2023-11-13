package model;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.nio.file.Files;

public class DOCX {
    // source: https://www.baeldung.com/docx4j
    public static void userToDOCX(User user, File file) {
        try {
            Logger.getRootLogger().setLevel(Level.ERROR);
            BasicConfigurator.configure();

            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

            mainDocumentPart.addStyledParagraphOfText("Title", "User: " + user.getName());
            mainDocumentPart.addParagraphOfText("Name: " + user.getName());
            mainDocumentPart.addParagraphOfText("Age: " + user.getAge());
            mainDocumentPart.addParagraphOfText("Email: " + user.getEmail());
            mainDocumentPart.addParagraphOfText("Image: " + user.getImagePath());
            byte[] fileContent = Files.readAllBytes(user.getImage().toPath());
            BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, fileContent);
            Inline inline = imagePart.createImageInline("image", "user image", 1, 2, false);
            P Imageparagraph = addImageToParagraph(inline);
            mainDocumentPart.getContent().add(Imageparagraph);

            wordPackage.save(file);
            System.out.println("DOCX generado con éxito.");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // source: https://www.baeldung.com/docx4j
    public static void usersToDOCX(Users users, File file) {
        // TODO: export user list to DOCX
        try {
            Logger.getRootLogger().setLevel(Level.FATAL);
            BasicConfigurator.configure();

            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

            for (User user : users.getAllUsers()) {
                mainDocumentPart.addStyledParagraphOfText("Title", "User: " + user.getName());
                mainDocumentPart.addParagraphOfText("Name: " + user.getName());
                mainDocumentPart.addParagraphOfText("Age: " + user.getAge());
                mainDocumentPart.addParagraphOfText("Email: " + user.getEmail());
                mainDocumentPart.addParagraphOfText("Image: " + user.getImagePath());

                mainDocumentPart.addParagraphOfText("\n");

                byte[] fileContent = Files.readAllBytes(user.getImage().toPath());
                BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, fileContent);
                Inline inline = imagePart.createImageInline("Image", "user image", 1, 2, false);
                P Imageparagraph = addImageToParagraph(inline);
                mainDocumentPart.getContent().add(Imageparagraph);

                Br pageBreak = new Br();
                pageBreak.setType(STBrType.PAGE);
                mainDocumentPart.addObject(pageBreak);
            }
            System.out.println("DOCX generado con éxito.");


            wordPackage.save(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // source: https://www.baeldung.com/docx4j
    private static P addImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        p.getContent().add(r);
        Drawing drawing = factory.createDrawing();
        r.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return p;
    }
}
