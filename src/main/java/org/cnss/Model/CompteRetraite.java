package org.cnss.Model;

public class CompteRetraite {
    private int id;
    private double salaireMoyen;
    private Employe employe;

    public CompteRetraite(int id, double salaireMoyen, Employe employe) {
        this.id = id;
        this.salaireMoyen = salaireMoyen;
        this.employe = employe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalaireMoyen() {
        return salaireMoyen;
    }

    public void setSalaireMoyen(double salaireMoyen) {
        this.salaireMoyen = salaireMoyen;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}

