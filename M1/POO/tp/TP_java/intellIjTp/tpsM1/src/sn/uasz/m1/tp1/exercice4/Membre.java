package sn.uasz.m1.tp1.exercice4;

public class Membre {
    private String identifiant;
    private String nom;
    private String profession;
    private String sexe; // "Homme" ou "Femme"
    private String telephone;

    public Membre() {
    }

    public Membre(String identifiant, String nom, String profession, String sexe, String telephone) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.profession = profession;
        this.sexe = sexe;
        this.telephone = telephone;
    }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Override
    public String toString() {
        return "Membre [Identifiant=" + identifiant +
                ", Nom=" + nom +
                ", Profession=" + profession +
                ", Sexe=" + sexe +
                ", Téléphone=" + telephone + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Membre autre = (Membre) obj;
        return identifiant != null && identifiant.equals(autre.identifiant);
    }
}

