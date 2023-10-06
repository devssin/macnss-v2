package org.cnss.DAO;

import org.cnss.Connection.connection;

import java.sql.*;

public class salaire {
    private static Connection conn;
    public salaire() {
        try {
             conn = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double SalaireMoyenDerniers95Salaires(int matricule) {
        String sql = "SELECT montant FROM (SELECT montant FROM salaire WHERE Employe = ? ORDER BY id DESC LIMIT 95) AS derniers_salaires";
        double totalMontant = 0.0;
        int nombreSalaires = 0;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                double montant = resultSet.getDouble("montant");
                totalMontant += montant;
                nombreSalaires++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (nombreSalaires > 0) {
            return totalMontant / nombreSalaires;
        } else {
            return 0.0;
        }
    }



}
