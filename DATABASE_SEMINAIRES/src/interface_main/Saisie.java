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
    
    /*** SAISIE DES DIFFERENTS DONNES CONERCERNANT UN SEMINAIRE ***/
    /*************************************************************/
        
    public static Seminaire un_seminaire() {
        Seminaire sem = new Seminaire();
        Scanner input = new Scanner(System.in);
        

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
            
            System.out.println("Saisir le moment du creneau 1, Matin / Apresmidi [Matin|Aprem] :");
            cren2.ajouterMoment(input.nextLine());
        
            System.out.println("Nom activité 1:");
            cren2.ajouterAct(input.nextLine(), 1);
        
            System.out.println("Nom activité 2:");
            cren2.ajouterAct(input.nextLine(), 2);
        
            System.out.println("Nom activité 3:");
            cren2.ajouterAct(input.nextLine(), 3);
            
            
            
            System.out.println("Fin saisie");
            sem.ajouterCreneau(cren2);
        }
        return sem;
    }
       
        
    
    public static Conference une_conference(){
        Conference conf = new Conference();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Saisir une conference (Au moins 1 obligatoire par seminaire):");
            
        System.out.println("1.a Saisir le numero du seminaire:");
            conf.setId_sem(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("1.a Saisir le numero du conferencier:");
            conf.setNumeroConf(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("1.b Saisir le nom de la conference:");
            conf.setTitre(input.nextLine());
            
        System.out.println("1.b Saisir le montant de la conference:");
            conf.setMontant(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("1.b Avez vous le support maintenent ? O|N):");  
            if(input.nextLine().equals("O") )conf.setSupport("TRUE");
            else conf.setSupport("FALSE");
        
        return conf;
    }
}
