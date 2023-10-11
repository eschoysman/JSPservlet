/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.LogOperazioniANSC;
import it.advancia.utility.DBConnector;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jfr.events.FileWriteEvent;

/**
 *
 * @author Lavoro
 */
public class LogOperazioniANSCRepository {
    
    private static Connection connection;
    private static LogOperazioniANSCRepository logOperazioniANSCRepository;
    
    private LogOperazioniANSCRepository() {
    }
    
    public static LogOperazioniANSCRepository getLogOperazioniANSCRepository() {
        
        if (logOperazioniANSCRepository == null) {
            logOperazioniANSCRepository = new LogOperazioniANSCRepository();
        }
        
        return logOperazioniANSCRepository;
    }
    
    public List<LogOperazioniANSC> executeQueryWhere(String where) {
        
        Connection conn = getConnection();
        List<LogOperazioniANSC> operazioni = new ArrayList<>();
        
        try {
            Statement createStatement = conn.createStatement();
            
            ResultSet executeQuery = createStatement.executeQuery(String.format("Select * From \"LogOperazioniANSC\" WHERE %s", where));
            
            while(executeQuery.next()) {
                LogOperazioniANSC logOperazioniANSC = convertToLogOperazioniANSC(executeQuery);                
                operazioni.add(logOperazioniANSC);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return operazioni;
    }
    
    private LogOperazioniANSC convertToLogOperazioniANSC(ResultSet executeQuery) throws SQLException {
        LogOperazioniANSC logOperazioniANSC = new LogOperazioniANSC();

        logOperazioniANSC.setIdArchivio(executeQuery.getInt("idArchivio"));
        logOperazioniANSC.setDate(executeQuery.getDate("data") == null ? null : executeQuery.getDate("data").toString());
        logOperazioniANSC.setIdOperazioneANSC(executeQuery.getInt("idOperazioneANSC"));
        logOperazioniANSC.setNote(executeQuery.getString("note"));
        logOperazioniANSC.setFileName(executeQuery.getString("fileName"));
        
        System.out.println("\n"+logOperazioniANSC.getFileName()+"\n");
        
        return logOperazioniANSC;
    }
    
    public int save(LogOperazioniANSC newLogOperazioniANSC) {
        Connection conn = getConnection();
        try {
            PreparedStatement prepareStatement = conn.prepareStatement("Insert into \"LogOperazioniANSC\"(\"note\", \"attachment\", \"fileName\") VALUES(?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1,newLogOperazioniANSC.getNote());
            prepareStatement.setBinaryStream(2, newLogOperazioniANSC.getFileStream());
            prepareStatement.setString(3, newLogOperazioniANSC.getFileName());
            prepareStatement.execute();
            ResultSet result = prepareStatement.getGeneratedKeys();
            if(result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public void saveBlobFromInputStream(InputStream in, String name){
    
        Connection conn = getConnection();
        
        try {
            PreparedStatement prepareStatement = conn.prepareStatement("Insert into \"LogOperazioniANSC\"(\"attachment\", \"fileName\") VALUES(?, ?)");
            
            prepareStatement.setBinaryStream(1, in);
            prepareStatement.setString(2, name);
            prepareStatement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public LogOperazioniANSC getBlob(int idArchivio){
        Connection conn = getConnection();
        
        InputStream in;
        
        try {
            
            
            Statement createStatement = conn.createStatement();
            
            ResultSet executeQuery = createStatement.executeQuery("select \"fileName\", \"attachment\" FROM \"LogOperazioniANSC\" WHERE \"idArchivio\" = " + idArchivio);
            
            if(executeQuery.next()){
            
                in = executeQuery.getBinaryStream("attachment");
                String fileName = executeQuery.getString("fileName");
                
                LogOperazioniANSC logOperazioniANSC = new LogOperazioniANSC();
                logOperazioniANSC.setFileStream(in);
                logOperazioniANSC.setFileName(fileName);
                return logOperazioniANSC;
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    private Connection getConnection() {
        
        if (connection == null) {
            
            connection = DBConnector.getConnection();
            
        }
        return connection;
        
    }
    
}
