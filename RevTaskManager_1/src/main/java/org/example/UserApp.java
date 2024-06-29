package org.example;

import org.example.model.User;
import org.example.service.UserService;
import java.util.Scanner;

public class UserApp {
    private UserService userService;
    private Scanner scanner;

    public UserApp() {
        userService = new UserService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
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

    private void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.loginUser(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName());
        } else {
            System.out.println("User not found. Please register.");
        }
    }

    private void register() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter gender (male/female/other): ");
        String gender = scanner.nextLine();
        System.out.print("Enter Role (user/admin/projectmanager): ");
        String role = scanner.nextLine();

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setGender(gender);
        newUser.setRole(role);

        if (userService.registerUser(newUser)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
