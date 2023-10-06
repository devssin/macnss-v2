package org.cnss.DAO;

import org.cnss.Connection.connection;
import org.cnss.Model.CompteRetraite;

import java.sql.*;

public class compteRetraite {
    private static Connection conn;
    public compteRetraite() {
        try {
             conn = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Calculer_salaire_de_retrait(){

    }
    public void insererCompteRetraite(int salaireMoyen,int employe_matricule) {
        String sql = "INSERT INTO compteretraite (salaireMoyen, employe_matricule) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, salaireMoyen);
            statement.setInt(2, employe_matricule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
