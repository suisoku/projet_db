package models;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import creationSem.Conference;
import creationSem.Seminaire;
import creationSem.To_db;
import interface_main.Saisie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
            Connection conn = DriverManager.getConnection(dbUrl, username, password) ;
                
           
                
            //Etape de ajout base de donne 
            To_db transaction = new To_db(conn);
            
            Scanner input = new Scanner(System.in);
            String scanned;
            
            do{
                System.out.println("Tapez S: Pour ajout de seminaire");
                System.out.println("Tapez C: Pour ajout de Conference");
                System.out.println("Tapez E: Pour Exit");
            
                scanned = input.nextLine();
                
            
                    if(scanned.equals("S")){
                        Seminaire sem = Saisie.un_seminaire();
                        transaction.ajouter_seminaire(sem);
                    } 
                    else if(scanned.equals("C")){
                        Conference conf = Saisie.une_conference();
                        transaction.ajouter_conference(conf);
                    }
                    
                }while(!scanned.equals("E"));
                
               
            
            // Print information about connection warnings
                SQLWarningsExceptions.printWarnings(conn);
                
            conn.close();
        } catch (SQLException se) {
            // Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
        } catch (ClassNotFoundException e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            
        }
    }
}
