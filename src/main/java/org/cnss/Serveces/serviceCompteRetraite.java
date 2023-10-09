package org.cnss.Serveces;

import org.cnss.DAO.employe;
import org.cnss.DAO.salaire;
import org.cnss.DAO.societe;
import org.cnss.Model.Employe;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.sql.Date;
import javax.swing.*;

public class serviceCompteRetraite {
    private static salaire salaireDAO;
    private static serviceSalaire salaire;
    private static employe employeDAO;

    public serviceCompteRetraite() {
        this.employeDAO=new employe();
        this.salaireDAO=new salaire();
        this.salaire=new serviceSalaire();
    }
    public void canRetraited(){



        String matriculeInput = JOptionPane.showInputDialog(null, "Enter the employee's matricule:");


        if (matriculeInput != null) {
            try {
                int matricule = Integer.parseInt(matriculeInput);
                Employe employe = employeDAO.Rechercher_employe(matricule);

                if (employe != null) {
                    Date dateNaissanceSql = (Date) employe.getDate_de_naissance();

                    java.util.Date dateNaissanceUtil = new java.util.Date(dateNaissanceSql.getTime());
                    LocalDate localDateNaissance = dateNaissanceUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    int age = Period.between(localDateNaissance, currentDate).getYears();

                    if (age > 54) {
                        // Use JOptionPane for message

                        // Assuming salaire and societe classes are available
                        salaire.calculersalairretraite(matricule);
                        societe soc = new societe();
                        soc.Declarationasretraited(matricule);
                        JOptionPane.showMessageDialog(null, "Employee's age: " + age + "\nIl peut prendre la retraite", "Retirement Eligibility", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        // Use JOptionPane for message
                        JOptionPane.showMessageDialog(null, "Employee is less than 55 years old", "Age Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // Use JOptionPane for message
                    JOptionPane.showMessageDialog(null, "Employee not found with matricule " + matricule, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                JOptionPane.showMessageDialog(null, "Please enter a valid matricule.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }



}
