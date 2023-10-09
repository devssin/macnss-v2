package org.cnss.UI;

import org.cnss.Serveces.serviceCompteRetraite;
import org.cnss.Serveces.serviceSociete;

import javax.swing.*;
import java.util.Scanner;

public class Societe {
    public void societeMenu() {
        Scanner scanner = new Scanner(System.in);
        serviceSociete service = new serviceSociete();

        String email = JOptionPane.showInputDialog(null , "Entrer l'email de la Société", "Authentification", JOptionPane.INFORMATION_MESSAGE);
        String motDePasse = JOptionPane.showInputDialog(null , "Entrer le mot de pass  de la Société", "Authentification", JOptionPane.INFORMATION_MESSAGE);

        boolean authentifie = service.authentifierSociete(email, motDePasse);

        if (authentifie) {
            System.out.println("Authentification réussie !");
            int idSociete = service.getIdSocieteConnectee();
            System.out.println("ID de la société connectée : " + idSociete);


            int choix = -1;
            do {
                choix = afficherMenu();
                switch (choix) {
                    case 1:
                        service.GetSociete();
                        break;
                    case 3:
                        service.EmployeDeclaration();
                        break;
                    case 4:
                        service.arreterDeclaration();
                        break;
                    case 5:
                        service.DeclarerSalaire();
                        break;
                    case 6:
                        serviceCompteRetraite salaire = new serviceCompteRetraite();
                        salaire.canRetraited();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Option invalide. Veuillez réessayer.", "Ajouter Société", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }

            }while (choix != 0);
        } else {
            System.out.println("Authentification échouée !");
        }

        scanner.close();
    }


    private static int afficherMenu() {

       int choix = -1;
        try{
            choix = Integer.parseInt(JOptionPane.showInputDialog(null , "********** Menu de la Société **********\n" +
                    "1. Obtenir les informations de la Société \n" +
                    "3. Déclaration Employé\n" +
                    "4. Arrêter la Déclaration\n" +
                    "5. Déclarer un Salaire\n" +
                    "6. Retraiter Compte Retraite\n" +
                    "0. Quitter \n"
            ));


        }catch (Exception e){
            JOptionPane.showMessageDialog(null , "Erreur de saisie ");
        }
        return choix;
    }
}
