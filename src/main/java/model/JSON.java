package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JSON {
    public static void userToJSON(User user, File file) {
        System.out.println("Exportando...");

        JSONObject jsonUser = new JSONObject();
        jsonUser.put("nombre", user.getName());
        jsonUser.put("edad", user.getAge());
        jsonUser.put("email", user.getEmail());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonUser.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Archivo JSON creado: " + jsonUser);
    }

    public static void allUsersToJSON(Users users, File file) {
        System.out.println("Exportando todos los usuarios...");

        JSONArray usersArray = new JSONArray();
        for (User user : users.getAllUsers()) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("nombre", user.getName());
            jsonUser.put("edad", user.getAge());
            jsonUser.put("email", user.getEmail());
            usersArray.add(jsonUser);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(usersArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Archivo JSON creado con todos los usuarios");
    }
}
