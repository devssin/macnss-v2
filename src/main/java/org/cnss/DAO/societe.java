package org.cnss.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import org.mindrot.jbcrypt.BCrypt;



import org.cnss.Connection.connection;
import org.cnss.Model.Societe;

public class societe {
    private HashMap<String, Societe> SocieteMap = new HashMap<>();
    private static Connection conn;
    public societe() {
        try {
             conn = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Societe authentifierSociete(String email, String motDePasse) {
        String sql = "SELECT * FROM societe WHERE Email = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String motDePasseHache = resultSet.getString("Pass");
                if (BCrypt.checkpw(motDePasse, motDePasseHache)) {
                          return new Societe(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("Email"), resultSet.getString("Pass"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Societe getSociete(int id) {
        String sql = "SELECT * FROM societe WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int societeId = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String email = resultSet.getString("Email");
                String motDePasse = resultSet.getString("Pass");

                return new Societe(societeId, nom, email, motDePasse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ajouterSociete(String nom, String email, String motDePasse) {
        String salt = BCrypt.gensalt();
        String motDePasseHache = BCrypt.hashpw(motDePasse, salt);

        String sql = "INSERT INTO societe (nom, Email, Pass) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nom);
            statement.setString(2, email);
            statement.setString(3, motDePasseHache); // Utilisez le mot de passe haché
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void declarerEmploye(String nom, String email, String Matricule, String motDePasse, Date dateNaissance) {
        String motDePasseHache = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
        String sql = "INSERT INTO employe (nom, Email, Matricule, Pass, Status, date_nss) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nom);
            statement.setString(2, email);
            statement.setString(3, Matricule);
            statement.setString(4, motDePasseHache); // Utilisation du mot de passe haché
            statement.setString(5, "TRAVAILLER");
            statement.setDate(6, dateNaissance);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Societe arreterDeclaration(int matricule) {
        String sql = "UPDATE employe SET Status = 'CHOMAGE' WHERE matricule = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void Declaration(String nom) {
        String sql = "UPDATE employe SET Status = 'TRAVAILLER' WHERE nom = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nom);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Declarationasretraited(int matricule) {
        String sql = "UPDATE employe SET Status = 'RETRAITER' WHERE matricule = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfDeclared(String nom) {
        String sql = "SELECT COUNT(*) FROM employe WHERE nom = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if(count > 0) {
                    return true;
                }           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkIfwork(int matricule) {
        String sql = "SELECT Status FROM employe WHERE Matricule = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, matricule);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("Status");
                if("TRAVAILLER".equals(status)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkIfRetraited(String nom) {
        String sql = "SELECT Status FROM employe WHERE nom = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("Status");
                if("RETRAITER".equals(status)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkIfChomage(String nom) {
        String sql = "SELECT Status FROM employe WHERE nom = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String status = resultSet.getString("Status");
                if("CHOMAGE".equals(status)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void affecterLesJours(double salaire, int societeId, int employeMatricule, int nombreJours) {
        String sql = "INSERT INTO salaire (montant,Date,jour_num,Employe,Societe) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            Date date = new Date(System.currentTimeMillis());

            statement.setDouble(1, salaire);
            statement.setDate(2,  date);
            statement.setInt(3, nombreJours);
            statement.setInt(4, employeMatricule);
            statement.setInt(5, societeId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
