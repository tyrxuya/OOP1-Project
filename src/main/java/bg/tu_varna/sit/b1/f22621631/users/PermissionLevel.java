package main.java.bg.tu_varna.sit.b1.f22621631.users;

public enum PermissionLevel {
    NONE("none"),
    USER("User"),
    ADMINISTRATOR("Admin");

    private final String permissionLevel;

    PermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getText() {
        return permissionLevel;
    }
}
