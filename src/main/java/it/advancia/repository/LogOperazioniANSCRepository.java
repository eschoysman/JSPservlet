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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
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
        logOperazioniANSC.setIdOperazioneANSC(executeQuery.getInt("idOperazioneANSC"));
        logOperazioniANSC.setIdOperazioneComune(executeQuery.getInt("idOperazioneComune"));
        logOperazioniANSC.setIdRiferimento(executeQuery.getString("idRiferimento"));
        logOperazioniANSC.setCodiceOperazioneANSC(executeQuery.getString("codiceOperazioneANSC"));
        logOperazioniANSC.setNome(executeQuery.getString("nome"));
        logOperazioniANSC.setCognome(executeQuery.getString("cognome"));
        logOperazioniANSC.setEseguita(executeQuery.getString("eseguita"));
        logOperazioniANSC.setNote(executeQuery.getString("note"));
        logOperazioniANSC.setOperatore(executeQuery.getString("operatore"));
        logOperazioniANSC.setData(executeQuery.getDate("data"));
        logOperazioniANSC.setIdAtto(executeQuery.getLong("idAtto"));
        logOperazioniANSC.setIdOperazioneAnnANSC(executeQuery.getInt("idOperazioneAnnANSC"));
        logOperazioniANSC.setIdOperazioneAnnComune(executeQuery.getInt("idOperazioneAnnComune"));
        logOperazioniANSC.setFileName(executeQuery.getString("fileName"));
        logOperazioniANSC.setAttachment(executeQuery.getBinaryStream("attachment"));
        
        return logOperazioniANSC;
    }
    
    public void save(LogOperazioniANSC newLogOperazioniANSC) {        
        Connection conn = getConnection();
        try {
            String insertQuery = "Insert into \"LogOperazioniANSC\"(\"idOperazioneANSC\",\"idOperazioneComune\",\"idRiferimento\",\"codiceOperazioneANSC\",\"operatore\",\"data\",\"eseguita\",\"idAtto\",\"note\",\"idOperazioneAnnANSC\",\"idOperazioneAnnComune\",\"nome\",\"cognome\",\"attachment\",\"fileName\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            // Statement.RETURN_GENERATED_KEYS permette di recuperare la chiave dopo aver eseguito la query
            PreparedStatement prepareStatement = conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setLong(1,	newLogOperazioniANSC.getIdOperazioneANSC());
            prepareStatement.setLong(2,	newLogOperazioniANSC.getIdOperazioneComune());
            prepareStatement.setString(3,	newLogOperazioniANSC.getIdRiferimento());
            prepareStatement.setString(4,	newLogOperazioniANSC.getCodiceOperazioneANSC());
            prepareStatement.setString(5,	newLogOperazioniANSC.getOperatore());
            prepareStatement.setDate(6,	newLogOperazioniANSC.getData()!=null ? new Date(newLogOperazioniANSC.getData().getTime()) : null);
            prepareStatement.setString(7,	newLogOperazioniANSC.getEseguita());
            prepareStatement.setLong(8,	newLogOperazioniANSC.getIdAtto());
            prepareStatement.setString(9,	newLogOperazioniANSC.getNote());
            prepareStatement.setLong(10,	newLogOperazioniANSC.getIdOperazioneAnnANSC());
            prepareStatement.setLong(11,	newLogOperazioniANSC.getIdOperazioneAnnComune());
            prepareStatement.setString(12,	newLogOperazioniANSC.getNome());
            prepareStatement.setString(13,	newLogOperazioniANSC.getCognome());
            prepareStatement.setBinaryStream(14,newLogOperazioniANSC.getAttachment());
            prepareStatement.setString(15,	newLogOperazioniANSC.getFileName());
            // eseguo la query di insert
            prepareStatement.execute();
            // recupero la chiave dell'oggetto dopo il suo inseritìmento in tabella
            ResultSet result = prepareStatement.getGeneratedKeys();
            if(result.next()) {
                int idArchivio = result.getInt("idArchivio");
                System.out.println("idArchivio generato: "+idArchivio);
                newLogOperazioniANSC.setIdArchivio(idArchivio);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveAttachment(LogOperazioniANSC logOperazioniANSC) {
        Connection conn = getConnection();
        
        try {
            PreparedStatement prepareStatement = conn.prepareStatement("UPDATE \"LogOperazioniANSC\" SET \"attachment\"=?, \"fileName\"=? WHERE \"idArchivio\"=?");
            prepareStatement.setBinaryStream(1, logOperazioniANSC.getAttachment());
            prepareStatement.setString(2, logOperazioniANSC.getFileName());
            prepareStatement.setLong(3, logOperazioniANSC.getIdArchivio());
            prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LogOperazioniANSC getBlob(long idArchivio){
        Connection conn = getConnection();
        
        InputStream in;
        
        try {
            Statement createStatement = conn.createStatement();
            
            ResultSet executeQuery = createStatement.executeQuery("select \"fileName\", \"attachment\" FROM \"LogOperazioniANSC\" WHERE \"idArchivio\" = " + idArchivio);
            
            if(executeQuery.next()){
                in = executeQuery.getBinaryStream("attachment");
                String fileName = executeQuery.getString("fileName");
                
                LogOperazioniANSC logOperazioniANSC = new LogOperazioniANSC();
                logOperazioniANSC.setAttachment(in);
                logOperazioniANSC.setFileName(fileName);
                return logOperazioniANSC; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<String> getAllDistinctIdRiferimento(){
        
        Connection conn = getConnection();
        try {
            
            List<String> idRiferimentoList = new ArrayList<>();
            Statement stm = conn.createStatement();
            
            ResultSet executeQuery = stm.executeQuery("select distinct(\"idRiferimento\") from \"LogOperazioniANSC\" WHERE \"idRiferimento\" IS NOT NULL AND \"idRiferimento\" NOT IN (select \"idRiferimento\" FROM \"Anagrafica\" WHERE \"idRiferimento\" IS NOT NULL ) ");
            while(executeQuery.next()){
                idRiferimentoList.add(executeQuery.getString(1));
            }
            return idRiferimentoList;
            
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
