package org.cnss.Serveces;

import org.cnss.DAO.compteRetraite;
import org.cnss.DAO.employe;
import org.cnss.DAO.salaire;

import java.util.Scanner;

public class serviceSalaire {
    private static salaire salaireDAO;
    private static employe employeDAO;

    public serviceSalaire() {
        this.employeDAO=new employe();
        this.salaireDAO=new salaire();
    }

    public void calculersalairretraite(int matricule){
        int sommeJours = employeDAO.sommeJoursNum(matricule);
        if (sommeJours > 1319 && sommeJours < 3241 ) {
            double salairemoyen=salaireDAO.SalaireMoyenDerniers95Salaires(matricule);
            double salaireretrait=salairemoyen * 0.5;
            if (salaireretrait>6000){
                System.out.println("Le Salaire retraite de l employer" + matricule + " est : " + 6000 + "dh");
            } else if (salaireretrait<1000) {
                System.out.println("Le Salaire retraite de l employer" + matricule + " est : " + 1000 + "dh");
            } else {
                System.out.println("Le Salaire retraite de l employer" + matricule + " est : " + salaireretrait + "dh");
            }
        }else if (sommeJours > 3240){
            int bonus=sommeJours-3240;
            int pourcentage=bonus % 216;
            double salairemoyen=salaireDAO.SalaireMoyenDerniers95Salaires(matricule);
            double pourc=((double) (50 + pourcentage) /100);
            if (pourc>0.7){
                pourc=0.7;
            }
            double salaireretrait=salairemoyen * pourc;
            if (salaireretrait>6000){
                System.out.println("Le Salaire de l employer" + matricule + " est : " + 6000 + "dh");
                compteRetraite compteRetraite=new compteRetraite();
                compteRetraite.insererCompteRetraite(6000,matricule);
            } else if (salaireretrait<1000) {
                System.out.println("Le Salaire de l employer" + matricule + " est : " + 1000 + "dh");
                compteRetraite compteRetraite=new compteRetraite();
                compteRetraite.insererCompteRetraite(1000,matricule);
            } else {
                System.out.println("Le Salaire de l employer" + matricule + " est : " + salaireretrait + "dh");
                compteRetraite compteRetraite=new compteRetraite();
                compteRetraite.insererCompteRetraite((int) salaireretrait,matricule);
            }
        }else {
            System.out.println("Le Salaire de l employer est 0dh car les jours de travaille ne depasse pas 1319");
        }

    }
    public void retraitSalair(){

    }
}
