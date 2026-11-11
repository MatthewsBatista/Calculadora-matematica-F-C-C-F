package cmatematica;

public class CMatematica {

    private double n1;
    private double n2;

    public CMatematica() {
        this.n1 = 0.0;
        this.n2 = 0.0;
    }

    public void darNumeros(double n1, double n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public double getN1() {
        return n1;
    }

    public double getN2() {
        return n2;
    }

    public double sumar() {
        return n1 + n2;
    }

    public double restar() {
        return n1 - n2;
    }

    public double multiplicar() {
        return n1 * n2;
    }

    public double dividir() {
        if (n2 == 0) {
            throw new ArithmeticException("Error: no se puede dividir entre cero.");
        }
        return n1 / n2;
    }

    public double raizCuadrada() {
        if (n1 < 0) {
            throw new ArithmeticException("Error: no existe raíz cuadrada real de un número negativo.");
        }
        return Math.sqrt(n1);
    }

    // n1 elevado a la n2
    public double exponente() {
        return Math.pow(n1, n2);
    }

    // °C = (°F - 32) x 5/9
    public static double fahrenheitACelsius(double f) {
        return (f - 32) * 5 / 9;
    }

    // °F = (°C x 9/5) + 32
    public static double celsiusAFahrenheit(double c) {
        return (c * 9 / 5) + 32;
    }
}
