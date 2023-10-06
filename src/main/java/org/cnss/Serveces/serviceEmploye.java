package org.cnss.Serveces;

import org.cnss.DAO.employe;
import org.cnss.DAO.societe;
import org.cnss.Model.Employe;
import org.cnss.Model.Salaire;
import org.cnss.Model.Societe;

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
                System.out.println("Matricule: " + employe.getMatricule());
                System.out.println("Nom: " + employe.getNom());
                System.out.println("Email: " + employe.getEmail());
                System.out.println("Statut: " + employe.getStatus());
                System.out.println("Date de Naissance: " + employe.getDate_de_naissance());
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("Aucun employé avec le statut 'travailler' n'a été trouvé.");
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
    public List<Salaire> VoirLesMoisDeclarer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le matricule de l'employé : ");
        int matricule = scanner.nextInt();
        List<Salaire> salaires = employeDAO.Voir_les_mois_declarer(matricule);

        if (salaires != null && !salaires.isEmpty()) {
            for (Salaire salaire : salaires) {
                System.out.println("Informations sur le salaire déclaré :");
                System.out.println("Montant : " + salaire.getMontant());
                System.out.println("Date : " + salaire.getDate_salaire());
                System.out.println("Jour numéro : " + salaire.getMontant());
                System.out.println("Nom de l'employé : " + salaire.getEmploye().getNom());
                System.out.println("Nom de la société : " + salaire.getSociete().getNom());
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("Aucun salaire déclaré trouvé pour l'employé avec le matricule " + matricule);
        }
        scanner.close();

        return salaires;
    }

    public void Voir_Les_point() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le matricule de l'employé : ");
        int matricule = scanner.nextInt();

        int sommeJours = employeDAO.sommeJoursNum(matricule);

        if (sommeJours > 0) {
            System.out.println("Le total des points pour l'employé avec le matricule " + matricule + " est : " + sommeJours);
        } else {
            System.out.println("Aucun point trouvé pour l'employé avec le matricule " + matricule);
        }

        scanner.close();
    }

}
