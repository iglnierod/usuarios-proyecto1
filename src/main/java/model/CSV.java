package model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CSV {
    public static void userToCSV(User user, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Name", "Age", "Email", "Image");
            csvPrinter.printRecord(user.getName(), user.getAge(), user.getEmail(), user.getImagePath());
            csvPrinter.flush();
            System.out.println("CSV generado con éxito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void usersToCSV(Users users, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Name", "Age", "Email", "Image");
            for (User user : users.getAllUsers()) {
                csvPrinter.printRecord(user.getName(), user.getAge(), user.getEmail(), user.getImagePath());
            }
            System.out.println("CSV generado con éxito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
