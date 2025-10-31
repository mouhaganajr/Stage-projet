package sn.uasz.m1.tp1.exercice4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Club {
    private Map<String, Membre> membres;

    public Club() {
        membres = new HashMap<>();
    }

    public void ajouter(Membre membre) throws IdentifiantExistantException {
        if (membres.containsKey(membre.getIdentifiant())) {
            throw new IdentifiantExistantException("Identifiant déjà utilisé : " + membre.getIdentifiant());
        }
        membres.put(membre.getIdentifiant(), membre);
    }

    public void modifier(String identifiant) throws InexistantMembreException {
        if (!membres.containsKey(identifiant)) {
            throw new InexistantMembreException("Aucun membre trouvé avec l’identifiant : " + identifiant);
        }

        Scanner sc = new Scanner(System.in);
        Membre m = membres.get(identifiant);

        System.out.print("Nouveau nom : ");
        m.setNom(sc.nextLine());
        System.out.print("Nouvelle profession : ");
        m.setProfession(sc.nextLine());
        System.out.print("Nouveau sexe (Homme/Femme) : ");
        m.setSexe(sc.nextLine());
        System.out.print("Nouveau téléphone : ");
        m.setTelephone(sc.nextLine());

        System.out.println("✅ Membre mis à jour avec succès !");
    }

    public void supprimer(String identifiant) throws InexistantMembreException {
        if (!membres.containsKey(identifiant)) {
            throw new InexistantMembreException("Aucun membre trouvé avec l’identifiant : " + identifiant);
        }
        membres.remove(identifiant);
        System.out.println("🗑️ Membre supprimé avec succès !");
    }

    public void afficher() {
        if (membres.isEmpty()) {
            System.out.println("Aucun membre enregistré pour le moment.");
            return;
        }
        System.out.println("📋 Liste des membres du club :");
        for (Membre m : membres.values()) {
            System.out.println(m);
        }
    }
}

