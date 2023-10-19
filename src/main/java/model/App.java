package model;

public class App {
    private final String filename = "usuarios.bin";
    private Users users;
    private Session session;

    public App() {
        users = new Users();
        new FileHandler();
    }

    public void addUser(User user) {
        users.addUser(user);
    }
}
