package sn.uasz.m1.tp1.exercice3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionEmploye {
    private List<Employe> listeEmployes;

    public GestionEmploye() {
        listeEmployes = new ArrayList<>();
    }

    public void ajouterEmploye(Employe e) {
        listeEmployes.add(e);
    }

    public List<Employe> employesEmbauchesA(LocalDate date) {
        List<Employe> res = new ArrayList<>();
        for (Employe e : listeEmployes) {
            if (e.getDateEmbauche().equals(date))
                res.add(e);
        }
        return res;
    }

    public List<Employe> employesAvant(LocalDate date) {
        List<Employe> res = new ArrayList<>();
        for (Employe e : listeEmployes) {
            if (e.getDateEmbauche().isBefore(date))
                res.add(e);
        }
        return res;
    }

    public int nombreEmployesAvant(LocalDate date) {
        return employesAvant(date).size();
    }

    public List<Employe> employesAvantEtSalaire(LocalDate date, double montant) {
        List<Employe> res = new ArrayList<>();
        for (Employe e : listeEmployes) {
            if (e.getDateEmbauche().isBefore(date) && e.getSalaire() > montant)
                res.add(e);
        }
        return res;
    }

    public Employe employeLeMieuxPaye() {
        if (listeEmployes.isEmpty()) return null;
        Employe meilleur = listeEmployes.get(0);
        for (Employe e : listeEmployes) {
            if (e.getSalaire() > meilleur.getSalaire())
                meilleur = e;
        }
        return meilleur;
    }

    public static void main(String[] args) {
        GestionEmploye gestion = new GestionEmploye();

        // Enregistrement des employés
        gestion.ajouterEmploye(new Employe("E001", "Sall", 250000, LocalDate.of(2020, 5, 10)));
        gestion.ajouterEmploye(new Employe("E002", "Diop", 180000, LocalDate.of(2021, 3, 15)));
        gestion.ajouterEmploye(new Employe("E003", "Ba", 300000, LocalDate.of(2019, 8, 20)));
        gestion.ajouterEmploye(new Employe("E004", "Ndiaye", 200000, LocalDate.of(2020, 5, 10)));

        System.out.println("Employés embauchés le 2020-05-10 :");
        for (Employe e : gestion.employesEmbauchesA(LocalDate.of(2020, 5, 10))) {
            System.out.println(e);
        }

        System.out.println("\nEmployés embauchés avant le 2021-01-01 :");
        for (Employe e : gestion.employesAvant(LocalDate.of(2021, 1, 1))) {
            System.out.println(e);
        }

        System.out.println("\nNombre d’employés embauchés avant 2021-01-01 : " +
                gestion.nombreEmployesAvant(LocalDate.of(2021, 1, 1)));

        System.out.println("\nEmployés embauchés avant 2021-01-01 et salaire > 200000 :");
        for (Employe e : gestion.employesAvantEtSalaire(LocalDate.of(2021, 1, 1), 200000)) {
            System.out.println(e);
        }

        System.out.println("\nEmployé le mieux payé :");
        System.out.println(gestion.employeLeMieuxPaye());
    }
}

