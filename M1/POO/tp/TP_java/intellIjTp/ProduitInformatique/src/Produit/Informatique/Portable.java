package Produit.Informatique;

public class Portable extends Ordinateur{
    private String typeEcran;
    private double tailleEcran; // en pouces


    public Portable(String reference, String marque, String dateFabrication, double prix, String processeur, String tailleRAM, String tailleDisqueDur, String systemeExploitation, String typeEcran, double tailleEcran) {
        super(reference, marque, dateFabrication, prix, processeur, tailleRAM, tailleDisqueDur, systemeExploitation);
        this.typeEcran = typeEcran;
        this.tailleEcran = tailleEcran;
    }

    public String getTypeEcran() {
        return typeEcran;
    }

    public void setTypeEcran(String typeEcran) {
        this.typeEcran = typeEcran;
    }

    public double getTailleEcran() {
        return tailleEcran;
    }

    public void setTailleEcran(double tailleEcran) {
        this.tailleEcran = tailleEcran;
    }
    public String toString() {
        System.out.println("\n=== Ordinateur Portable ===");
        super.toString();
        return "Type d'écran: " + typeEcran+ "Taille d'écran: " + tailleEcran + " pouces";
    }
}
