package sn.uasz.m1.tp1.exercice4;

public class GestionClub {
    public static void main(String[] args) {
        Club club = new Club();

        try {
            club.ajouter(new Membre("M001", "Sall", "Étudiante", "Femme", "772020303"));
            club.ajouter(new Membre("M002", "Diop", "Développeur", "Homme", "781234567"));
            club.ajouter(new Membre("M003", "Ba", "Professeur", "Homme", "764567890"));

            club.afficher();

            try {
                club.ajouter(new Membre("M001", "Ndiaye", "Designer", "Femme", "770000000"));
            } catch (IdentifiantExistantException e) {
                System.out.println(" Erreur : " + e.getMessage());
            }

            System.out.println("\n--- Modification du membre M002 ---");
            club.modifier("M002");

            System.out.println("\n--- Suppression du membre M003 ---");
            club.supprimer("M003");

            System.out.println("\n--- Liste finale des membres ---");
            club.afficher();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}

