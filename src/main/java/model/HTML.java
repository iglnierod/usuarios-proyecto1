package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static j2html.TagCreator.*;

public class HTML {
    public static void userToHTML(User user, File file) {
        String html =
                html(
                        head(
                                title("user: " + user.getName())
                        ),
                        body(
                                ul(
                                        li("Name: " + user.getName()),
                                        li("Age: " + user.getAge()),
                                        li("Email: " + user.getEmail()),
                                        li("Image: " + user.getImagePath())
                                ),
                                img().withSrc(user.getImagePath())
                        )
                ).render();

        writeToFile(html, file);
    }

    public static void usersToHTML(Users users, File file) {
        // TODO: export users to html
    }

    private static void writeToFile(String html, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(html);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
