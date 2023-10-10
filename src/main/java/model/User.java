package model;

import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String passwordHash;
    private int age;
    private String email;

    public User(String name, String password, int age, String email) {
        this.name = name;
        this.passwordHash = setPasswordHash(password);
        this.age = age;
        this.email = email;
    }

    private String setPasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

//    private String getPasswordHash() {
//
//    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
