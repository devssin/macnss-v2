package org.cnss.UI;

import org.cnss.Serveces.serviceCompteRetraite;
import org.cnss.Serveces.serviceSociete;

import java.util.Scanner;

public class Societe {
    public void societeMenu() {
        Scanner scanner = new Scanner(System.in);
        serviceSociete service = new serviceSociete();

        System.out.println("Authentification");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();

        boolean authentifie = service.authentifierSociete(email, motDePasse);

        if (authentifie) {
            System.out.println("Authentification réussie !");
            int idSociete = service.getIdSocieteConnectee();
            System.out.println("ID de la société connectée : " + idSociete);

            afficherMenu();

            int choix = -1;
            try {
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                } else {
                    System.out.println("Option invalide. Veuillez entrer un nombre.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de la saisie.");
                return;
            }

            while (choix != 0) {
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
                        System.out.println("Option invalide. Veuillez réessayer.");
                        break;
                }

                afficherMenu();
                try {
                    if (scanner.hasNextInt()) {
                        choix = scanner.nextInt();
                    } else {
                        System.out.println("Option invalide. Veuillez entrer un nombre.");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la saisie.");
                    return;
                }
            }
        } else {
            System.out.println("Authentification échouée !");
        }

        scanner.close();
    }


    private static void afficherMenu() {
        System.out.println("Menu de la Société");
        System.out.println("1. Obtenir les informations de la Société");
        System.out.println("3. Déclaration Employé");
        System.out.println("4. Arrêter la Déclaration");
        System.out.println("5. Déclarer un Salaire");
        System.out.println("6. Retraiter Compte Retraite");
        System.out.println("0. Quitter");
        System.out.print("Veuillez entrer votre choix : ");
    }
}
