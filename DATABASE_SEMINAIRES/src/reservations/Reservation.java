/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservations;

/**
 *
 * @author Nord_38
 */
public class Reservation {
    private int id_personne;
    private int id_sem;
    private String date_sem;

    public int getId_personne() {
        return id_personne;
    }
    public int getId_sem() {
        return id_sem;
    }
    public String getDate_sem() {
        return date_sem;
    }
    /*********** GETTER *******/
    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }
    public void setId_sem(int id_sem) {
        this.id_sem = id_sem;
    }
    public void setDate_sem(String date_sem) {
        this.date_sem = date_sem;
    }
    
    
    
    
    
}
