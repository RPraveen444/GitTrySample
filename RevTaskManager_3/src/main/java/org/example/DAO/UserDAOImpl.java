package org.example.DAO;

import org.example.Main;
import org.example.connection.DBConnection;
import org.example.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements BaseDAO{

    Connection conn;

    public UserDAOImpl(){
        this.conn = DBConnection.getConnection();
    }

    private final static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public User loginUser(String username, String password) {
        String query = "SELECT * FROM User WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getString("Status"),
                        rs.getString("AccessLevel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registerUser(User user) {
        String query = "INSERT INTO User (Username, Password, Role, Status, AccessLevel) VALUES (?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUser_name());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getStatus());
            stmt.setString(5, user.getAccess_level());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserActivity> getUserActivitiesByUsername(String username) {
        List<UserActivity> activities = new ArrayList<>();
        Statement stmt = null;

        try {
            String sql = "SELECT " +
                    "ua.ActivityID, u.UserID, u.Username, u.Password, u.Role, u.Status, u.AccessLevel, " +
                    "t.TaskID, t.TaskName, t.TaskDetails, t.TaskStatus, " +
                    "m.MilestoneID, m.MilestoneName, m.MilestoneDescription, ua.ActivityTimestamp " +
                    "FROM UserActivity ua " +
                    "JOIN User u ON ua.UserID = u.UserID " +
                    "JOIN Task t ON ua.TaskID = t.TaskID " +
                    "JOIN Milestone m ON ua.MilestoneID = m.MilestoneID " +
                    "WHERE u.Username = '" + username + "'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int activityID = rs.getInt("ActivityID");

                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getString("Status"),
                        rs.getString("AccessLevel")
                );

                Task task = new Task(
                        rs.getInt("TaskID"),
                        rs.getString("TaskName"),
                        rs.getString("TaskDetails"),
                        rs.getString("TaskStatus"),
                        null,
                        user,
                        null
                );

                MileStone milestone = new MileStone(
                        rs.getInt("MilestoneID"),
                        rs.getString("MilestoneName"),
                        rs.getString("MilestoneDescription")
                );

                String activityTimestamp = rs.getString("ActivityTimestamp");

                UserActivity userActivity = new UserActivity(activityID, user, task, milestone, activityTimestamp);
                activities.add(userActivity);
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return activities;
    }

    @Override
    public void printUserNamesByRole(String role) {
        String select_users_by_role = "SELECT Username FROM User WHERE Role = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(select_users_by_role);
            stmt.setString(1, role);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("Username");
                System.out.println(userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAdminNamesByRole(String role) {
        String select_users_by_role = "SELECT Username FROM User WHERE Role = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(select_users_by_role);
            stmt.setString(1, role);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("Username");
                System.out.println(userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserByUsername(String username, String newPassword, String newRole, String newStatus) {
        String query = "UPDATE User SET Password = ?, Role = ?, Status = ? WHERE Username = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, newRole);
            preparedStatement.setString(3, newStatus);
            preparedStatement.setString(4, username);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("updated successfully...");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserAdminByUsername(String username) {
        String query = "DELETE FROM User WHERE Username = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deactivated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccessLevelByUsername(String username, String newAccessLevel) {
        String query = "UPDATE User SET AccessLevel = ? WHERE Username = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newAccessLevel);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Access level changed successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getProjectDetails(String username) {
        String query = "SELECT "
                + "p.ProjectID, "
                + "p.ProjectName, "
                + "p.ProjectDetails, "
                + "p.StartDate, "
                + "p.DueDate, "
                + "c.ClientName, "
                + "c.ContactInformation, "
                + "t.TaskID, "
                + "t.TaskName, "
                + "t.TaskDetails, "
                + "t.TaskStatus, "
                + "m.MilestoneID, "
                + "m.MilestoneName, "
                + "m.MilestoneDescription "
                + "FROM User u "
                + "JOIN Task t ON u.UserID = t.AssignedTo "
                + "JOIN Project p ON t.ProjectID = p.ProjectID "
                + "JOIN Client c ON p.ClientID = c.ClientID "
                + "LEFT JOIN Milestone m ON t.MilestoneID = m.MilestoneID "
                + "WHERE u.Username = ? "
                + "AND u.Role = 'TeamMember';";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Retrieve data from the result set
                int projectID = resultSet.getInt("ProjectID");
                String projectName = resultSet.getString("ProjectName");
                String projectDetails = resultSet.getString("ProjectDetails");
                String startDate = resultSet.getString("StartDate");
                String dueDate = resultSet.getString("DueDate");
                String clientName = resultSet.getString("ClientName");
                String contactInformation = resultSet.getString("ContactInformation");
                int taskID = resultSet.getInt("TaskID");
                String taskName = resultSet.getString("TaskName");
                String taskDetails = resultSet.getString("TaskDetails");
                String taskStatus = resultSet.getString("TaskStatus");
                int milestoneID = resultSet.getInt("MilestoneID");
                String milestoneName = resultSet.getString("MilestoneName");
                String milestoneDescription = resultSet.getString("MilestoneDescription");

                Client client = new Client();
                client.setClient_name(clientName);
                client.setContact_information(contactInformation);

                User user = new User();
                user.setUser_name(username);

                Project project = new Project();
                project.setProject_id(projectID);
                project.setProject_name(projectName);
                project.setProject_details(projectDetails);
                project.setClient(client);
                project.setUser(user);
                project.setStart_date(startDate);
                project.setDue_date(dueDate);

                MileStone milestone = new MileStone();
                milestone.setMilestone_id(milestoneID);
                milestone.setMilestone_name(milestoneName);
                milestone.setMilestone_description(milestoneDescription);

                Task task = new Task();
                task.setTask_id(taskID);
                task.setTask_name(taskName);
                task.setTask_details(taskDetails);
                task.setTask_status(taskStatus);
                task.setProject(project);
                task.setUser(user);
                task.setMilestone(milestone);

//                System.out.println("Project ID: " + project.getProject_id());
                System.out.println("Project Name: " + project.getProject_name());
                System.out.println("Project Details: " + project.getProject_details());
                System.out.println("Start Date: " + project.getStart_date());
                System.out.println("Due Date: " + project.getDue_date());
                System.out.println("Client Name: " + project.getClient().getClient_name());
                System.out.println("Contact Information: " + project.getClient().getContact_information());
//                System.out.println("Task ID: " + task.getTask_id());
                System.out.println("Task Name: " + task.getTask_name());
                System.out.println("Task Details: " + task.getTask_details());
                System.out.println("Task Status: " + task.getTask_status());
//                System.out.println("Milestone ID: " + milestone.getMilestone_id());
                System.out.println("Milestone Name: " + milestone.getMilestone_name());
                System.out.println("Milestone Description: " + milestone.getMilestone_description());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTaskAndUserActivity(String usernameToUpdate, String newTaskStatus) {

        String updateTaskQuery = "UPDATE Task SET TaskStatus = ? WHERE AssignedTo = (SELECT UserID FROM User WHERE Username = ?)";
        String updateUserActivityQuery = "UPDATE UserActivity SET ActivityTimestamp = CURRENT_TIMESTAMP WHERE UserID = (SELECT UserID FROM User WHERE Username = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement updateTaskStmt = conn.prepareStatement(updateTaskQuery);
             PreparedStatement updateUserActivityStmt = conn.prepareStatement(updateUserActivityQuery)) {

            updateTaskStmt.setString(1, newTaskStatus);
            updateTaskStmt.setString(2, usernameToUpdate);

            int updatedTasks = updateTaskStmt.executeUpdate();
            System.out.println("Number of tasks updated: " + updatedTasks);

            updateUserActivityStmt.setString(1, usernameToUpdate);

            int updatedUserActivities = updateUserActivityStmt.executeUpdate();
            System.out.println("Number of user activities updated: " + updatedUserActivities);

            if(updatedTasks == 0){
                System.out.println("You are not Assigned in Any Task");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean resetPassword(String username, String newPassword) {

        String getPasswordQuery = "SELECT Password FROM User WHERE Username = ? AND Role = 'ProjectManager'";
        String resetPasswordQuery = "UPDATE User SET Password = ? WHERE Username = ? AND Role = 'ProjectManager'";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement getPwdStmt = conn.prepareStatement(getPasswordQuery);
                 PreparedStatement resetPwdStmt = conn.prepareStatement(resetPasswordQuery)) {

                // Get the current password
                getPwdStmt.setString(1, username);
                ResultSet rs = getPwdStmt.executeQuery();

                if (rs.next()) {
                    String currentPassword = rs.getString("Password");

                    if (currentPassword.equals(newPassword)) {
                        System.out.println("New password must be different from the current password.");
                        System.out.println("Enter the Different Password to Change.");
                        return false;
                    }

                    resetPwdStmt.setString(1, newPassword);
                    resetPwdStmt.setString(2, username);

                    int updatedRows = resetPwdStmt.executeUpdate();

                    if (updatedRows > 0) {
//                        System.out.println("Password reset successfully for username: " + username);
                        return true;
                    } else {
                        System.out.println("Failed to reset password. Username or role might be incorrect.");
                        return false;
                    }
                } else {
                    System.out.println("Username not found.");
                    return false;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Client> getClients() {
        String getClientsQuery = "SELECT * FROM Client";
        List<Client> clients = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getClientsQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int client_id = rs.getInt("ClientID");
                String client_name = rs.getString("ClientName");
                String contact_information = rs.getString("ContactInformation");
                clients.add(new Client(client_id,client_name, contact_information));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public int getClientIDByName(String clientName) {

        String getClientIDQuery = "SELECT ClientID FROM Client WHERE ClientName = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(getClientIDQuery)) {

            stmt.setString(1, clientName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ClientID");
            } else {
                System.out.println("Client not found.");
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean createProject(String projectName, String projectDetails, int clientID,
                                 java.sql.Timestamp startDate, java.sql.Timestamp dueDate, int pmid) {

        String createProjectQuery = "INSERT INTO Project (ProjectName, ProjectDetails, ClientID, StartDate, DueDate) " +
                "VALUES (?, ?, ?, ?, ?);";
        String assignProjectQuery = "INSERT INTO ProjectManager (ProjectID, PMID) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(createProjectQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmt = conn.prepareStatement(assignProjectQuery)) {

            stmt.setString(1, projectName);
            stmt.setString(2, projectDetails);
            stmt.setInt(3, clientID);
            stmt.setTimestamp(4, startDate);
            stmt.setTimestamp(5, dueDate);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {

                ResultSet generatedKeys = stmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    int projectId = generatedKeys.getInt(1);

                    pstmt.setInt(1, projectId);
                    pstmt.setInt(2, pmid);
                    pstmt.executeUpdate();

//                    System.out.println("ProjectID and PMID inserted successfully.");
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createMilestone(String milestoneName, String milestoneDescription) {
        String createMilestoneQuery = "INSERT INTO Milestone (MilestoneName, MilestoneDescription) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(createMilestoneQuery)) {

            stmt.setString(1, milestoneName);
            stmt.setString(2, milestoneDescription);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getProjectsByUsername(String username) {
        String select_project_by_username =
                "SELECT DISTINCT p.ProjectName " +
                        "FROM User u " +
                        "JOIN ProjectManager pa ON u.UserID = pa.PMID " +
                        "JOIN Project p ON pa.ProjectID = p.ProjectID " +
                        "WHERE u.Username = ?";
        List<String> projectNames = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(select_project_by_username)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String projectName = rs.getString("ProjectName");
                projectNames.add(projectName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectNames;
    }

    @Override
    public int getProjectIDByName(String projectName) {
        String select_project_id_by_name = "SELECT ProjectID FROM Project WHERE ProjectName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(select_project_id_by_name)) {

            stmt.setString(1, projectName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("ProjectID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getUserIDByUsername(String username) {
        String select_user_id_by_username = "SELECT UserID FROM User WHERE Username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(select_user_id_by_username)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void assignProjectToUser(int projectID, int pmid, int userID) {
        String insert_project_assigned = "INSERT INTO ProjectAssigned (ProjectID, PMID, AssignedAt) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insert_project_assigned)) {

            stmt.setInt(1, projectID);
            stmt.setInt(2, pmid);
            stmt.setInt(3, userID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertClient(Client client) {

        String insert_client_sql = "INSERT INTO Client (ClientName, ContactInformation) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(insert_client_sql)) {

            preparedStatement.setString(1, client.getClient_name());
            preparedStatement.setString(2, client.getContact_information());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getUsernamesByProjectAndPM(int projectId, int pmId) {

        String select_username_by_projectid_and_pmid_sql =
                "SELECT U.Username FROM User U " +
                        "JOIN ProjectAssigned PA ON U.UserID = PA.AssignedAt " +
                        "WHERE PA.ProjectID = ? AND PA.PMID = ?";

        List<String> usernames = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(select_username_by_projectid_and_pmid_sql)) {

            preparedStatement.setInt(1, projectId);
            preparedStatement.setInt(2, pmId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usernames.add(resultSet.getString("Username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernames;
    }

    @Override
    public List<MileStone> getAllMilestones() {

        String select_all_milestones_sql = "SELECT * FROM Milestone";
        List<MileStone> milestones = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(select_all_milestones_sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("MilestoneID");
                String name = resultSet.getString("MilestoneName");
                String description = resultSet.getString("MilestoneDescription");

                MileStone milestone = new MileStone(id, name, description);
                milestones.add(milestone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return milestones;
    }

    @Override
    public int getMilestoneIDByName(String milestoneName) {
        String select_milestone_id_by_name_sql = "SELECT MilestoneID FROM Milestone WHERE MilestoneName = ?";
        int milestoneID = -1;

        try (PreparedStatement preparedStatement = conn.prepareStatement(select_milestone_id_by_name_sql)) {

            preparedStatement.setString(1, milestoneName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                milestoneID = resultSet.getInt("MilestoneID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return milestoneID;
    }

    @Override
    public int insertTask(String taskName, String taskDetails, int projectID, int assignedTo, int milestoneID) {
        String insert_task_sql = "INSERT INTO Task (TaskName, TaskDetails, TaskStatus, ProjectID, AssignedTo, MilestoneID) VALUES (?, ?, 'To Do', ?, ?, ?)";
        String select_last_insert_id_sql = "SELECT LAST_INSERT_ID()";
        int taskID = -1;

        try (PreparedStatement insertStatement = conn.prepareStatement(insert_task_sql);
             PreparedStatement selectStatement = conn.prepareStatement(select_last_insert_id_sql)) {

            insertStatement.setString(1, taskName);
            insertStatement.setString(2, taskDetails);
            insertStatement.setInt(3, projectID);
            insertStatement.setInt(4, assignedTo);
            insertStatement.setInt(5, milestoneID);

            insertStatement.executeUpdate();

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                taskID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taskID;
    }

    @Override
    public void insertUserActivity(int userID, int taskID, int milestoneID) {
        String insert_user_activity_sql = "INSERT INTO UserActivity (UserID, TaskID, MilestoneID) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insert_user_activity_sql)) {

            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, taskID);
            preparedStatement.setInt(3, milestoneID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
