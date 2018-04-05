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
    private String titre;
    private int montant;
    private boolean Support;

    public int getNumeroConf() {
        return numeroConf;
    }

    public void setNumeroConf(int numeroConf) {
        this.numeroConf = numeroConf;
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

    public boolean isSupport() {
        return Support;
    }

    public void setSupport(boolean Support) {
        this.Support = Support;
    }
    
    
    
}
