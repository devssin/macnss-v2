package org.cnss.Model;

public class Societe {
    private int id;
    private String nom;
    private String Email;
    private String password;

    public Societe(int id, String nom, String Email, String password) {
        this.id = id;
        this.nom = nom;
        this.Email = Email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEmail(String Email) {
       this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
