package model;

import java.io.File;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSV {
    public static void userToCSV(User user, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Name", "Age", "Email");
            csvPrinter.printRecord(user.getName(), user.getAge(), user.getEmail());
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void usersToCSV(Users users, File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Name", "Age", "Email");
            for (User user : users.getAllUsers()) {
                csvPrinter.printRecord(user.getName(), user.getAge(), user.getEmail());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
