package org.cnss;

import org.cnss.UI.Employer;
import org.cnss.Serveces.serviceCompteRetraite;
import org.cnss.Serveces.serviceEmploye;
import org.cnss.Serveces.serviceSociete;
import org.cnss.UI.Societe;

import java.util.Scanner;



public class Main {
        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                serviceSociete service = new serviceSociete();
                System.out.println("Bienvenue !");
                System.out.println("1. Ajouter Societe ");
                System.out.println("2. Menu Employé");
                System.out.println("3. Menu Société");
                System.out.println("0. Quitter");
                System.out.print("Veuillez sélectionner le menu : ");

                int choixMenu =-1;
                try {
                        if (scanner.hasNextInt()) {
                                choixMenu = scanner.nextInt();
                        } else {
                                System.out.println("Option invalide. Veuillez entrer un nombre.");
                                return;
                        }
                } catch (Exception e) {
                        System.out.println("Erreur lors de la saisie.");
                        return;
                }

                switch (choixMenu) {
                        case 1:
                                service.AjouterSociete();
                                break;
                        case 2:
                                Employer employeMenu = new Employer();
                                employeMenu.employeMenu();
                                break;
                        case 3:
                                Societe societeMenu = new Societe();
                                societeMenu.societeMenu();
                                break;
                        case 0:
                                System.out.println("Au revoir !");
                                break;
                        default:
                                System.out.println("Option invalide. Veuillez réessayer.");
                                break;
                }

                scanner.close();
        }
}
