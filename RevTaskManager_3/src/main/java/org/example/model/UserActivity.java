package org.example.model;

public class UserActivity {
    private int activity_id;
    private User user;
    private Task task;
    private MileStone milestone;
    private String activity_timestamp;

    public UserActivity(){}

    public UserActivity(int activity_id, User user, Task task, MileStone milestone, String activity_timestamp) {
        this.activity_id = activity_id;
        this.user = user;
        this.task = task;
        this.milestone = milestone;
        this.activity_timestamp = activity_timestamp;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public MileStone getMilestone() {
        return milestone;
    }

    public void setMilestone(MileStone milestone) {
        this.milestone = milestone;
    }

    public String getActivity_timestamp() {
        return activity_timestamp;
    }

    public void setActivity_timestamp(String activity_timestamp) {
        this.activity_timestamp = activity_timestamp;
    }
}
