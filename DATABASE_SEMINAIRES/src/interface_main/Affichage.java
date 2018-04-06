/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interface_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Last_Pacifist
 */
public class Affichage {
    protected Connection conn;

    /******************** Constructor prend la connection pour tous ces methodes *************************/
    public Affichage(Connection connect){this.conn = connect;}
    
    
    public  void afficher_animateurs(){}
    public  void afficher_conferenciers(){}
    public  void afficher_prestataires(){}
    public  void afficher_salles(){}
    public  void afficher_seminaires(){}
    public  void afficher_evenements(){}
    public  void afficher_reservations() {
        
        
        Scanner input = new Scanner(System.in);
        int id_seminaire;
        String date_sem;
            
        /******************** Affichage*************************/
        System.out.println("Saisir l'id seminaire [1/2 Evenement]");
            id_seminaire = input.nextInt();
            input.nextLine(); // to avoid skipping issues after next int
        System.out.println("Saisir  la date du seminaire[1/2 Evenement] dd/mm/yyyy");
            date_sem = input.nextLine();
            
            ResultSet result;
        try { 
            Statement st = this.conn.createStatement(); 
            result = st.executeQuery("SELECT id_pers, id_sem, date_sem , theme , date_r from RESERVATIONS natural join SEMINAIRES WHERE id_sem="
                    + id_seminaire +
                    " AND date_sem=to_date('" + date_sem + "', 'DD/MM/YYYY')");
            
            while (result.next()) {
                System.out.println("Numéro Personne : " + result.getInt(1));
                System.out.println("Numero Seminaire : " + result.getInt(2));
                System.out.println("Seminaire Theme: " + result.getString(4));
                System.out.println("Date evenement : " + result.getString(3));
                System.out.println("Date de reservation : " + result.getString(5));
                System.out.println();
            }
            result.close();
             st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Affichage.class.getName()).log(Level.SEVERE, null, ex);
        }
            

       

    }
    
    
    public  void afficher_depense(){}
    public  void afficher_conferences(){}
    public  void afficher_recettes(){}
}
