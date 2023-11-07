package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SQL {
    static void createUsersTable(Users users, File file) {
        StringBuilder string = new StringBuilder();
        string.append("CREATE TABLE IF NOT EXISTS usuarios(  nombre varchar(20) PRIMARY KEY,\n" +
                "  contrasena varchar(60) NOT NULL,\n" +
                "  edad int NOT NULL,\n" +
                "  email varchar(60) NOT NULL\n" +
                ");\n\n");
        for (User user : users.getAllUsers()) {
            string.append("INSERT INTO usuarios VALUES (\"" + user.getName()+"\", \"" + user.getPasswordHash() + "\", "
                    + user.getAge() + ", \"" + user.getEmail()+"\");\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(string.toString());
            System.out.println("Script SQL generado con éxito.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
