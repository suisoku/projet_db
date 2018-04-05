/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creationSem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nord_38
 */
public class add_to_db {


    public static void ajouter_seminaire(Connection conn, Seminaire sem) throws SQLException{
        try (Statement req = conn.createStatement()) {
            ResultSet result_idsem = req.executeQuery("SELECT max(id_sem) from SEMINAIRES");
            
            int id;
            if (result_idsem.next()){
                id = result_idsem.getInt(1) + 1;
                req.executeUpdate("INSERT INTO SEMINAIRES VALUES ('"
                        + id + "','" 
                        + sem.getTheme() + "','"
                        + sem.getTarif() + "','"
                        + sem.getDejeuner() + "','"
                        + sem.getAnimateur() + "')");
            }
            else   System.out.println("no data found");
            
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
        
        
        
   
    }
    
}

