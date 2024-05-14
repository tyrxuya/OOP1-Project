package main.java.bg.tu_varna.sit.b1.f22621631.lists;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList instance;
    private List<User> userList;

    private UserList() {
        userList = new ArrayList<>();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public List<User> getUserList() {
        return new ArrayList<>(userList);
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void clear() {
        userList.clear();
    }

    public void add(User user) {
        if (!userExists(user.getUsername())) {
            userList.add(user);
        }
    }

    public void remove(String username) {
        User userToRemove = findUser(username);
        userList.remove(userToRemove);
    }

    public User findUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found!");
    }

    public Boolean userExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
