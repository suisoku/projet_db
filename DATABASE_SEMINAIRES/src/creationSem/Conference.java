/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creationSem;

/**
 *
 * @author Nord_38
 */
public class Conference {
    private int numeroConf;
    private int id_sem;
    private String titre;
    private int montant;
    private String Support;

    public int getNumeroConf() {
        return numeroConf;
    }

    public void setNumeroConf(int numeroConf) {
        this.numeroConf = numeroConf;
    }

    public int getId_sem() {
        return id_sem;
    }

    public void setId_sem(int id_sem) {
        this.id_sem = id_sem;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getSupport() {
        return Support;
    }

    public void setSupport(String Support) {
        this.Support = Support;
    }
    
    
    
}
