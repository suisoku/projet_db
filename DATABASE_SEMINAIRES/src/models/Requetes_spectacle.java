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
public class Requetes_spectacle {

    private static Object scanner;

    /**
     * Afficher toutes les informations sur tous les spectacles.
     *
     * @param conn connexion � la base de donn�es
     * @throws SQLException en cas d'erreur d'acc�s � la base de donn�es
     */
    public static void req1(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles");
        while (rs.next()) {
            System.out.println("Numéro Spectacle : " + rs.getInt(1));
            System.out.println("Nom Spectacle : " + rs.getString(2));
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");

    }

    public static void req2(Connection conn) throws SQLException {

        int id_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro de spectacle:");
        id_spec = sc.nextInt();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOMS FROM LesSpectacles WHERE NUMS = " + id_spec);
        if (rs.next()) {
            //while (rs.next()) {
            System.out.println("Nom Spectacle : " + rs.getString(1));
            //}
        } else {
            System.out.println("Aucun spectacle correspondant à votre demande");
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");
    }

    public static void req3(Connection conn) throws SQLException {
        String nom_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un nom de spectacle:");
        nom_spec = sc.nextLine();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NUMS FROM LesSpectacles WHERE UPPER(NOMS) = '" + nom_spec.toUpperCase() + "'");
        if (rs.getRow() < 0) {
            System.out.println("Aucun spectacle correspondant à votre demande");
        } else {
            while (rs.next()) {
                System.out.println("Numéro Spectacle : " + rs.getString(1));
            }
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");

    }

    public static void req4(Connection conn) throws SQLException {
        int id_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro de spectacle:");
        id_spec = sc.nextInt();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOMS FROM LesSpectacles WHERE NUMS = " + id_spec);
        if (rs.next()) {
            while (rs.next()) {
                System.out.println("Nom Spectacle : " + rs.getString(1));
            }
            ResultSet rs1 = stmt.executeQuery("SELECT DATEREP FROM LesRepresentations WHERE NUMS = " + id_spec);
            if (rs1.next()) {
                while (rs1.next()) {
                    System.out.println("Date représentation: " + rs1.getString(1));
                }
            } else {
                System.out.println("Aucune représentation correspondant à votre demande");
            }
            rs1.close();
        } else {
            System.out.println("Aucun spectacle correspondant à votre demande");
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");
    }

    public static void req5(Connection conn) throws SQLException {
        int id_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro de spectacle:");
        id_spec = sc.nextInt();
        System.out.println("Veuillez saisir une date (format JJ-MMM-AA): Exemple 15-SEP-94\n"
                + "Pour des raisons d'intégrité, les mois seront abrégés en: \n"
                + "JAN-FEB-MAR-APR-MAY-JUN-JUL-AUG-SEP-OCT-NOV-DEC");
        Scanner scd = new Scanner(System.in);
        String date_saisie = scd.nextLine();
        DateFormat df = new SimpleDateFormat("dd-MMM-yy");
        Date d = null;
        try {

            d = df.parse(date_saisie);

        } catch (ParseException e) {
            System.out.println("Impossible de parser "
                    + date_saisie
                    + ". Veuillez entrer la date au format JJ-MMM-AA");
        }

        DateFormat df3 = new SimpleDateFormat("dd-MMM-yy");

        String s3 = df3.format(d);

        System.out.println("Date saisie: " + s3.toUpperCase());
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles WHERE NUMS = " + id_spec);
        if (rs.next()) {
            while (rs.next()) {
                System.out.println("Nom Spectacle : " + rs.getString(1));
            }
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM LesRepresentations WHERE DATEREP = " + "'" + s3.toUpperCase() + "'");
            if (rs1.next()) {
                System.out.println("Date déjà existante");
            } else {
                stmt.executeUpdate("INSERT INTO LesRepresentations VALUES(" + "'" + id_spec + "'" + "," + "'" + s3.toUpperCase() + "'" + ")");
                System.out.println("Ajout réussi");
            }
            rs1.close();
        } else {
            System.out.println("Veuillez ajouer un spectacle avant d'assigner des dates");
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");
    }

    public static void req6(Connection conn) throws SQLException {
        int id_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un nom de spectacle:");
        id_spec = sc.nextInt();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOMS FROM LesSpectacles WHERE NUMS = " + id_spec);
        if (rs.next()) {
            while (rs.next()) {
                System.out.println("Nom Spectacle : " + rs.getString(1));
            }
            ResultSet rs1 = stmt.executeQuery("SELECT DATEREP FROM LesRepresentations WHERE NUMS = " + id_spec);
            if (rs1.next()) {
                stmt.executeUpdate("DELETE FROM LesRepresentations WHERE NUMS = " + id_spec);
                System.out.println("Représentation(s) supprimée(s)");
            } else {
                System.out.println("Rien à supprimer.");
            }
            rs1.close();
        } else {
            System.out.println("Aucun spectacle correspondant à votre demande");
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");

    }

    public static void req7(Connection conn) throws SQLException {

        int id_spec = 0;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(NUMS) FROM LesSpectacles");
        while (rs.next()) {
            id_spec = (rs.getInt(1)) + 1;
        }
        String nom_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un nom de spectacle:");
        nom_spec = sc.nextLine();
        stmt.executeUpdate("INSERT INTO LesSpectacles VALUES(" + "'" + id_spec + "'" + "," + "'" + nom_spec + "'" + ")");
        System.out.println("Ajout réussi");
        System.out.println("********************************************************\n");

    }

    public static void req8(Connection conn) throws SQLException {
        int id_spec;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un numéro de spectacle:");
        id_spec = sc.nextInt();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NOMS FROM LesSpectacles WHERE NUMS = " + id_spec);
        if (rs.next()) {
            while (rs.next()) {
                System.out.println("Nom Spectacle : " + rs.getString(1));
            }
            ResultSet rs1 = stmt.executeQuery("SELECT DATEREP FROM LesRepresentations WHERE NUMS = " + id_spec);
            if (rs1.next()) {
                stmt.executeUpdate("DELETE FROM LesSpectacles WHERE NUMS = " + id_spec);
                stmt.executeUpdate("DELETE FROM LesRepresentations WHERE NUMS = " + id_spec);
                System.out.println("Suppression réussie");
            } else {
                System.out.println("Rien à supprimer.");
            }
            rs1.close();
        } else {
            System.out.println("Aucun spectacle correspondant à votre demande");
        }
        rs.close();
        stmt.close();
        System.out.println("********************************************************\n");

    }
}
