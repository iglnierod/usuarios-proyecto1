package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {
    private User user;
    private File log;

    public void setUser(User user) {
        this.user = user;
        log = new File("session.log");
        this.log(user.getName() + " LOGIN");
    }

    public User getUser() {
        return this.user;
    }

    public void logOut() {
        this.log(user.getName() + " LOGOUT");
    }

    private void log(String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(dateFormat.format(now)).append(" ");
        sb.append(message);
        sb.append("\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.log, true))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("No se ha podido hacer log del usuario.");
        }
    }
}
