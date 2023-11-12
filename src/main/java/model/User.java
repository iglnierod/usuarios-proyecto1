package model;

import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String passwordHash;
    private int age;
    private String email;
    private File image;

    public User() {
        // Constructor vacío necesario para JAXB
    }

    public User(String name, String password, int age, String email) {
        this.name = name;
        this.setPasswordHash(password);
        System.out.println(passwordHash);
        System.out.println(passwordHash.length());
        this.age = age;
        this.email = email;
    }

    public User(String name, String password, int age, String email, File image) {
        this.name = name;
        this.setPasswordHash(password);
        System.out.println(passwordHash);
        System.out.println(passwordHash.length());
        this.age = age;
        this.email = email;
        this.image = image;
    }

    private void setPasswordHash(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String pwd, String hashedPwd) {
        return BCrypt.checkpw(pwd, hashedPwd);
    }

    public void changePassword(String newPassword) {
        this.setPasswordHash(newPassword);
        System.out.println("Se ha cambiado la contraseña al usuario: " + this.getName());
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return image.getAbsolutePath();
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
