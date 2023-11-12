package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SQL {
    static void createUsersTable(Users users, File file) {
        StringBuilder string = new StringBuilder();
        string.append("CREATE TABLE IF NOT EXISTS usuarios(\n" +
                "\tnombre varchar(20) PRIMARY KEY,\n" +
                "\tcontrasena varchar(60) NOT NULL,\n" +
                "\tedad int NOT NULL,\n" +
                "\temail varchar(60) NOT NULL,\n" +
                "\timage varchar(100) NOT NULL\n" +
                ");\n");
        for (User user : users.getAllUsers()) {
            string.append("INSERT INTO usuarios VALUES (\""
                    + user.getName() + "\", \""
                    + user.getPasswordHash() + "\", "
                    + user.getAge() + ", \""
                    + user.getEmail() + "\", \""
                    // TODO: exportar imagen con /
                    + user.getImagePath() + "\");\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(string.toString());
            System.out.println("Script SQL generado con éxito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
