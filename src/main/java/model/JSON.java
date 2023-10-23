package model;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSON {
    public static void userToJSON(User user, File file) {
        System.out.println("exportando...");
        //Creating a JSONObject object
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("nombre", user.getName());
        jsonUser.put("edad", user.getAge());
        jsonUser.put("email", user.getEmail());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonUser.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonUser);
    }
}
