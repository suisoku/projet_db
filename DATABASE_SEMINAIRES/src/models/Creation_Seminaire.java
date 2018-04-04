/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import java.text.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Creation_Seminaire {

    public static void creer_seminaire(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Statement stmt = conn.createStatement();

        //----------------------Animateur----------------------
        System.out.println("Veuillez saisir un animateur:");
        int animateur = sc.nextInt();

        try (ResultSet rs_nom_anim = stmt.executeQuery("select nom from animateurs join  personnes on (id_anim = id_pers) where id_anim =" + animateur)) {
            if (rs_nom_anim.next() ) System.out.println("id_anim selectionné " + rs_nom_anim.getString(1));
            else   System.out.println("no data found");
            
            rs_nom_anim.close();
             stmt.close();
        }
    }

}



        /*
            while (id_anim > id_animMax) {
            System.out.println("id anim Max est "+id_animMax);
            System.out.println("Veuillez saisir un animateur:");
            id_anim = sc.nextInt();
            }
            rs1.close();
            //----------------------Prestataire----------------------
            System.out.println("Veuillez saisir un Prestataire:");
            int id_Prest = sc.nextInt();
            ResultSet rs2 = stmt.executeQuery("select max(idAnim) from animateur");
            while (id_Prest > rs.getInt(1)) {
            System.out.println("Veuillez saisir un animateur:");
            id_Prest = sc.nextInt();
            }
            rs2.close();
            //--------------------Num Salle----------------------------
            System.out.println("Veuillez saisir le numero de la salle ");
            int numSalle = sc.nextInt();
            //------------------le theme -----------------
            System.out.println("Veuillez saisir le theme de seminaire:");
            String theme = sc.nextLine();
            //-------------------------Le nombre de participant------------------
            System.out.println("Veuillez saisir le nombre maximum de partcipants ");
            int nbMaxPart = sc.nextInt();
            //-------------- Le tarif de seminaire ------------------------------
            System.out.println("Veuillez saisir le tarif");
            double tarif = sc.nextDouble();
            //--------------------Duree---------------------------------
            System.out.println("Veuillez saisir la duree");
            String duree = sc.nextLine();
            while (!(duree.equals("JOURNEE")) || !(duree.equals("MATIN")) || !(duree.equals("APRES-MIDI"))) {
            System.out.println("Veuillez saisir la duree");
            duree = sc.nextLine();
            }
            //-----------------la date de seminaire ------------------------
            System.out.println("Veuillez saisir la date de seminaire:");
            String dateSem = sc.nextLine();
            DateFormat df = new SimpleDateFormat("dd-MMM-yy");
            Date d = null;
            try {
            
            d = df.parse(dateSem);
            
            } catch (ParseException e) {
            System.out.println("Impossible de parser "
            + dateSem
            + ". Veuillez entrer la date au format JJ-MMM-AA");
            }
            DateFormat df3 = new SimpleDateFormat("dd-MMM-yy");
            String s3 = df3.format(d);
            stmt.executeUpdate("INSERT INTO seminaire VALUES(" + "'" + id_sem + "'"
            + "," + "'" + id_anim + "'" + "," + "'" + id_Prest + "'" + "," + "'" + numSalle
            + "'" + "," + "'" + theme + "'" + "," + "'" + nbMaxPart + "'" + "," + "'" + tarif + "'" + ","
            + "'" + duree + "'" + "," + "'" + dateSem + "'" + ")");
            System.out.println("Ajout réussi");
            rs.close();
            stmt.close();
            
            System.out.println("********************************************************\n"); */