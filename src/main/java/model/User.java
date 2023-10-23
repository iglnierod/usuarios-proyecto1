package model;

import org.mindrot.jbcrypt.BCrypt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class User implements Serializable {
    private String name;
    private String passwordHash;
    private int age;
    private String email;

    public User() {
        // Constructor vacío necesario para JAXB
    }

    public User(String name, String password, int age, String email) {
        this.name = name;
        this.setPasswordHash(password);
        this.age = age;
        this.email = email;
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

    @XmlElement
    public int getAge() {
        return age;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
