package org.example.DAO;

import org.example.connection.DBConnection;
import org.example.model.User;

import java.sql.*;

public class UserDAOImpl {

    Connection conn;

    public UserDAOImpl(){
        this.conn = DBConnection.getConnection();
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user (email, password, name, gender, role) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setString(4, user.getGender());
        statement.setString(5, user.getRole());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
//        DBConnection.closeConnection();
        return rowInserted;
    }

    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?;";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int userid = resultSet.getInt("userid");
            String name = resultSet.getString("name");
            String gender = resultSet.getString("gender");
            String role = resultSet.getString("role");

            user = new User();
            user.setUserid(userid);
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setGender(gender);
            user.setRole(role);
        }

        resultSet.close();
        statement.close();
//        DBConnection.closeConnection();

        return user;
    }
}