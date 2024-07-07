package org.example;

import org.example.model.Client;
import org.example.model.MileStone;
import org.example.model.User;
import org.example.model.UserActivity;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class UserApp {

    private static UserService userService;
    private static Scanner scanner;

    public UserApp() {
        userService = new UserService();
        scanner = new Scanner(System.in);
    }

    private final static Logger logger = LoggerFactory.getLogger(UserApp.class);

    public static void start() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    flag = login();
//                    updatePass();
//                    deleteIdd();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        User user = userService.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getUser_name() +" as "+user.getRole());
            handleUserRole(user);
            return false;
        } else {
            System.out.println("Invalid username or password.");
            System.out.println("or, Please Register.");
            return true;
        }

    }

    private static void register() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scan.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scan.nextLine().trim();

        System.out.print("Enter role (Admin, ProjectManager, TeamMember): ");
        String role = scan.nextLine().trim();

        System.out.print("Enter status (Active, Inactive): ");
        String status = scan.nextLine().trim();

        System.out.print("Enter access level (Admin, ProjectManager, TeamMember): ");
        String accessLevel = scan.nextLine().trim();

        if (userService.registerUser(username, password, role, status, accessLevel)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private static void handleUserRole(User user) {
        switch (user.getRole().toLowerCase()) {
            case "admin":
                handleAdminFunctions();
                break;
            case "projectmanager":
                handleProjectManagerFunctions(user);
                break;
            case "teammember":
                handleTeamMemberFunctions(user);
                break;
            default:
                System.out.println("Invalid role.");
        }
    }

    private static void handleAdminFunctions() {
        while (true) {
            System.out.println("\n=== Admin Functions ===");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Deactivate User");
            System.out.println("4. Assign Access Levels");
            System.out.println("5. Track User Activity");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    register();
                    break;

                case 2:
                    updateUserAdmin();
                    break;
                case 3:
                    deactivateUserAdmin();
                    break;
                case 4:
                    assignAccessLevel();
                    break;
                case 5:
                    userActivity();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    start();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleProjectManagerFunctions(User user) {
        while (true) {
            System.out.println("\n=== Project Manager Functions ===");
            System.out.println("1. Reset Password");
            System.out.println("2. Create Client information");
            System.out.println("3. Manage client information And Create Project");
            System.out.println("4. Create MileStones");
            System.out.println("5. Add team members to projects");
            System.out.println("6. Assign tasks to team members");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    resetPasswordSecure(user);
                    break;
                case 2:
                    craeteClient();
                    break;
                case 3:
                    manageClientProject(user);
                    break;
                case 4:
                    createMileStone();
                    break;
                case 5:
                    addTeammembersToProjects(user);
                    break;
                case 6:
                    assignTasksToTeamMember(user);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    start();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleTeamMemberFunctions(User user) {
        while (true) {
            System.out.println("\n=== Team Member Functions ===");
            System.out.println("1. View project details and assigned tasks");
            System.out.println("2. Update the status of assigned tasks");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    viewProjectDetailsAssignedTask(user.getUser_name());
                    break;
                case 2:
                    updateTaskStatusActivityTimestamp(user.getUser_name());
                    break;
                case 3:
                    System.out.println("Logging out...");
                    start();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void updateUserAdmin(){
        System.out.println("------User Names------");
        UserService userService = new UserService();
        userService.getprintUserNamesByRole();
        System.out.println("------Admin Names------");
        userService.getprintAdminNamesByRole();
        System.out.println("-----------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName/AdminName to update: ");
        String username = scanner.nextLine();

        System.out.print("Enter new Password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Enter new Role (Admin, ProjectManager, TeamMember): ");
        String newRole = scanner.nextLine();

        System.out.print("Enter new Status (Active, Inactive): ");
        String newStatus = scanner.nextLine();

        userService.getupdateUserByUsername(username, newPassword, newRole, newStatus);

    }

    public static void deactivateUserAdmin(){
        System.out.println("------User Names------");
        UserService userService = new UserService();
        userService.getprintUserNamesByRole();
        System.out.println("------Admin Names------");
        userService.getprintAdminNamesByRole();
        System.out.println("-----------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName/AdminName to Deactivate: ");
        String username = scanner.nextLine();
        userService.deleteUserAdminByUsername(username);
    }

    public static void assignAccessLevel(){
        System.out.println("------User Names------");
        UserService userService = new UserService();
        userService.getprintUserNamesByRole();
        System.out.println("------Admin Names------");
        userService.getprintAdminNamesByRole();
        System.out.println("-----------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter UserName/AdminName to update access level: ");
        String username = scanner.nextLine();

        System.out.print("Enter new Access Level (ProjectManager, TeamMember): ");
        String newAccessLevel = scanner.nextLine();

        userService.updateAccessLevelByUsername(username, newAccessLevel);

    }

    private static void userActivity() {
        System.out.println("------User Names------");
        UserService userService = new UserService();
        userService.getprintUserNamesByRole();
        System.out.println("-----------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username for which user you want : ");
        String username = scanner.nextLine();

        List<UserActivity> userActivities = userService.getUserActivities(username);
        if (userActivities.isEmpty()) {
            System.out.println("---No user activities Found---");
        }

        for (UserActivity activity : userActivities) {
//            System.out.println("ActivityID: " + activity.getActivity_id());
            System.out.println("Username: " + activity.getUser().getUser_name());
            System.out.println("Role: " + activity.getUser().getRole());
            System.out.println("TaskName: " + activity.getTask().getTask_name());
            System.out.println("TaskDetails: " + activity.getTask().getTask_details());
            System.out.println("TaskStatus: " + activity.getTask().getTask_status());
            System.out.println("MilestoneName: " + activity.getMilestone().getMilestone_name());
            System.out.println("MilestoneDescription: " + activity.getMilestone().getMilestone_description());
            System.out.println("ActivityTimestamp: " + activity.getActivity_timestamp());
            System.out.println("-------------------------------------------------------------");
        }
    }

    public static  void viewProjectDetailsAssignedTask(String username){
        UserService userService = new UserService();
        userService.getgetProjectDetails(username);
    }

   public static void updateTaskStatusActivityTimestamp(String username){
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter the Task Status ('To Do', 'In Progress', 'Done') :  ");
       String task_status = scanner.nextLine();

       UserService userService = new UserService();
       userService.getupdateTaskAndUserActivity(username,task_status);
   }

   public static void resetPasswordSecure(User user){
       boolean new_password = true;
       while(new_password){
           Scanner scanner = new Scanner(System.in);

           System.out.print("Enter new password: ");
           String newPassword = scanner.nextLine();

           UserService userService = new UserService();
           boolean passwordReset = userService.getresetPassword(user.getUser_name(), newPassword);

           if (!passwordReset) {
           } else {
               System.out.println(user.getUser_name() + " Password reset successfully!");
               new_password = false;
           }
       }
   }

    public static void craeteClient(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();

        System.out.print("Enter contact information: ");
        String contactInformation = scanner.nextLine();

        Client newClient = new Client(1,clientName, contactInformation);
        UserService userService = new UserService();
        userService.getinsertClient(newClient);

        System.out.println("Client inserted successfully.");
    }

   public static void manageClientProject(User user){

       dispalyClientInfo();
       Scanner scanner = new Scanner(System.in);

       System.out.print("Enter client name: ");
       String clientName = scanner.nextLine().trim();

       System.out.print("Enter project name: ");
       String projectName = scanner.nextLine().trim();

       System.out.print("Enter project details: ");
       String projectDetails = scanner.nextLine().trim();

       System.out.print("Enter project start date (yyyy-mm-dd hh:mm:ss): ");
       String startDateStr = scanner.nextLine().trim();

       System.out.print("Enter project due date (yyyy-mm-dd hh:mm:ss): ");
       String dueDateStr = scanner.nextLine().trim();

       Timestamp startDate = parseTimestamp(startDateStr);
       Timestamp dueDate = parseTimestamp(dueDateStr);

       if (startDate == null || dueDate == null) {
           System.out.println("Invalid date format. Please use 'yyyy-mm-dd hh:mm:ss'.");
           return;
       }

       UserService userService = new UserService();
       int clientID = userService.getgetClientIDByName(clientName);

       if (clientID != -1) {
           boolean projectCreated = userService.getcreateProject(projectName, projectDetails, clientID, startDate, dueDate, user.getUser_id());
           if (projectCreated) {
               System.out.println("Project created successfully!");
           } else {
               System.out.println("Failed to create project.");
           }
       } else {
           System.out.println("Failed to find client.");
       }
   }

    private static Timestamp parseTimestamp(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new Timestamp(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void dispalyClientInfo(){
        UserService userService = new UserService();
        List<Client> clients = userService.getgetClients();

        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Client client : clients) {
                System.out.println("Client Name: " + client.getClient_name());
                System.out.println("Contact Information: " + client.getContact_information());
                System.out.println("-------------------------------------");
            }
        }
    }

    public static void createMileStone(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter milestone name: ");
        String milestoneName = scanner.nextLine();

        System.out.print("Enter milestone description: ");
        String milestoneDescription = scanner.nextLine();

        UserService userService = new UserService();

        boolean milestoneCreated = userService.getcreateMilestone(milestoneName, milestoneDescription);
        if (milestoneCreated) {
            System.out.println("Milestone created successfully!");
        } else {
            System.out.println("Failed to create milestone.");
        }
    }

    public static void addTeammembersToProjects(User user){
        System.out.println("------User Names------");
        UserService userService = new UserService();
        userService.getprintUserNamesByRole();
        System.out.println("-----------------------");

//        UserService userService = new UserService();
        List<String> projects = userService.getgetProjectsByUsername(user.getUser_name());

        System.out.println("Projects for Project Manager '" + (user.getUser_name()) + "':");
        for (String projectName : projects) {
            System.out.println(projectName);
        }
        System.out.println("-----------------------");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Project Name: ");
        String projectName = scanner.nextLine().trim();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        int projectID = userService.getgetProjectIDByName(projectName);
        int userID = userService.getgetUserIDByUsername(username);

        if (projectID != -1 && userID != -1) {
            userService.getassignProjectToUser(projectID, user.getUser_id(), userID);
            System.out.println("Project '" + projectName + "' assigned to user '" + username + "'.");
        } else {
            System.out.println("Project or User not found.");
        }

    }

    public static void assignTasksToTeamMember(User user){
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        List<String> projects = userService.getgetProjectsByUsername(user.getUser_name());

        System.out.println("Projects for Project Manager '" + (user.getUser_name()) + "':");
        for (String projectName : projects) {
            System.out.println(projectName);
        }
        System.out.println("-----------------------");

        System.out.print("Enter Project Name: ");
        String projectName = scanner.nextLine().trim();

        int projectID = userService.getgetProjectIDByName(projectName);

        List<String> usernames = userService.getgetUsernamesByProjectAndPM(projectID, user.getUser_id());

        System.out.println("Team Members assigned to this project :");
        for (String username : usernames) {
            System.out.println(username);
        }
        System.out.println("-----------------------");

        List<MileStone> milestones = userService.getgetAllMilestones();

        System.out.println("Milestone Details:");
        for (MileStone milestone : milestones) {
//            System.out.println("ID: " + milestone.getMilestoneID());
            System.out.println("Name: " + milestone.getMilestone_name());
            System.out.println("Description: " + milestone.getMilestone_description());
            System.out.println("---------------------------");
        }

        System.out.print("Enter username to Assign Task : ");
        String username = scanner.nextLine();

        System.out.print("Enter milestone name for this Task : ");
        String milestoneName = scanner.nextLine();


        int userID = userService.getgetUserIDByUsername(username);
        int milestoneID = userService.getgetMilestoneIDByName(milestoneName);

        if (userID == -1 || milestoneID == -1) {
            System.out.println("Invalid username or milestone name.");
            return;
        }

        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter task details: ");
        String taskDetails = scanner.nextLine();

        int taskID = userService.getinsertTask(taskName, taskDetails, projectID, userID, milestoneID);

        if (taskID == -1) {
            System.out.println("Failed to create task.");
            return;
        }

        userService.getinsertUserActivity(userID, taskID, milestoneID);

        System.out.println("Task created and user activity recorded successfully.");
    }


}
