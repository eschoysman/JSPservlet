/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.Anagrafica;
import it.advancia.utility.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            stm.setString(5, anagrafica.getIdRiferimentoString());
            stm.execute();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    
    private Connection getConnection(){
    
        if(connection == null) 
            connection = DBConnector.getConnection();
        
        return connection;
    }
    
}
