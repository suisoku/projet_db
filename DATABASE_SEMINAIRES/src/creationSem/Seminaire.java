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
    private ArrayList<Creneau> creneaux;
    private ArrayList<Conference> conferences;
    
    public Seminaire() {};
    
    public void ajouterTheme(String theme){}
    public void ajouterDejeuner(boolean dej){}
    public void ajouterAnimateur(int anim){ this.animateur = anim;}
    public void ajouterCreneau(Creneau c){}
    public void ajouterConference(Conference conf){}
    
    
    public String getTheme(){ return "";}
    public boolean getDejeuner(){ return false;}
    public int getAnimateur(){ return 0;}
    public ArrayList<Creneau> getCreneau(){ return null;}
    public ArrayList<Conference> getConference(){return null;}
    
}
