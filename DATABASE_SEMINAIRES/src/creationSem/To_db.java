/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creationSem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import reservations.Personne;
import reservations.Reservation;

/**
 *
 * @author Nord_38
 */
public class To_db {
    protected Connection conn;

    /******************** Constructor prend la connection pour tous ces methodes *************************/
    public To_db(Connection connect){this.conn = connect;}
    
    
    /******************** AJOUTER SEMINAIRE *************************/
    public void ajouter_seminaire(Seminaire sem) throws SQLException{
        Statement st = this.conn.createStatement(); 
        int id;
            
        /******************** AJOUTER SEMINAIRE *************************/
            ResultSet result_idsem = st.executeQuery("SELECT max(id_sem) from SEMINAIRES");
            
            if (result_idsem.next()){
                id = result_idsem.getInt(1) +1 ;
                result_idsem.close();
            result_idsem.close();
            }else   {
                System.out.println("no data found");
                id = 0;
            }
            
            st.executeUpdate("INSERT INTO SEMINAIRES VALUES ('"
                    + id + "','" 
                    + sem.getTheme() + "','"
                    + sem.getDejeuner() + "','"
                    + sem.getAnimateur() + "')"); 
                    
            
         /******************** AJOUTER ACTIVITES CRENEAU 1/2 *************************/
         
         
            /** RECHERCHE ID POUR INCREMENTER **/
            ResultSet result_idact = st.executeQuery("SELECT max(id_act) from ACTIVITEES");
            int id_act;
            
            if (result_idact.next()){
                id_act = result_idact.getInt(1);
                result_idact.close();
            }else   {
                System.out.println("no data found");
                id_act= 0;
            }
            
            // *** AJOUTE CRENEAU DANS CRENEAU ET ACTIVITES CORRESPONDANTES ***/
            for(int i = 0 ;  i < sem.getCreneauxSize() ; i++){
                
                st.executeUpdate("INSERT INTO CRENEAUX VALUES ('"
                    + id + "','" 
                    + sem.getCreneau(i).getMoment()  + "')");
               

                id_act++;
                
                
                st.executeUpdate("INSERT INTO ACTIVITEES VALUES ('"
                        + id_act + "','" 
                        + sem.getCreneau(i).getNom_act1() + "','" 
                        + id + "','" 
                        + sem.getCreneau(i).getMoment()+ "')");           

                id_act++;

                st.executeUpdate("INSERT INTO ACTIVITEES VALUES ('"
                    + id_act + "','" 
                    + sem.getCreneau(i).getNom_act2() + "','" 
                    + id + "','" 
                    + sem.getCreneau(i).getMoment()+ "')");  

                id_act++;

                st.executeUpdate("INSERT INTO ACTIVITEES VALUES ('"
                    + id_act + "','" 
                    + sem.getCreneau(i).getNom_act3() + "','" 
                    + id + "','" 
                    + sem.getCreneau(i).getMoment()+ "')");  
            }
            st.close();
    }
    
    
    
    /******************** AJOUTER Conference *************************/
    public void ajouter_conference(Conference conf) throws SQLException{
        Statement st = this.conn.createStatement(); 

        st.executeUpdate("INSERT INTO CONFERENCES VALUES ('"
                        + conf.getNumeroConf() + "','" 
                        + conf.getId_sem() + "','" 
                        + conf.getTitre() + "','" 
                        + conf.getMontant() + "','"
                        + conf.getSupport() + "')");  
        st.close();
    }
    
    /******************** AJOUTER Evenement
     * @param ev
     * @throws java.sql.SQLException *************************/
    
    public void ajouter_evenement(Evenement ev) throws SQLException{
        Statement st = this.conn.createStatement(); 

        
        st.executeUpdate("INSERT INTO EVENEMENTS VALUES ('"
                        + ev.getId_sem() 
                        + "',to_date('" + ev.getDate() + "', 'DD/MM/YYYY'),'" 
                        + ev.getNomSalle() + "','"
                        + ev.getId_prest()+ "','" 
                        + ev.getTarif()+ "','AVAILABLE')"); 
       //st.executeUpdate("INSERT INTO EVENEMENTS VALUES ('1', to_date('22/10/2018', 'DD/MM/YYYY') , '2A' , '0', '22','AVAILABLE')");
       st.close();
    }
     
  
    /******************** AJOUTER Reservation *************************/
     
    public void ajouter_reservation(Reservation reserv) throws SQLException{
        Statement st = this.conn.createStatement(); 

        st.executeUpdate("INSERT INTO RESERVATIONS VALUES ('"
                        + reserv.getId_personne() + "','" 
                        + reserv.getId_sem() 
                        + "',to_date('" + reserv.getDate_sem() + "', 'DD/MM/YYYY'),sysdate(), 'CONFIRME' )");  
        st.close();
    }
    
    public void ajouter_personne(Personne pers) throws SQLException{
        Statement st = this.conn.createStatement(); 
        int id;
            
        
        ResultSet result_idpers = st.executeQuery("SELECT max(id_pers) from PERSONNES");
            
        if (result_idpers.next()){
            id = result_idpers.getInt(1) +1 ;
            result_idpers.close();
        }else   {
            System.out.println("no data found");
            id = 0;
        }
        st.executeUpdate("INSERT INTO PERSONNES VALUES ('"
                + id + "','" 
                + pers.getNom() + "','" 
                + pers.getPrenom() + "','" 
                + pers.getAdresse() + "','" 
                + pers.getTel() + "','" 
                + pers.getMail() + "')");
        st.close();
    }
}

