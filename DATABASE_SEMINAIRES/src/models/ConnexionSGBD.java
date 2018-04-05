package models;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import creationSem.Seminaire;
import creationSem.add_to_db;
import interface_main.Saisie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionSGBD {

    private static final String configurationFile = "BD.properties";

    public static void main(String args[]) {
        try {
            String jdbcDriver, dbUrl, username, password;
            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            jdbcDriver = dap.getJdbcDriver();
            dbUrl = dap.getDatabaseUrl();
            username = dap.getUsername();
            password = dap.getPassword();
            // Load the database driver
            Class.forName(jdbcDriver);// Get a connection to the database
            try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
                
            // Etape de saisie
                Seminaire sem = Saisie.un_seminaire();
                
            //Etape de ajout base de donne  
               add_to_db.ajouter_seminaire(conn, sem);  
               
            // Print information about connection warnings
                SQLWarningsExceptions.printWarnings(conn);
            }
        } catch (SQLException se) {
            // Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
        } catch (ClassNotFoundException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
