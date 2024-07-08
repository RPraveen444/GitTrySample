package org.example.model;

public class User {
    private int user_id;
    private String user_name;
    private String password;
    private String role;
    private String status;
    private String access_level;

    public User() {}

    public User(int user_id, String user_name, String password, String role, String status, String access_level) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.role = role;
        this.status = status;
        this.access_level = access_level;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccess_level() {
        return access_level;
    }

    public void setAccess_level(String access_level) {
        this.access_level = access_level;
    }
}

