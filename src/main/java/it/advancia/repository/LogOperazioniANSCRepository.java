/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.utility.DBConnector;
import java.sql.Connection;

/**
 *
 * @author Lavoro
 */
public class LogOperazioniANSCRepository {
    
    private static Connection connection;
    private static LogOperazioniANSCRepository logOperazioniANSCRepository;
    
    private LogOperazioniANSCRepository(){}
    
    
    public LogOperazioniANSCRepository getLogOperazioniANSCRepository(){
    
        if(logOperazioniANSCRepository == null) logOperazioniANSCRepository = new LogOperazioniANSCRepository();
    
        
        return logOperazioniANSCRepository;
    }
    
    void executeQueryWhere(String where){
    
        Connection conn = getConnection();
        
        
    
    }
    
    
        private Connection getConnection() {

        if (connection == null) {

            connection = DBConnector.getConnection();

        }
        return connection;

    }
    
}
