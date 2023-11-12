package model;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.BufferOverflowException;
import java.util.Arrays;

import org.imgscalr.*;

import javax.imageio.ImageIO;

public class FileHandler {
    private static final File file = new File("usuarios.bin");
    private static final byte[] header = new byte[]{(byte) 0xFF, (byte) 0xEE, (byte) 0x20, (byte) 0x23, (byte) 0xEE, (byte) 0xFF};
    private static final int IMAGE_WIDTH = 400;
    private static final int IMAGE_HEIGHT = 400;

    private static boolean checkHeader() {
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(file))) {
            byte[] readBytes = bif.readNBytes(header.length);
            int result = Arrays.compare(header, readBytes);
            // If result == 0 -> this.header == readBytes
            return result == 0;
        } catch (IOException e) {
            return false;
        }
    }

    public static void saveUsers(Users users) {
        FileOutputStream fos;
        ObjectOutputStream ois;
        try {
            // Writes header bytes into the file
            fos = new FileOutputStream(file);
            fos.write(header);
            // Writes object Users into the file
            ois = new ObjectOutputStream(new FileOutputStream(file, true));
            ois.writeObject(users);
            /**/
            System.out.println("FileHandler: Se han guardado los usuarios: ");
            System.out.println(users);

            ois.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Users loadUsersFromFile() {
        try (FileInputStream fis = new FileInputStream(file)) {
            // Skips over the header bytes
            fis.readNBytes(header.length);
            // Reads list of users from file and casts it
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println("Se han leido los usuarios del archivo: " + file.getName());
            return (Users) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudieron leer los usuarios del archivo: " + file.getName());
            return null;
        }
    }

    public static Users loadUsers() {
        if (fileExists()) {
            return loadUsersFromFile();
        }
        return initiateFile();
    }

    private static Users initiateFile() {
        Users newUsers = new Users();
        newUsers.addUser(new User("admin", "admin", 0, "admin@admin.local", new File(App.PROJECT_PATH, "\\img\\admin.png")));
        saveUsers(newUsers);
        return newUsers;
    }

    private static boolean fileExists() {
        return file.exists() && checkHeader();
    }

    public static String getFileExtension(File file) {
        return file.getName().substring(file.getName().lastIndexOf('.') + 1);
    }

    /* source:
     *  https://medium.com/@SatyaRaj_PC/simple-java-image-scaling-and-cropping-33f95e7d9278
     *  https://www.tabnine.com/code/java/classes/org.imgscalr.Scalr
     */
    public static void saveResizedImage(File source, File target) {
        try {
            BufferedImage originalImage = ImageIO.read(source);

            BufferedImage subImage = originalImage;
            if (originalImage.getWidth() > 400 && originalImage.getHeight() > 400) {
                int x = Math.max(0, (originalImage.getWidth() - IMAGE_WIDTH) / 2);
                int y = Math.max(0, (originalImage.getHeight() - IMAGE_HEIGHT) / 2);
                subImage = originalImage.getSubimage(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
            }

            BufferedImage resizedImage = Scalr.resize(subImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, IMAGE_WIDTH, IMAGE_HEIGHT);

            ImageIO.write(resizedImage, getFileExtension(source), target);

            System.out.println("Imagen reescalada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al reescalar la imagen: " + e.getMessage());
        }
    }
}


















