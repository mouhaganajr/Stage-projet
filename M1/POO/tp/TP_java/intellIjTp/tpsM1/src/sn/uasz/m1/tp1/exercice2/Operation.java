package sn.uasz.m1.tp1.exercice2;

public class Operation {

    static class OperationException extends Exception {
        OperationException(String message) {
            super(message);
        }
    }

    private double a;
    private double b;

    public Operation() {
        this.a = 0;
        this.b = 1;
    }

    public Operation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    double quotient() throws OperationException {
        if (b == 0)
            throw new OperationException("Division par zéro interdite !");
        return a / b;
    }

    double racineDeLaSomme() throws OperationException {
        double somme = a + b;
        if (somme < 0)
            throw new OperationException("Somme négative, racine impossible !");
        return Math.sqrt(somme);
    }

    boolean estDivisible() throws OperationException {
        if (a <= 0 || b <= 0)
            throw new OperationException("Les deux nombres doivent être positifs !");
        return a % b == 0;
    }

    public static void main(String[] args) {
        Operation op1 = new Operation(10, 2);
        Operation op2 = new Operation(9, 0);
        Operation op3 = new Operation(4, -2);
        Operation op4 = new Operation(3, 6);

        try {
            System.out.println("Quotient : " + op1.quotient());
            System.out.println("Racine de la somme : " + op1.racineDeLaSomme());
            System.out.println("Est divisible : " + op1.estDivisible());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("----- Test avec d'autres valeurs -----");

        try {
            System.out.println("Quotient : " + op2.quotient());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        try {
            System.out.println("Est divisible : " + op3.estDivisible());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        try {
            System.out.println("Racine de la somme : " + op4.racineDeLaSomme());
        } catch (OperationException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
