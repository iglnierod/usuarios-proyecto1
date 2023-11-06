package model;

import gui.Login;

import java.io.File;

public class App {
    private Users users;
    private Session session;

    public App() {
        this.users = FileHandler.loadUsers();
        this.session = new Session();
        new Login(this);
    }

    public boolean addUser(User user) {
        if (this.users.userExists(user.getName())) {
            return false;
        }
        this.users.addUser(user);
        this.saveUsers();
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

    public void exportAllUsersToJSON(File file) {
        JSON.allUsersToJSON(this.users, file);
    }

    public void exportUserToXML(File file) {
        XML.userToXML(session.getUser(), file);
    }

    public void exportAllUsersToXML(File file) {
        XML.usersToXML(this.users, file);
    }

    public void exportUsersToZIP(File file) {
        ZIP.exportUsersToZIP(this, file);
    }

    public void exportUserToCSV(File file) {
        CSV.userToCSV(this.session.getUser(),file);
    }

    public void exportAllUsersToCSV(File file) {
        CSV.usersToCSV(this.users, file);
    }

    private void saveUsers() {
        FileHandler.saveUsers(this.users);
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
        this.users = FileHandler.loadUsers();
        this.session = new Session();
    }

    public void deleteUser() {
        users.deleteUser(session.getUser().getName());
        this.saveUsers();
        this.logOut();
    }

    public User getCurrentUser() {
        return this.session.getUser();
    }
}
