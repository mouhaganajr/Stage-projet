package sn.uasz.m1.tp1.exercice2;

public class TestOperation {
    public static void main(String[] args) {
        Operation op1 = new Operation(10, 2);
        Operation op2 = new Operation(9, 0);
        Operation op3 = new Operation(-4, -2);
        Operation op4 = new Operation(3, 6);

        try {
            System.out.println("le quotient : " + op1.quotient());
            System.out.println("la racine de la somme : " + op1.racineDeLaSomme());
            System.out.println("Est divisible : " + op1.estDivisible());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("----- Test avec d'autres valeurs -----");

        try {
            System.out.println("le quotient : " + op2.quotient());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        try {
            System.out.println("Est divisible : " + op3.estDivisible());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        try {
            System.out.println("la racine de la somme : " + op4.racineDeLaSomme());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
