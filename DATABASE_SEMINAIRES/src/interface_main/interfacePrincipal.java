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
import reservations.Personne;
import reservations.Reservation;

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
                        System.out.println("creation requete");
                        transaction.ajouter_seminaire(sem);
                    } 
                    else if(scanned.equals("C")){
                        Conference conf = Saisie.une_conference();
                        System.out.println("creation requete");
                        transaction.ajouter_conference(conf);
                    }
                    else if(scanned.equals("E")){
                        Evenement ev = Saisie.un_evenement();
                        System.out.println("creation requete");
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
            System.out.println("Tapez P: Pour ajout de Personne/Client");
            System.out.println("Tapez Q: Pour Quit");

            scanned = input.nextLine();


                if(scanned.equals("I")){
                    System.out.println("Saisie d'une reservation");
                    Reservation reserv = Saisie.une_reservation();
                    transaction.ajouter_reservation(reserv);
                } 
                else if(scanned.equals("A")){
                    System.out.println("Annuler la reservation");
                    Reservation reserv = Saisie.une_reservation();
                    transaction.annuler_reservation(reserv);
                }
                else if(scanned.equals("P")){
                    Personne pers = Saisie.une_personne();
                    transaction.ajouter_personne(pers);
                }
            System.out.println("**********Retour Reserv/annul ***********");
            }while(!scanned.equals("Q"));
    }

    
    public static void actionsEditer(Connection conn) throws SQLException{
        To_db transaction = new To_db(conn);

        Scanner input = new Scanner(System.in);
        String scanned;

        do{
            System.out.println("Tapez C: Confimer Programme Seminaire et Evenement");
            System.out.println("Tapez D: Confirmer prestataires/conferences");
            System.out.println("Tapez Q: Pour Quit");

            scanned = input.nextLine();


                if(scanned.equals("C")){
                    System.out.println("Confirmation du programme l'evenement (30j)");
                    Evenement ev = Saisie.look_evenement();
                    transaction.confirmer_clos(ev, "CLOS_30J");
                } 
                else if(scanned.equals("D")){
                    System.out.println("Confirmer reservations/prestataire/support conference (7j)");
                    Evenement ev = Saisie.look_evenement();
                    transaction.annulation_ev(ev);
                    
                }
            System.out.println("**********Retour Edition ***********");
            }while(!scanned.equals("Q"));
    }
}