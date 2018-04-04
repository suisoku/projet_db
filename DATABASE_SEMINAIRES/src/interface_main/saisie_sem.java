/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_main;

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
        
        System.out.println("Veuillez saisir le theme:");
        sem.ajouterTheme(input.nextLine());
        
        System.out.println("Veuillez saisir le numero de l'Animateur:");
        sem.ajouterAnimateur(input.nextInt());
        
        
        System.out.println("Voulez vous un dejeuner quoi qu'il arrive ? OUI / * ");
        
        if(input.nextLine() == "OUI"){
            sem.ajouterDejeuner(true);
        }
        else sem.ajouterDejeuner(false); // peut etre overrided en cas d'une journée complete
        
        
        return sem;
    }
    
}
