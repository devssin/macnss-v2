package org.cnss;

import org.cnss.UI.Employer;
import org.cnss.Serveces.serviceCompteRetraite;
import org.cnss.Serveces.serviceEmploye;
import org.cnss.Serveces.serviceSociete;
import org.cnss.UI.Societe;

import javax.swing.*;



public class Main {
        public static void main(String[] args) {
                serviceSociete service = new serviceSociete();



                int choice = -1;
                do {
                        try {
                                choice = Integer.parseInt(JOptionPane.showInputDialog(null , "Bienvenu \n 1- Ajouter une Societe \n 2- Espace employe \n 3- Espace entreprise "));
                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Erreur de saisi" , "alert", JOptionPane.ERROR_MESSAGE);

                        }

                        switch (choice) {
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
                                        JOptionPane.showMessageDialog(null, "Option invalide. Veuillez réessayer.", "Alert", JOptionPane.WARNING_MESSAGE);
                                        break;
                        }
                }while (choice != 0);
        }
}
