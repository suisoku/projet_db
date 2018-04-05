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
    private String dejeuner;
    private int animateur;
    private ArrayList<Creneau> creneaux = new ArrayList();
    private ArrayList<Conference> conferences = new ArrayList();
    
    public Seminaire() {};
    
    public void ajouterTheme(String theme){
        this.theme = theme;
    }
    public void ajouterDejeuner(String dej){
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
    
    
    public String getTheme(){ return theme;}
    public String getDejeuner(){ return dejeuner;}
    public int getAnimateur(){ return animateur;}
    public Creneau getCreneau(int index){ return creneaux.get(index);}
    public Conference getConference(int index){return conferences.get(index);}
    public int getCreneauxSize(){ return this.creneaux.size(); }
}
