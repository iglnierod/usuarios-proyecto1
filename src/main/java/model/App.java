package model;

public class App {
    private final String filename = "usuarios.bin";
    public static Users users;
    private Session session;

    public App() {
        users = new Users();
        new FileHandler();
    }
}
