/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_main;

import creationSem.Creneau;
import creationSem.Seminaire;
import java.util.Scanner;

/**
 *
 * @author Nord_38
 */
public abstract class saisie_sem {
    
    
    public Seminaire saisie_seminaire() {
        Seminaire sem = new Seminaire();
        Scanner input = new Scanner(System.in);
        
        /*** SAISIE DES DIFFERENTS DONNES CONERCERNANT UN SEMINAIRE ***/
        /*************************************************************/
        
        System.out.println("Veuillez saisir le theme:");
        sem.ajouterTheme(input.nextLine());
        
        System.out.println("Veuillez saisir le numero de l'Animateur:");
        sem.ajouterAnimateur(input.nextInt());
        
        
        System.out.println("Voulez vous un dejeuner quoi qu'il arrive ? O/N ");
        
        if(input.nextLine().equals("O")){
            sem.ajouterDejeuner(true);
        }
        else sem.ajouterDejeuner(false); // peut etre overrided en cas d'une journée complete
        
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
        }
        
        
        
        
        return sem;
    }
    
}
