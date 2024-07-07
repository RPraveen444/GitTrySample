package org.example.model;

public class ProjectManager {
    private Project project;
    private User PMID;

    public ProjectManager(){}

    public ProjectManager(Project project, User PMID) {
        this.project = project;
        this.PMID = PMID;
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
}


