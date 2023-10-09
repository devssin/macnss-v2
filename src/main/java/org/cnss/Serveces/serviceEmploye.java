package org.cnss.Serveces;

import org.cnss.DAO.employe;
import org.cnss.DAO.societe;
import org.cnss.Model.Employe;
import org.cnss.Model.Salaire;
import org.cnss.Model.Societe;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class serviceEmploye {
    private static employe employeDAO;
    public serviceEmploye() {
        this.employeDAO = new employe();
    }
    public void afficherEmployesTravailler() {
        List<Employe> employes =employeDAO.getEmployes();
        if (!employes.isEmpty()) {
            for (Employe employe : employes) {

                JOptionPane.showMessageDialog(null, "Matricule: " + employe.getMatricule() +
                        "\nNom: " + employe.getNom() +
                        "\nEmail: " + employe.getEmail() +
                        "\nStatut: " + employe.getStatus() +
                        "\nDate de Naissance: " + employe.getDate_de_naissance()+
                        "\n-----------------------\n");
            }
        } else {
            JOptionPane.showMessageDialog(null ,"Aucun employé avec le statut 'travailler' n'a été trouvé." ,"List des employees ", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public boolean authentifierEmp(String email, String motDePasse) {
        try {
            Employe employe = employeDAO.authentifierEmp(email, motDePasse);
            if (employe != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void VoirLesMoisDeclarer() {
        String matriculeInput = JOptionPane.showInputDialog(null, "Entrez le matricule de l'employé :");
        if (matriculeInput != null) {
            try {
                int matricule = Integer.parseInt(matriculeInput);
                List<Salaire> salaires = employeDAO.Voir_les_mois_declarer(matricule);

                if (salaires != null && !salaires.isEmpty()) {
                    for (Salaire salaire : salaires) {
                        String message = "Informations sur le salaire déclaré:\n" +
                                "Montant : " + salaire.getMontant() + "\n" +
                                "Date : " + salaire.getDate_salaire() + "\n" +
                                "Jour numéro : " + salaire.getMontant() + "\n" +
                                "Nom de l'employé : " + salaire.getEmploye().getNom() + "\n" +
                                "Nom de la société : " + salaire.getSociete().getNom() + "\n" +
                                "-----------------------";

                        JOptionPane.showMessageDialog(null, message, "Informations sur le salaire", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null ,"Aucun salaire déclaré trouvé pour l'employé avec le matricule " + matricule);
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                JOptionPane.showMessageDialog(null, "Veuillez entrer un matricule valide.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void Voir_Les_point() {
        String matriculeInput = JOptionPane.showInputDialog(null, "Entrez le matricule de l'employé :");

// Check if the user clicked Cancel or closed the dialog
        if (matriculeInput != null) {
            try {
                int matricule = Integer.parseInt(matriculeInput);

                int sommeJours = employeDAO.sommeJoursNum(matricule);

                if (sommeJours > 0) {
                    // Use JOptionPane for message
                    JOptionPane.showMessageDialog(null, "Le total des points pour l'employé avec le matricule " + matricule + " est : " + sommeJours, "Résultat", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Use JOptionPane for message
                    JOptionPane.showMessageDialog(null, "Aucun point trouvé pour l'employé avec le matricule " + matricule, "Aucun point trouvé", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                JOptionPane.showMessageDialog(null, "Veuillez entrer un matricule valide.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
