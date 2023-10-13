/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.repository;

import it.advancia.model.Anagrafica;
import it.advancia.model.LogOperazioniANSC;
import it.advancia.model.Multa;
import it.advancia.utility.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Multa> recuperaPerAnagrafica(long idAnagrafica) {
        Connection conn = getConnection();
        try { 
            PreparedStatement prepareStatement = conn.prepareStatement("SELECT * FROM \"Multa\" WHERE \"idAnagrafica\"=?");
            prepareStatement.setLong(1, idAnagrafica);
            prepareStatement.execute();
            ResultSet resultSet = prepareStatement.executeQuery();
            
            List<Multa> multe = new ArrayList<>();
            
            while(resultSet.next()) {
                multe.add(convertToMulta(resultSet));
            }
            return multe;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public double getTotaleMultePerAnagrafica(long idAnagrafica) {
        Connection conn = getConnection();
        try { 
            PreparedStatement prepareStatement = conn.prepareStatement("SELECT COALESCE(SUM(\"importo\")) as totaleMulte FROM \"Multa\" WHERE \"idAnagrafica\"=?");
            prepareStatement.setLong(1, idAnagrafica);
            prepareStatement.execute();
            ResultSet resultSet = prepareStatement.executeQuery();
            
            List<Multa> multe = new ArrayList<>();
            double totaleMulte = 0;
            
            while(resultSet.next()) {
                totaleMulte = resultSet.getDouble("totaleMulte");
            }
            return totaleMulte;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private Multa convertToMulta(ResultSet resultSet) throws SQLException {
        Multa multa = new Multa();
        multa.setId(resultSet.getLong("id"));
        multa.setIdAnagrafica(resultSet.getLong("idAnagrafica"));
        multa.setTipo(resultSet.getString("tipo"));
        multa.setImporto(resultSet.getDouble("importo"));
        return multa;
    }
    
    private Connection getConnection() {
        if (connection == null) {
            connection = DBConnector.getConnection();
        }
        return connection;
    }    

}
