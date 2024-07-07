package org.example.service;

import org.example.DAO.UserDAOImpl;
import org.example.model.Client;
import org.example.model.MileStone;
import org.example.model.User;
import org.example.model.UserActivity;

import java.sql.Timestamp;
import java.util.List;

public class UserService {
    private UserDAOImpl userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public User loginUser(String username, String password) {
        return userDAO.loginUser(username, password);
    }

    public boolean registerUser(String username, String password, String role, String status, String accessLevel) {
        User user = new User( 1,username, password, role, status, accessLevel);
        return userDAO.registerUser(user);
    }

    public List<UserActivity> getUserActivities(String username) {
        return userDAO.getUserActivitiesByUsername(username);
    }

    public void getprintUserNamesByRole(){
        userDAO.printUserNamesByRole("TeamMember");
    }

    public void getprintAdminNamesByRole(){
        userDAO.printAdminNamesByRole("Admin");
    }

    public void getupdateUserByUsername(String username, String newPassword, String newRole, String newStatus) {
        userDAO.updateUserByUsername(username,newPassword,newRole,newStatus);
    }

    public void deleteUserAdminByUsername(String username){
        userDAO.deleteUserAdminByUsername(username);
    }

    public void updateAccessLevelByUsername(String username, String newAccessLevel){
        userDAO.updateAccessLevelByUsername(username,newAccessLevel);
    }

    public void getgetProjectDetails(String username){
        userDAO.getProjectDetails(username);
    }

    public void getupdateTaskAndUserActivity(String usernameToUpdate, String newTaskStatus){
        userDAO.updateTaskAndUserActivity(usernameToUpdate,newTaskStatus);
    }

    public boolean getresetPassword(String username, String newPassword){
        return userDAO.resetPassword(username,newPassword);
    }

    public  List<Client> getgetClients(){
        return userDAO.getClients();
    }

    public int getgetClientIDByName(String clientName){
        return userDAO.getClientIDByName(clientName);
    }

    public boolean getcreateProject(String projectName, String projectDetails, int clientID, Timestamp startDate, Timestamp dueDate, int pmid){
        return userDAO.createProject(projectName, projectDetails, clientID, startDate, dueDate, pmid);
    }

    public boolean getcreateMilestone(String milestoneName, String milestoneDescription){
        return userDAO.createMilestone(milestoneName,milestoneDescription);
    }

    public List<String> getgetProjectsByUsername(String username){
        return userDAO.getProjectsByUsername(username);
    }

    public int getgetProjectIDByName(String projectName){
        return userDAO.getProjectIDByName(projectName);
    }

    public int getgetUserIDByUsername(String username) {
        return userDAO.getUserIDByUsername(username);
    }

    public void getassignProjectToUser(int projectID, int pmid, int userID) {
        userDAO.assignProjectToUser(projectID,pmid, userID);
    }

    public void getinsertClient(Client client){
        userDAO.insertClient(client);
    }

    public List<String> getgetUsernamesByProjectAndPM(int projectId, int pmId) {
        return userDAO.getUsernamesByProjectAndPM(projectId, pmId);
    }

    public List<MileStone> getgetAllMilestones(){
        return userDAO.getAllMilestones();
    }

    public int getgetMilestoneIDByName(String milestoneName) {
        return userDAO.getMilestoneIDByName(milestoneName);
    }

    public int getinsertTask(String taskName, String taskDetails, int projectID, int assignedTo, int milestoneID) {
        return userDAO.insertTask(taskName,taskDetails,projectID,assignedTo,milestoneID);
    }

    public void getinsertUserActivity(int userID, int taskID, int milestoneID) {
        userDAO.insertUserActivity(userID,taskID,milestoneID);
    }

}
