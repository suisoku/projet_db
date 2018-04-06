/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_main;

import creationSem.Conference;
import creationSem.Creneau;
import creationSem.Evenement;
import creationSem.Seminaire;
import java.util.Scanner;
import reservations.Personne;
import reservations.Reservation;

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
            
        System.out.println("1.b Saisir le numero du conferencier:");
            conf.setNumeroConf(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("1.c Saisir le nom de la conference:");
            conf.setTitre(input.nextLine());
            
        System.out.println("1.d Saisir le montant de la conference:");
            conf.setMontant(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("1.e Avez vous le support maintenent ? O|N):");  
            if(input.nextLine().equals("O") )conf.setSupport("TRUE");
            else conf.setSupport("FALSE");
        
        return conf;
    }
    
    
    public static Evenement un_evenement(){
        Evenement ev = new Evenement();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Saisie d'un evenement");
            
        System.out.println("Saisir le numero du seminaire:");
            ev.setId_sem(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("Saisir la date de representation dd/mm/yyyy");
            ev.setDate(input.nextLine());
            
        System.out.println("Saisir le id prestataire :");
            ev.setId_prest(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("Saisir le nom de la salle");
            ev.setNomSalle(input.nextLine());
            
        System.out.println("Sasir le tarif voulu , si null calculé automatiquement");  
             ev.setTarif(input.nextInt());
             input.nextLine(); // to avoid skipping issues after next int
        
        return ev;
    }
    
    public static Reservation une_reservation(){
        Reservation reserv = new Reservation();
        Scanner input = new Scanner(System.in);
        
        
            
        System.out.println("Saisir le id du client (si non: le saisir au prealable grace a 'P'");
            reserv.setId_personne(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("Saisir l'evenement [id seminaire 1/2]:");
            reserv.setId_sem(input.nextInt());
            input.nextLine(); // to avoid skipping issues after next int
            
        System.out.println("Saisir l'evenement [date seminaire 2/2] [dd/mm/yyy):");
            reserv.setDate_sem(input.nextLine());
            
        return reserv;
    }
    
    public static Personne une_personne(){
        Personne pers = new Personne();
        Scanner input = new Scanner(System.in);
        
        System.out.println("Saisie d'une personne/client");
            
        System.out.println("Saisir le Nom");
            pers.setNom(input.nextLine());
        System.out.println("Saisir le Prenom");
            pers.setPrenom(input.nextLine());
            
        System.out.println("Saisir le Adresse");
            pers.setAdresse(input.nextLine());
            
        System.out.println("Saisir le Tel");
            pers.setTel(input.nextLine());
            
        System.out.println("Saisir le Mail");
            pers.setMail(input.nextLine());
           
        return pers;
    }

    static Evenement look_evenement() {
        Evenement ev = new Evenement();
            Scanner input = new Scanner(System.in);

            System.out.println("Saisie d'un evenement");

            System.out.println("Saisir le numero du seminaire:");
                ev.setId_sem(input.nextInt());
                input.nextLine(); // to avoid skipping issues after next int

            System.out.println("Saisir la date de representation dd/mm/yyyy");
                ev.setDate(input.nextLine());
                
            return ev;
    }
    
    
    
}
