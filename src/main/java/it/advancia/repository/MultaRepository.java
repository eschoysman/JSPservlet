/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.Multa;
import it.advancia.utility.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Lavoro
 */
public class MultaRepository {
    
    private static Connection connection;
    private static MultaRepository multaRepository;
    
    private MultaRepository(){}
    public static MultaRepository getMultaRepository(){
    
        if(multaRepository == null) multaRepository = new MultaRepository();
        return multaRepository;
    }
    public void save(Multa multa){
    
        Connection conn = getConnection();
        try {
            
            PreparedStatement prepareStatement = conn.prepareStatement("Insert into \"Multa\"(\"tipo\", \"importo\", \"idAnagrafica\") VALUES(?,?,?)");
        
            prepareStatement.setString(1, multa.getTipo());
            prepareStatement.setDouble(2, multa.getImporto());
            prepareStatement.setLong(3, multa.getIdAnagrafica());
            
            prepareStatement.execute();
            
        }catch(Exception e){e.printStackTrace();}
    
    }
    
    private Connection getConnection() {
        if (connection == null) {
            connection = DBConnector.getConnection();
        }
        return connection;
    }    

}
