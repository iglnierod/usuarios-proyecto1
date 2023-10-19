package model;

import gui.Login;

import javax.swing.*;

public class App {
    private final String filename = "usuarios.bin";
    private Users users;
    private Session session;

    private User currentUser;

    private FileHandler fileHandler;

    public App() {
        if (FileHandler.fileExists()) {
            this.users = FileHandler.loadUsers();
        } else {
            this.users = new Users();
        }
    }

    public void addUser(User user) {
        users.addUser(user);
    }

    public boolean logIn(String userName, String pwd) {
        if (users.findUser(userName)) {
            if (users.checkPwd(userName, pwd)) {
                currentUser = users.getUser(userName);
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
