package model;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.specialized.UlTag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        String html =
                html(
                        head(
                                title("users")
                        ),
                        body(
                                users.getAllUsers().stream().map(user ->
                                        ul(
                                                li("Name: " + user.getName()),
                                                li("Age: " + user.getAge()),
                                                li("Email: " + user.getEmail()),
                                                li("Image: " + user.getImagePath()),
                                                img().withSrc(user.getImagePath()).withStyle("list-style-type: none;")
                                        )
                                ).toArray(ContainerTag[]::new)
                        )
                ).render();

        writeToFile(html, file);
    }

    private static void writeToFile(String html, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(html);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
