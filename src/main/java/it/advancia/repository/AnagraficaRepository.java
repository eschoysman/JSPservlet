/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.Anagrafica;
import it.advancia.model.LogOperazioniANSC;
import it.advancia.utility.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lavoro
 */
public class AnagraficaRepository {
    
    private static AnagraficaRepository anagraficaRepository;
    private static Connection connection;
    
    private AnagraficaRepository(){}
    
    public static AnagraficaRepository getAnagraficaRepository(){
        if(anagraficaRepository == null){
            anagraficaRepository = new AnagraficaRepository();
        }
        return anagraficaRepository;
    }
    
    
    public boolean save(Anagrafica anagrafica){
    
        Connection conn = getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement("Insert INTO \"Anagrafica\" (\"nome\",\"cognome\",\"dataNascita\",\"luogoNascita\", \"idRiferimento\") VALUES(?,?,?,?,?)");
            stm.setString(1, anagrafica.getNome());
            stm.setString(2, anagrafica.getCognome());
            stm.setDate(3, java.sql.Date.valueOf(anagrafica.getDataNascita()));
            stm.setString(4, anagrafica.getLuogoNascita());
            stm.setString(5, anagrafica.getIdRiferimento());
            stm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public Map<Anagrafica, List<LogOperazioniANSC>> getAllAnagraficheWithLogOperazioniANSC(){
    
        Map<Anagrafica, List<LogOperazioniANSC>> anagraficaConLog = new HashMap<>();
    
                Connection conn = getConnection();
        try {
            
            Statement stm = conn.createStatement();
            String sql = "select \"id\", \"Anagrafica\".\"nome\" as \"nome\", \"Anagrafica\".\"cognome\" as \"cognome\", \"dataNascita\",  \"luogoNascita\", \"Anagrafica\".\"idRiferimento\" as \"idRiferimento\", \"idArchivio\"  from \"Anagrafica\" LEFT JOIN \"LogOperazioniANSC\" ON \"Anagrafica\".\"idRiferimento\" = \"LogOperazioniANSC\".\"idRiferimento\" WHERE \"Anagrafica\".\"idRiferimento\" IS NOT NULL";

            ResultSet executeQuery = stm.executeQuery(sql);
            
            while(executeQuery.next()){
                
                Anagrafica anagrafica = new Anagrafica();
                anagrafica.setId(executeQuery.getLong("id"));
                anagrafica.setNome(executeQuery.getString("nome"));
                anagrafica.setCognome(executeQuery.getString("cognome"));
                anagrafica.setLuogoNascita(executeQuery.getString("luogoNascita"));
                anagrafica.setDataNascita(executeQuery.getDate("dataNascita").toString());
                anagrafica.setIdRiferimento(executeQuery.getString("idRiferimento"));
                
                LogOperazioniANSC loansc = new LogOperazioniANSC();
                loansc.setIdArchivio(executeQuery.getLong("idArchivio"));
                
                anagraficaConLog.computeIfAbsent(anagrafica, key -> new ArrayList<>() ).add(loansc);
            }
            return anagraficaConLog;
            
        } catch (SQLException ex) {
            Logger.getLogger(LogOperazioniANSCRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    
    }
    
    
    private Connection getConnection(){
    
        if(connection == null) 
            connection = DBConnector.getConnection();
        
        return connection;
    }
    
}
