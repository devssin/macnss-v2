package org.cnss.DAO;

import org.cnss.Connection.connection;
import org.cnss.Model.Employe;
import org.cnss.Model.Salaire;
import org.cnss.Model.Societe;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class employe {
    private static Connection conn;
    public employe() {
        try {
             conn = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkIfEmpExist(int matricule) {
        String sql = "SELECT COUNT(*) FROM employe WHERE Matricule = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Employe authentifierEmp(String email, String motDePasse) {
        String sql = "SELECT * FROM employe WHERE Email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String motDePasseHache = resultSet.getString("Pass");
                if (BCrypt.checkpw(motDePasse, motDePasseHache)) {
                int matricule = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String status = resultSet.getString("Status");
                String emailEmp = resultSet.getString("Email");
                String motDePasseEmp = resultSet.getString("Pass");
                Date dateNaissance = resultSet.getDate("date_nss");
                return new Employe(matricule, nom, emailEmp, motDePasseEmp, dateNaissance, status);
            }}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employe> getEmployes() {
        List<Employe> employes = new ArrayList<>();
        String sql = "SELECT * FROM employe";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int matricule = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String status = resultSet.getString("Status");
                String emailEmp = resultSet.getString("Email");
                String motDePasseEmp = resultSet.getString("Pass");
                Date dateNaissance = resultSet.getDate("date_nss");
                Employe employe = new Employe(matricule, nom, emailEmp, motDePasseEmp, dateNaissance, status);
                employes.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employes;
    }

    public List<Salaire> Voir_les_mois_declarer(int matricule) {
        List<Salaire> salaires = new ArrayList<>();
        String sql = "SELECT * FROM salaire WHERE Employe = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                double montant = resultSet.getDouble("montant");
                Date date = resultSet.getDate("Date");
                int jourNum = resultSet.getInt("jour_num");

                employe employedao = new employe();
                Employe employe = employedao.Rechercher_employe(matricule);

                int id = resultSet.getInt("Societe");
                societe societedao = new societe();
                Societe societe = societedao.getSociete(id);

                Salaire salaire = new Salaire(montant, date, jourNum, employe, societe);
                salaires.add(salaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaires;
    }

    public int sommeJoursNum(int matricule) {
        String sql = "SELECT jour_num FROM salaire WHERE Employe = ?";
        int somme = 0;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int jourNum = resultSet.getInt("jour_num");
                somme += jourNum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return somme;
    }
    public void Retraiter(int matricule) {
        String sql = "UPDATE employe SET Status = ? WHERE matricule = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "RETRAITER");
            statement.setInt(2, matricule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Employe Rechercher_employe(int matricul){
        String sql="SELECT * FROM employe WHERE matricule=?";
        try(PreparedStatement statement=conn.prepareStatement(sql)) {
            statement.setInt(1,matricul);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()) {
                int matricule = resultSet.getInt("matricule");
                String nom = resultSet.getString("nom");
                String status = resultSet.getString("Status");
                String emailEmp = resultSet.getString("Email");
                String motDePasseEmp = resultSet.getString("Pass");
                Date dateNaissance = resultSet.getDate("date_nss");
                return new Employe(matricule, nom, emailEmp, motDePasseEmp, dateNaissance, status);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
