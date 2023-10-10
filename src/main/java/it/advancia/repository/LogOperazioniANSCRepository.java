/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.utility.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lavoro
 */
public class LogOperazioniANSCRepository {
    
    private static Connection connection;
    private static LogOperazioniANSCRepository logOperazioniANSCRepository;
    
    private LogOperazioniANSCRepository(){}
    
    
    public static LogOperazioniANSCRepository getLogOperazioniANSCRepository(){
    
        if(logOperazioniANSCRepository == null) logOperazioniANSCRepository = new LogOperazioniANSCRepository();
    
        
        return logOperazioniANSCRepository;
    }
    
    public void executeQueryWhere(String where){
    
        Connection conn = getConnection();
        
        try {
            Statement createStatement = conn.createStatement();
            
            ResultSet executeQuery = createStatement.executeQuery(String.format( "Select * From \"LogOperazioniANSC\" WHERE %s", where) );
                        
            String debugString = "";
            
            while(executeQuery.next()){
            
                debugString = executeQuery.getString("idArchivio");
            
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
        private Connection getConnection() {

        if (connection == null) {

            connection = DBConnector.getConnection();

        }
        return connection;

    }
    
}
