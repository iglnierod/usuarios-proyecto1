package model;

public class User {
    private String name;
    private String passwordHash;
    private int age;
    private String email;

    public User(String name, String passwordHash, int age, String email) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.age = age;
        this.email = email;
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
