package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileHandler {
    private File file;
    private byte[] header;

    private HashMap<String, User> users;

    public FileHandler() {
        file = new File("usuarios.bin");
        header = new byte[]{(byte) 0xFF, (byte) 0xEE, (byte) 0x20, (byte) 0x23, (byte) 0xEE, (byte) 0xFF};
        createFile();
    }

    private void createFile() {
        // If file exists then check header
        if (file.exists()) {
            if (checkHeader() == true) {
                users = loadUsers();
                /**/
                System.out.println("Se han cargado los siguientes usuarios: ");
                System.out.println(users);
            }
            return;
        }
        // If file doesn't exist creates it and writes header and admin user
        try {
            file.createNewFile();
            writeHeader();
            HashMap<String, User> users = new HashMap<>();
            users.put("admin", new User("admin", "admin", 0, "admin@dam.local"));
            writeObject(users);
        } catch (IOException e) {
            System.out.println("ERROR: No se ha podido crear el archivo " + file.getName());
            e.printStackTrace();
        }
    }

    private boolean checkHeader() {
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(this.file))) {
            byte[] readBytes = bif.readNBytes(this.header.length);
            int result = Arrays.compare(this.header, readBytes);
            // If result == 0 -> this.header == readBytes
            /**/
            System.out.println("this.header == readBytes: " + (result == 0));
            return result == 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeHeader() {
        try (FileOutputStream fos = new FileOutputStream(this.file)) {
            // Writes header bytes into the file
            fos.write(this.header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeObject(HashMap<String, User> users) {
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(this.file, true))) {
            ois.writeObject(users);
            System.out.println("Se ha escrito el objeto: " + users);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, User> loadUsers() {
        try (FileInputStream fis = new FileInputStream(this.file)) {
            // Skips over the header bytes
            fis.readNBytes(this.header.length);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Reads list of users from file and casts it
            return (HashMap<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
