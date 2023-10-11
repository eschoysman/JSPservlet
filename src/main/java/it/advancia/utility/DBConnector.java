/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 *
 * @author Lavoro
 */
public class DBConnector {

    private static Connection conn;

    private DBConnector() {}

    public static Connection getConnection() {

        if (conn == null) {
            try {

                InitialContext ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");

                conn = ds.getConnection();
                System.out.println("\ndb connection successful\n");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;

    }
}
