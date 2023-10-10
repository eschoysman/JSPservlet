/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.LogOperazioniANSC;
import it.advancia.model.utility.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<LogOperazioniANSC> executeQueryWhere(String where){
    
        Connection conn = getConnection();
        List<LogOperazioniANSC> operazioni = new ArrayList<>();

        try {
            Statement createStatement = conn.createStatement();

            if(where.isEmpty()) where = "true";
            
            ResultSet executeQuery = createStatement.executeQuery(String.format( "Select * From \"LogOperazioniANSC\" WHERE %s", where) );
                        
            String debugString = "";
            
            
            while(executeQuery.next()){
            
                LogOperazioniANSC logOperazioniANSC = new LogOperazioniANSC();
                
                
                logOperazioniANSC.setIdArchivio( executeQuery.getInt("idArchivio"));
                logOperazioniANSC.setDate(executeQuery.getDate("data").toString());
                logOperazioniANSC.setIdOperazioneANSC(executeQuery.getInt("idOperazioneANSC"));
                logOperazioniANSC.setNote(executeQuery.getString("note"));
                operazioni.add(logOperazioniANSC);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return operazioni;
    }
    
    
        private Connection getConnection() {

        if (connection == null) {

            connection = DBConnector.getConnection();

        }
        return connection;

    }
    
}
