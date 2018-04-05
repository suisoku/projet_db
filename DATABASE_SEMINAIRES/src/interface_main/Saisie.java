/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_main;

import creationSem.Conference;
import creationSem.Creneau;
import creationSem.Seminaire;
import java.util.Scanner;

/**
 *
 * @author Nord_38
 */
public class Saisie {
    
    
    public static Seminaire un_seminaire() {
        Seminaire sem = new Seminaire();
        Scanner input = new Scanner(System.in);
        
        /*** SAISIE DES DIFFERENTS DONNES CONERCERNANT UN SEMINAIRE ***/
        /*************************************************************/
        
        System.out.println("Veuillez saisir le theme:");
        sem.ajouterTheme(input.nextLine());
        
        System.out.println("Veuillez saisir le numero de l'Animateur:");
        sem.ajouterAnimateur(input.nextInt());
        input.nextLine(); // to avoid skipping issues after next int
        
        System.out.println("Voulez vous un dejeuner quoi qu'il arrive ? O/N ");
        
        if(input.nextLine().equals("O")){
            sem.ajouterDejeuner("TRUE");
        }
        else sem.ajouterDejeuner("FALSE"); // peut etre overrided en cas d'une journée complete
        
       // System.out.println("saisir Tarif:");
       // sem.ajouterTarif(input.nextInt());
      //  input.nextLine(); // to avoid skipping issues after next int
        
        System.out.println("Saisir votre premier creneau:");
        System.out.println("Saisir le moment du creneau 1, Matin / Apresmidi [Matin|Aprem] :");
        
        Creneau cren1 = new Creneau();
        Creneau cren2;
        
        cren1.ajouterMoment(input.nextLine());
        
        System.out.println("Nom activité 1:");
        cren1.ajouterAct(input.nextLine(), 1);
        
        System.out.println("Nom activité 2:");
        cren1.ajouterAct(input.nextLine(), 2);
        
        System.out.println("Nom activité 3:");
        cren1.ajouterAct(input.nextLine(), 3);
        
        sem.ajouterCreneau(cren1);
        
        System.out.println("Voulez vous saisir un deuxieme creneau ? O|N :");
        if(input.nextLine().equals("O")){
            cren2 = new Creneau();
            
            cren2.ajouterMoment(input.nextLine());
        
            System.out.println("Nom activité 1:");
            cren2.ajouterAct(input.nextLine(), 1);
        
            System.out.println("Nom activité 2:");
            cren2.ajouterAct(input.nextLine(), 2);
        
            System.out.println("Nom activité 3:");
            cren2.ajouterAct(input.nextLine(), 3);
            
            sem.ajouterCreneau(cren2);
        }
        /*
        System.out.println("Saisir une conference (Au moins 1 obligatoire):");
            Conference conf = new Conference();
            System.out.println("1.a Saisir le numero du conferencier:");
               conf.setNumeroConf(input.nextInt());
               input.nextLine(); // to avoid skipping issues after next int
            System.out.println("1.b Saisir le nom de la conference:");
                conf.setTitre(input.nextLine());
            System.out.println("1.b Saisir le montant de la conference:");
                conf.setMontant(input.nextInt());
                input.nextLine(); // to avoid skipping issues after next int
            System.out.println("1.b Avez vous le support maintenent ? O|N):");
            
            if(input.nextLine().equals("O") )conf.setSupport(true);
            else conf.setSupport(true);
            
            sem.ajouterConference(conf);
          */          
        return sem;
    }
    
}
