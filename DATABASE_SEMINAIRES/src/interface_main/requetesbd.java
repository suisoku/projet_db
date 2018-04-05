package interface_main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class requetesbd {

    /**
     * Afficher toutes les informations sur tous les spectacles.
     *
     *@param conn connexion � la base de donn�es
     * @throws SQLException en cas d'erreur d'acc�s � la base de donn�es
     */
    /************************* test *******************************/
    
    public static void nbseminaire(Connection conn) throws
            SQLException {
// Get a statement from the connection
        Statement stmt = conn.createStatement();
// Execute the query
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM animateur");
        while (rs.next()) {
            System.out.println("Nombre de animateur : "+ rs.getInt(1));
        }
// Close the result set, statement and theconnection 
        rs.close();
        stmt.close();
    }

    
 
}
