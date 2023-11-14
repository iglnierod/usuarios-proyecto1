package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSON {
    public static void userToJSON(User user, File file) {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("nombre", user.getName());
        jsonUser.put("edad", user.getAge());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("image", user.getImageName());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonUser.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("JSON generado con éxito.");
    }

    public static void allUsersToJSON(Users users, File file) {
        JSONArray usersArray = new JSONArray();
        for (User user : users.getAllUsers()) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("nombre", user.getName());
            jsonUser.put("edad", user.getAge());
            jsonUser.put("email", user.getEmail());
            jsonUser.put("image", user.getImageName());
            usersArray.add(jsonUser);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(usersArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON generado con éxito.");
    }
}
