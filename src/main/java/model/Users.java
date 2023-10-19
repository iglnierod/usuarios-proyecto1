package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Users implements Serializable {
    HashMap<String, User> users;

    public Users() {
        this.users = new HashMap<>();
    }

    public Users(HashMap<String, User> users) {
        this.users = users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public HashMap<String, User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.put(user.getName(), user);
        System.out.println("Se ha añadido el usuario: ");
        System.out.println(user);
    }

    public boolean findUser(String userName) {
        return users.get(userName) != null;
    }

    public boolean checkPwd(String userName, String pwd) {
        User user = users.get(userName);
        return user.checkPassword(pwd, user.getPasswordHash());
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collection<User> users = this.users.values();
        for (User user : users) {
            sb.append(user.toString() + "\n");
        }
        return sb.toString();
    }
}
