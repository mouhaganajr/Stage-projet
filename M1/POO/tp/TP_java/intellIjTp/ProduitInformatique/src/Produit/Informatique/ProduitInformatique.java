package Produit.Informatique;

import java.util.Date;

public class ProduitInformatique {

    private String reference;
    private String marque;
    private Date dateFabrication;
    private double prix;

    public ProduitInformatique(String reference, String marque, Date dateFabrication, double prix) {
        this.reference = reference;
        this.marque = marque;
        this.dateFabrication = dateFabrication;
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public String getMarque() {
        return marque;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public double getPrix() {
        return prix;
    }

    // Setters
    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

}
