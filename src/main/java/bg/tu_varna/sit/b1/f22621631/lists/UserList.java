package main.java.bg.tu_varna.sit.b1.f22621631.lists;

import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserList {
    private static UserList instance;
    private Set<User> userList;

    private UserList() {
        userList = new HashSet<>();
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
}
