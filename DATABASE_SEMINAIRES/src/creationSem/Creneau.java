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
public class Creneau {
    
    private String moment;
    
    private String nom_act1;
    private String nom_act2;
    private String nom_act3;
    
    public void ajouterMoment(String mom){
        this.moment = mom;
    }
    
    public void ajouterAct(String act , int numero){
        if(numero == 1)this.nom_act1 = act;
        if(numero == 2)this.nom_act2 = act;
        if(numero == 3)this.nom_act3 = act;
    }
    
    public String getMoment(){
        return this.moment;
    }
}
