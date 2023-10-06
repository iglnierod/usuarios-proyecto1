package model;

public class App {
    private final String filename = "usuarios.bin";
    private Users users;
    private Session session;

    public App() {
        new FileHandler();
    }
}
