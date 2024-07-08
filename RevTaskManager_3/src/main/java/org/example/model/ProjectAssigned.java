package org.example.model;

public class ProjectAssigned {
    private Project project;
    private User PMID;
    private User AssignedAt;

    public ProjectAssigned(){}

    public ProjectAssigned(Project project, User PMID, User assignedAt) {
        this.project = project;
        this.PMID = PMID;
        AssignedAt = assignedAt;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getPMID() {
        return PMID;
    }

    public void setPMID(User PMID) {
        this.PMID = PMID;
    }

    public User getAssignedAt() {
        return AssignedAt;
    }

    public void setAssignedAt(User assignedAt) {
        AssignedAt = assignedAt;
    }
}
