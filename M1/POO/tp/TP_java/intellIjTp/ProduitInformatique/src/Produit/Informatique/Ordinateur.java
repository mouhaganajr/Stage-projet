package Produit.Informatique;

import java.util.Date;

public class Ordinateur extends ProduitInformatique{

    private String processeur;
    private String tailleRAM; // en Go
    private String tailleDisqueDur; // en Go
    private String systemeExploitation;

    public Ordinateur(String reference, String marque, Date dateFabrication, double prix,
                      String processeur, String tailleRAM, String tailleDisqueDur, String systemeExploitation) {
        super(reference, marque, dateFabrication, prix);
        this.processeur = processeur;
        this.tailleRAM = tailleRAM;
        this.tailleDisqueDur = tailleDisqueDur;
        this.systemeExploitation = systemeExploitation;
    }

    public String getProcesseur() {
        return processeur;
    }

    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    public String getTailleRAM() {
        return tailleRAM;
    }

    public void setTailleRAM(String tailleRAM) {
        this.tailleRAM = tailleRAM;
    }

    public String getTailleDisqueDur() {
        return tailleDisqueDur;
    }

    public void setTailleDisqueDur(String tailleDisqueDur) {
        this.tailleDisqueDur = tailleDisqueDur;
    }

    public String getSystemeExploitation() {
        return systemeExploitation;
    }

    public void setSystemeExploitation(String systemeExploitation) {
        this.systemeExploitation = systemeExploitation;
    }

    public String toString() {
        super.toString();
       return "Processeur: " + processeur+ "RAM: " + tailleRAM + " Go"+ "Disque dur: " + tailleDisqueDur + " Go"+ "Système d'exploitation: " + systemeExploitation;
    }
}

