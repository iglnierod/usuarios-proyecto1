package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIP {
    // source: UD-Ficheros > Ejercicio119
    public static void exportUsersToZIP(App app,File file) {
        String zipPath = file.getParent();
        File users = new File(zipPath, "users");
        users.mkdir();
        app.exportAllUsersToXML(new File(users, "users.xml"));
        app.exportAllUsersToJSON(new File(users, "users.json"));

        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            System.out.println("~~~~~~~~ Añadiendo archivos... ~~~~~~~~");
            for (File f : users.listFiles()) {
                addToZIP(f, f.getName(), zipOut);
                System.out.println("Se ha añadido al archivo '" + file.getName() + "' el " + (f.isDirectory() ? "directorio" : "archivo") + ": " + f.getName());
            }
            zipOut.close();
            fos.close();
            System.out.println("Completada la compresión del directorio " + file.getName() + " a .zip");
            System.out.println("La ruta del nuevo archivo comprimido es: " + file.getAbsolutePath());
            deleteFolder(Path.of(users.getAbsolutePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // source: UD-Ficheros > Ejercicio119
    private static void addToZIP(File fileToZip, String fileName, ZipOutputStream zipOut) {
        try {
            if (fileToZip.isDirectory()) {
                String folderName = fileName.endsWith("/") ? fileName : (fileName + "/");
                zipOut.putNextEntry(new ZipEntry(folderName));
                zipOut.closeEntry();

                File[] children = fileToZip.listFiles();
                for (File childFile : children) {
                    addToZIP(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
                return;
            }

            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int lenght;
            while ((lenght = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, lenght);
            }
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // source: https://www.educative.io/answers/how-to-delete-a-directory-and-its-contents-in-java
    private static void deleteFolder(Path folderPath) throws IOException {
        Files
                .walk(folderPath) // Traverse the file tree in depth-first order
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        System.out.println("Deleting: " + path);
                        Files.delete(path);  //delete each file or directory
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}
