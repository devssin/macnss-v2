package org.cnss.Model;

import java.util.Date;
import java.util.List;

public class Salaire {
    private double montant;
    private Date date_salaire;
    private  int Numero_jour_declare;
    private Employe employe;
    private Societe societe;

    public Salaire(double montant, Date date_salaire, int numero_jour_declare, Employe employe, Societe societe) {
        this.montant = montant;
        this.date_salaire = date_salaire;
        Numero_jour_declare = numero_jour_declare;
        this.employe = employe;
        this.societe = societe;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate_salaire() {
        return date_salaire;
    }

    public void setDate_salaire(Date date_salaire) {
        this.date_salaire = date_salaire;
    }

    public int getNumero_jour_declare() {
        return Numero_jour_declare;
    }

    public void setNumero_jour_declare(int numero_jour_declare) {
        Numero_jour_declare = numero_jour_declare;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}

