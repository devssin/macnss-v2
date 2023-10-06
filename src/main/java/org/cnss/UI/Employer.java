package org.cnss.UI;

import org.cnss.Serveces.serviceEmploye;
import java.util.Scanner;

public class Employer {
    public void employeMenu() {
        Scanner scanner = new Scanner(System.in);
        serviceEmploye employeService = new serviceEmploye();

        System.out.println("Authentification");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();

        boolean authentifie = employeService.authentifierEmp(email, motDePasse);

        if (authentifie) {
            System.out.println("Authentification réussie !");

            afficherMenu();

            int choix = -1;
            try {
                if (scanner.hasNextInt()) {
                    choix = scanner.nextInt();
                } else {
                    System.out.println("Option invalide. Veuillez entrer un nombre.");
                    return; // Arrêter la méthode si l'entrée n'est pas un nombre.
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de la saisie.");
                return; // Arrêter la méthode en cas d'erreur lors de la saisie.
            }

            while (choix != 0) {
                switch (choix) {
                    case 1:
                        employeService.afficherEmployesTravailler();
                        break;
                    case 2:
                        employeService.VoirLesMoisDeclarer();
                        break;
                    case 3:
                        employeService.Voir_Les_point();
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
                        return; // Arrêter la méthode si l'entrée n'est pas un nombre.
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la saisie.");
                    return; // Arrêter la méthode en cas d'erreur lors de la saisie.
                }
            }
        } else {
            System.out.println("Authentification échouée !");
        }

        scanner.close();
    }


    private static void afficherMenu() {
        System.out.println("Menu de l'Employé");
        System.out.println("1. Afficher les employés travaillant");
        System.out.println("2. Voir les mois déclarés");
        System.out.println("3. Voir les points");
        System.out.println("0. Quitter");
        System.out.print("Veuillez entrer votre choix : ");
    }
}
