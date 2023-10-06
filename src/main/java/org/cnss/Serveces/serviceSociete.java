package org.cnss.Serveces;

import org.cnss.DAO.employe;
import org.cnss.DAO.societe;
import org.cnss.Model.Societe;

import java.util.Scanner;
import java.sql.Date;

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
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez le nom de la société : ");
            String nom = scanner.nextLine();
            System.out.print("Entrez l'email de la société : ");
            String email = scanner.nextLine();
            System.out.print("Entrez le mot de passe de la société : ");
            String motDePasse = scanner.nextLine();
            societe societeDAO = new societe();
            societeDAO.ajouterSociete(nom, email, motDePasse);
            System.out.println("Société ajoutée avec succès !");
            scanner.close();
        }


    public void EmployeDeclaration() {
            Scanner scanner = new Scanner(System.in);
            societe societeDAO = new societe();
                System.out.print("Entrez le nom de l'employé : ");
                String nomEmploye = scanner.nextLine();
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
                    System.out.print("Entrez l'email de l'employé : ");
                    String emailEmploye = scanner.nextLine();
                    System.out.print("Entrez le matricule de l'employé : ");
                    String MatriculeEmploye = scanner.nextLine();
                    System.out.print("Entrez le mot de passe de l'employé : ");
                    String motDePasseEmploye = scanner.nextLine();
                    System.out.print("Entrez la date de naissance de l'employé (AAAA-MM-JJ) : ");
                    String dateNaissance = scanner.nextLine();
                    Date dateNaissanceEmploye = Date.valueOf(dateNaissance);
                    scanner.close();
                    societeDAO.declarerEmploye(nomEmploye, emailEmploye,MatriculeEmploye, motDePasseEmploye, dateNaissanceEmploye);
                    System.out.println("L'employé a été déclaré avec succès !");
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
        Scanner scanner = new Scanner(System.in);
        societe societeDAO = new societe();
        employe employedao = new employe();

        System.out.print("Entrez le matricule de l'employé : ");
        int matricule = scanner.nextInt();
        boolean checkIfEmpExist = employedao.checkIfEmpExist(matricule);
        boolean checkIfwork = societeDAO.checkIfwork(matricule);
        if (checkIfEmpExist && checkIfwork){
        System.out.print("Entrez le matricule de la societe : ");
        int Societe = scanner.nextInt();
        System.out.print("Entrez le salaire : ");
        double Salaire = scanner.nextInt();
        System.out.print("Entrez les jours absent: ");
        int jrs = scanner.nextInt();
        int nombreJours=Calculer_les_jours(jrs);
        societeDAO.affecterLesJours(Salaire,Societe,matricule,nombreJours);
            System.out.println("Declarer avec succee");
        }
        else {
            System.out.println("l'employer n existe pas");
        }
    }
    public int Calculer_les_jours(int jrs){
        return 26-jrs;
    }
}






