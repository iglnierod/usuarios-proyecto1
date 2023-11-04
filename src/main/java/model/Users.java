package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Users implements Serializable {
    private final HashMap<String, User> users;

    public Users() {
        this.users = new HashMap<>();
    }

    public boolean userExists(String userName) {
        for (String s : users.keySet()) {
            if (s.equals(userName)) return true;
        }
        return false;
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

    public void deleteUser(String userName) {
        this.users.remove(userName);
    }

    public Collection<User> getAllUsers() {
        return this.users.values();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collection<User> users = this.users.values();
        for (User user : users) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }
}
