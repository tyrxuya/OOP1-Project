package main.java.bg.tu_varna.sit.b1.f22621631.users;

public class User {
    private final String username;
    private final String password;
    private final PermissionLevel permissionLevel;

    public User(String username, String password, PermissionLevel permissionLevel) {
        this.username = username;
        this.password = password;
        this.permissionLevel = permissionLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }
}
