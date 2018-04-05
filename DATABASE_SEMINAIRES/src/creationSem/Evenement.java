/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creationSem;

/**
 *
 * @author Last_Pacifist
 */
public class Evenement {
    private int id_sem;
    private String nomSalle;
    private int id_prest;
    private int tarif;
    private String date;
    
    
    public void setId_sem(int id_sem) {
        this.id_sem = id_sem;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public void setId_prest(int id_prest) {
        this.id_prest = id_prest;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    /******** GETTERS ****/
    
    
    public int getId_sem() {
        return id_sem;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getId_prest() {
        return id_prest;
    }

    public int getTarif() {
        return tarif;
    }

    public String getDate() {
        return date;
    }
    
    
    
    
    
}
