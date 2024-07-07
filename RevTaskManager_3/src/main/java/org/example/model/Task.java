package org.example.model;

public class Task {
    private int task_id;
    private String task_name;
    private String task_details;
    private String task_status;
    private Project project;
    private User user;
    private MileStone milestone;

    public Task(){}

    public Task(int task_id, String task_name, String task_details, String task_status, Project project, User user, MileStone milestone) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_details = task_details;
        this.task_status = task_status;
        this.project = project;
        this.user = user;
        this.milestone = milestone;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_details() {
        return task_details;
    }

    public void setTask_details(String task_details) {
        this.task_details = task_details;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MileStone getMilestone() {
        return milestone;
    }

    public void setMilestone(MileStone milestone) {
        this.milestone = milestone;
    }
}
