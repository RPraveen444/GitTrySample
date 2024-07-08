package org.example.model;

public class Project {

    private int project_id;
    private String project_name;
    private String project_details;
    private Client client;
    private User user;
    private String start_date;
    private String due_date;

    public Project(){}

    public Project(int project_id, String project_name, String project_details, Client client, User user, String start_date, String due_date) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_details = project_details;
        this.client = client;
        this.user = user;
        this.start_date = start_date;
        this.due_date = due_date;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_details() {
        return project_details;
    }

    public void setProject_details(String project_details) {
        this.project_details = project_details;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
