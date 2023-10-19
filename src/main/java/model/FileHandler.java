package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileHandler {
    private static File file = new File("usuarios.bin");;
    private static byte[] header = new byte[]{(byte) 0xFF, (byte) 0xEE, (byte) 0x20, (byte) 0x23, (byte) 0xEE, (byte) 0xFF};

    private Users users = new Users();

    public FileHandler() {
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
            this.users.addUser(new User("admin", "admin", 0, "admin@dam.local"));
            this.users.addUser(new User("test", "test", 0, "test@test.local"));

            writeObject(users);
        } catch (IOException e) {
            System.out.println("ERROR: No se ha podido crear el archivo " + file.getName());
            e.printStackTrace();
        }
    }

    private static boolean checkHeader() {
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(file))) {
            byte[] readBytes = bif.readNBytes(header.length);
            int result = Arrays.compare(header, readBytes);
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

    private void writeObject(Users users) {
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(this.file, true))) {
            ois.writeObject(users);
            System.out.println("Se ha escrito el objeto:");
            System.out.println(users);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Users loadUsers() {
        try (FileInputStream fis = new FileInputStream(file)) {
            // Skips over the header bytes
            fis.readNBytes(header.length);
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Reads list of users from file and casts it
            return (Users) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean fileExists() {
        return file.exists() && checkHeader();
    }
}
