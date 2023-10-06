package org.cnss.Serveces;

import org.cnss.DAO.employe;
import org.cnss.DAO.salaire;
import org.cnss.DAO.societe;
import org.cnss.Model.Employe;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.sql.Date;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the employee's matricule: ");
        int matricule = scanner.nextInt();
        Employe employe = employeDAO.Rechercher_employe(matricule);

        if (employe != null) {
            Date dateNaissanceSql = (Date) employe.getDate_de_naissance();

            java.util.Date dateNaissanceUtil = new java.util.Date(dateNaissanceSql.getTime());
            LocalDate localDateNaissance = dateNaissanceUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(localDateNaissance, currentDate).getYears();
            if (age>54){
                System.out.println("Employee's age: " + age + "\n il peut prendre la retraite");
                salaire.calculersalairretraite(matricule);
                societe soc=new societe();
                soc.Declarationasretraited(matricule);
            }else {
                System.out.println("Employee est moin 55 " );
            }
        } else {
            System.out.println("Employee not found with matricule " + matricule);
        }
    }



}
