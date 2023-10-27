package model;

import gui.Login;

import javax.swing.*;
import java.io.File;

public class App {
    private Users users;
    private Session session;

    public App() {
        if (FileHandler.fileExists()) {
            this.users = FileHandler.loadUsers();
            /**/
            System.out.println("Usuarios cargados:");
            System.out.println(this.users);
        } else {
            this.users = FileHandler.initiateFile();
        }
        this.session = new Session();
        new Login(this);
    }

    public boolean addUser(User user) {
        if (this.users.userExists(user.getName())) {
            return false;
        }
        this.users.addUser(user);
        this.saveUsers(this.users);
        System.out.println("users: ");
        System.out.println(this.users);
        return true;
    }


    public boolean logIn(String userName, String pwd) {
        if (this.users.findUser(userName)) {
            if (this.users.checkPwd(userName, pwd)) {
                this.session.setUser(this.users.getUser(userName));
                this.showUser(this.session.getUser());
                return true;
            }
        }
        return false;
    }

    public void exportUserToJSON(File file) {
        JSON.userToJSON(session.getUser(), file);
    }

    void exportAllUsersToJSON(File file) {
        JSON.allUsersToJSON(this.users.getAllUsers(), file);
    }

    public void exportUserToXML(File file) {
        XML.userToXML(session.getUser(), file);
    }

    void exportAllUsersToXML(File file) {
        XML.usersToXML(this.users.getAllUsers(), file);
    }

    public void exportUsersToZIP(File file) {
        ZIP.exportUsersToZIP(this, file);
    }

    public void saveUsers(Users users) {
        FileHandler.saveUsers(users);
    }

    public void showUser(model.User user) {
        new gui.User(this, user.getName()).setVisible(true);
    }

    public void showUserDetails() {
        User currentUser = this.session.getUser();
        new gui.UserDetails(this, currentUser.getName(), String.valueOf(currentUser.getAge()), currentUser.getEmail()).setVisible(true);
    }

    public void showUserChangePassword() {
        User currentUser = this.session.getUser();
        new gui.UserChangePassword(this, currentUser.getName()).setVisible(true);
    }

    public void changeUserPassword(String newPassword) {
        User currentUser = this.session.getUser();
        currentUser.changePassword(newPassword);
        FileHandler.saveUsers(this.users);
    }

    public void showUserCreate() {
        new gui.UserCreate(this).setVisible(true);
    }

    public void showUserDelete() {
        new gui.UserDelete(this, session.getUser().getName()).setVisible(true);
    }

    public void logOut() {
        this.session.logOut();
        this.loadUsers();
        new gui.Login(this).setVisible(true);
    }

    private void loadUsers() {
        if (FileHandler.fileExists()) {
            this.users = FileHandler.loadUsers();
            this.session = new Session();
        }
    }

    public void deleteUser() {
        users.deleteUser(session.getUser().getName());
        this.saveUsers(this.users);
        this.logOut();
    }

    public User getCurrentUser() {
        return this.session.getUser();
    }
}
