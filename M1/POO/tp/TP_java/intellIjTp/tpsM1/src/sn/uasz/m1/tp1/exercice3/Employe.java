package sn.uasz.m1.tp1.exercice3;

import java.time.LocalDate;

public class Employe {
    private String matricule;
    private String nom;
    private double salaire;
    private LocalDate dateEmbauche;

    // Constructeur paramétrique
    public Employe(String matricule, String nom, double salaire, LocalDate dateEmbauche) {
        this.matricule = matricule;
        this.nom = nom;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    // Getters et Setters
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getSalaire() { return salaire; }
    public void setSalaire(double salaire) { this.salaire = salaire; }

    public LocalDate getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(LocalDate dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    // Méthode toString()
    @Override
    public String toString() {
        return "Employé [Matricule=" + matricule + ", Nom=" + nom +
                ", Salaire=" + salaire + ", Date d'embauche=" + dateEmbauche + "]";
    }
}

