import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class PrincipalCuenta {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;

    static ArrayList<Cuenta> cuentas = new ArrayList<>();
    static Cuenta cuentaActual = null;

    public static void main(String[] args) throws Exception {
        boolean noSalir = true;
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();
            noSalir = ejecutarAccion(opcion);
        } while (noSalir);
    }

    static void mostrarMenu() {
        out.println("========= MENÚ PRINCIPAL =========");
        out.println("1. Crear cuenta");
        out.println("2. Ver cantidad de cuentas creadas");
        out.println("3. Listar cuentas");
        out.println("4. Seleccionar cuenta actual");
        out.println("5. Depositar");
        out.println("6. Retirar");
        out.println("7. Consultar saldo");
        out.println("8. Consultar estado (toString)");
        out.println("0. Salir");
        out.println("==================================");
    }

    static int leerOpcion() throws Exception {
        out.print("Seleccione una opción: ");
        return Integer.parseInt(in.readLine());
    }

    static boolean ejecutarAccion(int opcion) throws Exception {
        boolean noSalir = true;
        switch (opcion) {
            case 1: crearCuenta(); break;
            case 2: out.println("Cuentas creadas: " + Cuenta.getContador()); break;
            case 3: listarCuentas(); break;
            case 4: seleccionarCuenta(); break;
            case 5: depositar(); break;
            case 6: retirar(); break;
            case 7: consultarSaldo(); break;
            case 8: consultarEstado(); break;
            case 0: noSalir = false; break;
            default: out.println("Opción no válida.");
        }
        out.println();
        return noSalir;
    }

    static void crearCuenta() throws Exception {
        out.print("Número de cuenta: ");
        int num = Integer.parseInt(in.readLine());
        out.print("Nombre del cuentahabiente (ENTER para omitir): ");
        String nombre = in.readLine();

        if (nombre.isEmpty()) {
            cuentas.add(new Cuenta(num));
        } else {
            out.print("¿Saldo inicial? (ENTER para 0): ");
            String saldoTxt = in.readLine();
            if (saldoTxt.isEmpty()) {
                cuentas.add(new Cuenta(num, nombre));
            } else {
                double saldoInicial = Double.parseDouble(saldoTxt);
                cuentas.add(new Cuenta(num, nombre, saldoInicial));
            }
        }
        out.println("Cuenta creada exitosamente.");
    }

    static void listarCuentas() {
        if (cuentas.isEmpty()) {
            out.println("No hay cuentas creadas.");
            return;
        }
        for (Cuenta c : cuentas) {
            out.println(c.toString());
        }
    }

    static void seleccionarCuenta() throws Exception {
        out.print("Ingrese el número de cuenta a seleccionar: ");
        int num = Integer.parseInt(in.readLine());

        for (Cuenta c : cuentas) {
            if (c.toString().contains("Cuenta N°: " + num)) {
                cuentaActual = c;
                out.println("Cuenta seleccionada: " + c.toString());
                return;
            }
        }
        out.println("No se encontró la cuenta.");
    }

    static void depositar() throws Exception {
        if (cuentaActual == null) {
            out.println("Debe seleccionar una cuenta primero.");
            return;
        }
        out.print("Monto a depositar: ");
        double monto = Double.parseDouble(in.readLine());
        out.println("Nuevo saldo: " + cuentaActual.depositar(monto));
    }

    static void retirar() throws Exception {
        if (cuentaActual == null) {
            out.println("Debe seleccionar una cuenta primero.");
            return;
        }
        out.print("Monto a retirar: ");
        double monto = Double.parseDouble(in.readLine());
        out.println("Nuevo saldo: " + cuentaActual.retirar(monto));
    }

    static void consultarSaldo() {
        if (cuentaActual == null) {
            out.println("Debe seleccionar una cuenta primero.");
            return;
        }
        out.println("Saldo actual: " + cuentaActual.getSaldo());
    }

    static void consultarEstado() {
        if (cuentaActual == null) {
            out.println("Debe seleccionar una cuenta primero.");
            return;
        }
        out.println(cuentaActual.toString());
    }
}
