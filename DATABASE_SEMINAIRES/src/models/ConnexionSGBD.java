package models;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import creationSem.Conference;
import creationSem.Evenement;
import creationSem.Seminaire;
import creationSem.To_db;
import interface_main.Saisie;
import interface_main.interfacePrincipal;
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
             Scanner input = new Scanner(System.in);
             String scanned;
                do{
                    System.out.println("Tapez 1: Groupe d'actions seminaire/etc");
                    System.out.println("Tapez 2: Groupe d'actions reservations/annulations");
                    System.out.println("Tapez 3: Groupe d'actions editer/confirmer evenement etc");
                    System.out.println("Tapez Q: Pour Quit");
                    
                    scanned = input.nextLine();
                    
                    if(scanned.equals("1")){
                        interfacePrincipal.actionsSeminaire(conn);
                    }
                    else if(scanned.equals("2")){
                        interfacePrincipal.actionsReservation(conn);
                    }
                    else if(scanned.equals("3")){
                        interfacePrincipal.actionsEditer(conn);
                    }
                    System.out.println("**********Retour MenuActions ***********");
                }while(!scanned.equals("Q"));
               
            
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
