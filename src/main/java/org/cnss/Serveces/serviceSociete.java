package org.cnss.Serveces;

import org.cnss.DAO.employe;
import org.cnss.DAO.societe;
import org.cnss.Main;
import org.cnss.Model.Societe;
import javax.swing.*;
import java.sql.Date;
import java.util.Scanner;


public class serviceSociete {
    private static societe societeDAO;
    private int idSocieteConnectee; // Variable de session fictive pour stocker l'ID de la société connectée

    public serviceSociete() {
        this.societeDAO = new societe();
        this.idSocieteConnectee = -1;
    }

    public boolean authentifierSociete(String email, String motDePasse) {
        try {
            Societe societe = societeDAO.authentifierSociete(email, motDePasse);
            if (societe != null) {
                idSocieteConnectee = societe.getId();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIdSocieteConnectee() {
        return idSocieteConnectee;
    }

    public void GetSociete () {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez l'ID de la société à rechercher : ");
        int idSociete = scanner.nextInt();
            Societe societe = societeDAO.getSociete(idSociete);

            if (societe != null) {
                System.out.println("Société trouvée :");
                System.out.println("ID : " + societe.getId());
                System.out.println("Nom : " + societe.getNom());
                System.out.println("Email : " + societe.getEmail());
                System.out.println("Mot de passe : " + societe.getPassword());
            } else {
                System.out.println("Aucune société trouvée avec l'ID spécifié.");
            }

            scanner.close();
        }

    public void AjouterSociete(){
            String nom = JOptionPane.showInputDialog(null, "Entrer le nom de la Société: ", "Ajouter Société", JOptionPane.INFORMATION_MESSAGE);
            String email = JOptionPane.showInputDialog(null, "Entrer l'email de la Société: ", "Ajouter Société", JOptionPane.INFORMATION_MESSAGE);
            String motDePasse = JOptionPane.showInputDialog(null, "Entrer le mot de pass de la Société: ", "Ajouter Société", JOptionPane.INFORMATION_MESSAGE);
            societe societeDAO = new societe();
            societeDAO.ajouterSociete(nom, email, motDePasse);
            JOptionPane.showMessageDialog(null,"Société ajoutée avec succès !","Ajouter Société", JOptionPane.INFORMATION_MESSAGE );
        }


    public void EmployeDeclaration() {
            Scanner scanner = new Scanner(System.in);
            societe societeDAO = new societe();
                String nomEmploye = JOptionPane.showInputDialog(null , "Entrez le nom de l'employé : ");
                boolean employeDejaDeclare = societeDAO.checkIfDeclared(nomEmploye);

                if (employeDejaDeclare) {
                    boolean estEnChomage = societeDAO.checkIfChomage(nomEmploye);
                    boolean estEnRetrait = societeDAO.checkIfRetraited(nomEmploye);
                    if (estEnChomage) {
                        System.out.println("L'employé est actuellement en chômage.");
                        societeDAO.Declaration(nomEmploye);
                        System.out.println("Le statut de l'employé a été changé en 'TRAVAILLER'.");
                    } else if (estEnRetrait){
                        System.out.println("L'employé est  en retrait.");
                    }else {
                        System.out.println("L'employé est Deja Travailler.");
                    }
                } else {
                    String emailEmploye = JOptionPane.showInputDialog("\"Entrez l'email de l'employé : \"");

                    String MatriculeEmploye = JOptionPane.showInputDialog(null , "Entrez le matricule de l'employé : ");
                    String motDePasseEmploye = JOptionPane.showInputDialog(null , "Entrez le mot de passe de l'employé : ");
                    String dateNaissance = JOptionPane.showInputDialog(null , "Entrez la date de naissance de l'employé (AAAA-MM-JJ) : ");
                    Date dateNaissanceEmploye = Date.valueOf(dateNaissance);

                    societeDAO.declarerEmploye(nomEmploye, emailEmploye,MatriculeEmploye, motDePasseEmploye, dateNaissanceEmploye);
                    JOptionPane.showMessageDialog(null , "L'employé a été déclaré avec succès !");


                }
            }
    public void arreterDeclaration() {
        Scanner scanner = new Scanner(System.in);
        societe societeDAO = new societe();
        System.out.print("Entrez le matricule de l'employé : ");
        int matricule = scanner.nextInt();
        boolean checkIfwork = societeDAO.checkIfwork(matricule);
        Societe societe = societeDAO.arreterDeclaration(matricule);
        if (checkIfwork){
        System.out.print("L employer a ete arrete");
        }else {
            System.out.print("L employer deja arreter ou retraiter");
        }
    }
    public void DeclarerSalaire(){
        societe societeDAO = new societe();
        employe employedao = new employe();

        int matricule = Integer.parseInt(JOptionPane.showInputDialog(null , "Entre le matricule "));


        boolean checkIfEmpExist = employedao.checkIfEmpExist(matricule);
        boolean checkIfwork = societeDAO.checkIfwork(matricule);
        if (checkIfEmpExist && checkIfwork){
        int societe_matricule = Integer.parseInt(JOptionPane.showInputDialog(null , "Entrer le matrucule de societe"));

        double Salaire = Double.parseDouble(JOptionPane.showInputDialog(null , "Entre le salaire"));

        int jrs = Integer.parseInt(JOptionPane.showInputDialog(null , "Entrez les jours absent:"));
        int nombreJours=Calculer_les_jours(jrs);
        societeDAO.affecterLesJours(Salaire,societe_matricule,matricule,nombreJours);
            JOptionPane.showMessageDialog(null, "Declarer avec succee");
            Main.main(null);
        }
        else {
            JOptionPane.showMessageDialog(null, "l'employer n existe pas");
            Main.main(null);
        }
    }
    public int Calculer_les_jours(int jrs){
        return 26-jrs;
    }
}






