/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.model.utility;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Lavoro
 */
public class DBConnector {

    private static Connection conn;
    
    
    public static Connection getConnection(){
    
        if(conn == null){
            
            try {

                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost/Jspservlet", "postgres", "password");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        }
    
        return conn;
        
    }
}
