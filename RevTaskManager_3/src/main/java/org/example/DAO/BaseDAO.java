package org.example.DAO;

import org.example.model.Client;
import org.example.model.MileStone;
import org.example.model.User;
import org.example.model.UserActivity;

import java.util.List;

public interface BaseDAO {

    public User loginUser(String username, String password);

    public boolean registerUser(User user);

    public List<UserActivity> getUserActivitiesByUsername(String username);

    public void printUserNamesByRole(String role);

    public void printAdminNamesByRole(String role);

    public void updateUserByUsername(String username, String newPassword, String newRole, String newStatus);

    public void deleteUserAdminByUsername(String username);

    public void updateAccessLevelByUsername(String username, String newAccessLevel);

    public void getProjectDetails(String username);

    public void updateTaskAndUserActivity(String usernameToUpdate, String newTaskStatus);

    public boolean resetPassword(String username, String newPassword);

    public List<Client> getClients();

    public int getClientIDByName(String clientName);

    public boolean createProject(String projectName, String projectDetails, int clientID, java.sql.Timestamp startDate, java.sql.Timestamp dueDate, int pmid);

    public boolean createMilestone(String milestoneName, String milestoneDescription);

    public List<String> getProjectsByUsername(String username);

    public int getProjectIDByName(String projectName);

    public int getUserIDByUsername(String username);

    public void assignProjectToUser(int projectID, int pmid, int userID);

    public void insertClient(Client client);

    public List<String> getUsernamesByProjectAndPM(int projectId, int pmId);

    public List<MileStone> getAllMilestones();

    public int getMilestoneIDByName(String milestoneName);

    public int insertTask(String taskName, String taskDetails, int projectID, int assignedTo, int milestoneID);

    public void insertUserActivity(int userID, int taskID, int milestoneID);




}
