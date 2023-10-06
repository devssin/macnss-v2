package org.cnss.Model;

import org.cnss.Enum.Status;
import java.util.Date;
import java.util.List;

public class Employe {
    private int matricule;
    private String nom;
    private String Email;
    private String password;
    private Date Date_de_naissance;
    private Status Status;
    private List<Salaire> salaire;


    public Employe(int matricule, String nom, String Email, String password, Date date_de_naissance, String status) {
        this.matricule = matricule;
        this.nom = nom;
        this.Email =Email;
        this.password = password;
        Date_de_naissance = date_de_naissance;
        Status = org.cnss.Enum.Status.valueOf(status);
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_de_naissance() {
        return Date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        Date_de_naissance = date_de_naissance;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

    public List<Salaire> getSalaire() {
        return salaire;
    }

    public void setSalaire(List<Salaire> salaire) {
        this.salaire = salaire;
    }
}

