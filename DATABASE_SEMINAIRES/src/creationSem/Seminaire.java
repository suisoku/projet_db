/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creationSem;

import java.util.ArrayList;

/**
 *
 * @author sassiab
 */
public class Seminaire {
    
    private String theme;
    private Boolean dejeuner;
    private int animateur;
    private double tarif = 0;
    private ArrayList<Creneau> creneaux = new ArrayList();
    private ArrayList<Conference> conferences = new ArrayList();
    
    public Seminaire() {};
    
    public void ajouterTheme(String theme){
        this.theme = theme;
    }
    public void ajouterDejeuner(boolean dej){
        this.dejeuner = dej;
    }
    
    public void ajouterAnimateur(int anim){ 
        this.animateur = anim;
    }
    
    public void ajouterCreneau(Creneau c){
        creneaux.add(c);
    }
    
    public void ajouterConference(Conference conf){
        conferences.add(conf);
    }
    
    public void ajouterTarif( double tar){
        this.tarif = tar;
    }
    
    public String getTheme(){ return theme;}
    public boolean getDejeuner(){ return dejeuner;}
    public int getAnimateur(){ return animateur;}
    public Creneau getCreneau(int index){ return creneaux.get(index);}
    public Conference getConference(int index){return conferences.get(index);}
    public double getTarif(){ return tarif; }
}
