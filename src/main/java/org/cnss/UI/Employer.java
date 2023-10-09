package org.cnss.UI;

import org.cnss.Serveces.serviceEmploye;

import javax.swing.*;
import java.util.Scanner;

public class Employer {
    public void employeMenu() {
        Scanner scanner = new Scanner(System.in);
        serviceEmploye employeService = new serviceEmploye();

//        System.out.println("Authentification");
//        System.out.print("Email: ");
//        String email = scanner.nextLine();
//        System.out.print("Mot de passe: ");
//        String motDePasse = scanner.nextLine();
        String email = JOptionPane.showInputDialog(null , "Entrer l'email d'employer", "Authentification", JOptionPane.INFORMATION_MESSAGE);
        String motDePasse = JOptionPane.showInputDialog(null , "Entrer le mot de pass  d'employer", "Authentification", JOptionPane.INFORMATION_MESSAGE);

        boolean authentifie = employeService.authentifierEmp(email, motDePasse);

        if (authentifie) {
            System.out.println("Authentification réussie !");

            int choix = afficherMenu();




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


    private static int afficherMenu() {
        int choice = -1;
       try{
           choice = Integer.parseInt(JOptionPane.showInputDialog(null, "****** Menu d'employe ****** \n" +
                   "1. Afficher les employés travaillant \n" +
                   "2. Voir les mois déclarés \n" +
                   "3. Voir les points \n" +
                   "0. Quitter \n"
           ));
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "Erreur de saisi" , "alert", JOptionPane.ERROR_MESSAGE);
       }

       return choice;
    }
}
