/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_main;

import creationSem.Conference;
import creationSem.Evenement;
import creationSem.Seminaire;
import creationSem.To_db;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Last_Pacifist
 */
public class interfacePrincipal {
    
    
    public static void actionsSeminaire(Connection conn) throws SQLException{
            To_db transaction = new To_db(conn);
            
            Scanner input = new Scanner(System.in);
            String scanned;
            
            do{
                System.out.println("Tapez S: Pour ajout de seminaire");
                System.out.println("Tapez C: Pour ajout de Conference");
                System.out.println("Tapez E: Pour ajout de Evenement");
                System.out.println("Tapez Q: Pour Quit");
            
                scanned = input.nextLine();
                
            
                    if(scanned.equals("S")){
                        Seminaire sem = Saisie.un_seminaire();
                        transaction.ajouter_seminaire(sem);
                    } 
                    else if(scanned.equals("C")){
                        Conference conf = Saisie.une_conference();
                        transaction.ajouter_conference(conf);
                    }
                    else if(scanned.equals("E")){
                        Evenement ev = Saisie.un_evenement();
                        transaction.ajouter_evenement(ev);
                    }
                System.out.println("**********Retour Actions seminaires ***********");
                }while(!scanned.equals("Q"));
    }
    
    public static void actionsReservation(Connection conn) throws SQLException{
        To_db transaction = new To_db(conn);

        Scanner input = new Scanner(System.in);
        String scanned;

        do{
            System.out.println("Tapez I: Faire une reservation");
            System.out.println("Tapez A: Annuler une reservation");
            //System.out.println("Tapez E: Pour ajout de Evenement");
            System.out.println("Tapez Q: Pour Quit");

            scanned = input.nextLine();


                if(scanned.equals("I")){
                    Seminaire sem = Saisie.un_seminaire();
                    transaction.ajouter_seminaire(sem);
                } 
                else if(scanned.equals("A")){
                    Conference conf = Saisie.une_conference();
                    transaction.ajouter_conference(conf);
                }
              /*  else if(scanned.equals("E")){
                    Evenement ev = Saisie.un_evenement();
                    transaction.ajouter_evenement(ev);
                }*/
            System.out.println("**********Retour Reserv/annul ***********");
            }while(!scanned.equals("Q"));
    }
}
