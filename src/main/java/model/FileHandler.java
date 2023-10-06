package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHandler {
    private File file;
    private byte[] header;

    public FileHandler() {
        file = new File("usuarios.bin");
        header = new byte[]{(byte) 0xFF, (byte) 0xEE, (byte) 0x20, (byte) 0x23, (byte) 0xEE, (byte) 0xFF};
        createFile();
    }

    private void createFile() {
        // If file exists then check header
        if (file.exists()) {
            if (checkHeader() == true) {
                loadUsers();
            }
            return;
        }
        // If file doesn't exist creates it and writes header and admin user
        try {
            file.createNewFile();
            writeHeader();
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
            System.out.println(result == 0);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try (FileInputStream fis = new FileInputStream(this.file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Skips over the header bytes
            ois.skip(this.header.length);
            // Reads list of users from file and casts it
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            /**/
            // Show users loaded from file
            System.out.println("~~~~ USUARIOS CARGADOS DESDE ARCHIVO ~~~~");
            System.out.println(users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAdminUser() {

    }
}
