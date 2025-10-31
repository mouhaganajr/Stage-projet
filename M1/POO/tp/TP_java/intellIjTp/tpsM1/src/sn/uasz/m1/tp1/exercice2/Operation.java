package sn.uasz.m1.tp1.exercice2;

public class Operation {

    private final int a;
    private final int b;

    public Operation() {
        this.a = 0;
        this.b = 1;
    }

    public Operation(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public double quotient() throws OperationException {
        if (b == 0)
            throw new OperationException("la division par zéro impossible ");
        return (double)a / b;
    }

    public double racineDeLaSomme() throws OperationException {
        double somme = a + b;
        if (somme < 0)
            throw new OperationException("la somme est négative, racine impossible ");
        return Math.sqrt(somme);
    }

    public boolean estDivisible() throws OperationException {
        if (a <= 0 || b <= 0)
            throw new OperationException("Les deux nombres doivent être positifs !");
        return a % b == 0;  //a modulo b
    }


}
