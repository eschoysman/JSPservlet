/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.User;
import it.advancia.model.utility.DBConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Lavoro
 */
public class UserRepository {

    private static Connection connection;
    private static UserRepository userRepository = null;

    private UserRepository() {
    }

    public static UserRepository getUserRepository() {

        if (userRepository == null) {
            userRepository = new UserRepository();
        }

        return userRepository;
    }

    public User getUserByUsername(String username) {

        try {
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet executeQuery = stat.executeQuery(String.format("Select * From \"User\" Where \"username\" = '%s'", username));

            if (executeQuery.next()) {
                System.out.println(
                        executeQuery.getDate("birthDate")
                );

                User foundUser = new User();
                foundUser.setUsername(username);
                foundUser.setPassword(executeQuery.getString("password"));
                foundUser.setBirthDate(executeQuery.getDate("birthDate"));

                return foundUser;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean saveUser(User newuser) {
        try {
            Connection conn = getConnection();
            Statement stat = conn.createStatement();

            if (getUserByUsername(newuser.getUsername()) == null) {

                stat.execute(
                        String.format("Insert INTO \"User\" (\"username\", \"password\", \"birthDate\") Values('%s', '%s', '%s')",
                                newuser.getUsername(), newuser.getPassword(), newuser.getBirthDate()));

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    private Connection getConnection() {

        if (connection == null) {

            connection = DBConnector.getConnection();

        }
        return connection;

    }
}
