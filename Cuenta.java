public class Cuenta {
    private int numeroCuenta;
    private String cuentaHabiente;
    private double saldo;
    private static int contador = 0;

    public Cuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
        contador++;
    }

    public Cuenta(int numeroCuenta, String cuentaHabiente) {
        this(numeroCuenta); // reutiliza el constructor de un parámetro
        this.cuentaHabiente = cuentaHabiente;
    }

    public double depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
        return saldo;
    }

    public double retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
        }
        return saldo;
    }
    
    public double getSaldo() {
        return saldo;
    }

    public static int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        return "Cuenta N°: " + numeroCuenta +
               " | Habiente: " + cuentaHabiente +
               " | Saldo: " + saldo;
    }
}
