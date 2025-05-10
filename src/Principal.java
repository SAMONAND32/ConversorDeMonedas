import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        List<HistorialConversion> historial = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        OperacionCambioMoneda servicio = new OperacionCambioMoneda();
        int opcion = 0;

        do {
            System.out.println("******************** ********************************");
            System.out.println("Bienvenidos al Conversor de Monedas");
            System.out.println("***************************************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Dólar => Peso Chileno");
            System.out.println("8) Peso Chileno => Dólar");
            System.out.println("9) Dólar => Peso mexicano");
            System.out.println("10) Peso mexicano => Dólar");
            System.out.println("11) Dólar => Yen japonés");
            System.out.println("12) Yen japonés=> Dólar");
            System.out.println("13) Ver historial de conversiones");
            System.out.println("14) Salir");
            System.out.println("***************************************************");

            System.out.print("Elija una opción válida: ");
            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
                continue;
            }

            if (opcion >= 1 && opcion <= 12) {
                System.out.print("Ingrese el valor que desea convertir: ");
                double cantidad;

                try {
                    cantidad = Double.parseDouble(teclado.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número decimal válido.");
                    continue;
                }

                String moneda = "", valorMonedaElegida = "";
                switch (opcion) {
                    case 1 -> { moneda = "USD"; valorMonedaElegida = "ARS"; }
                    case 2 -> { moneda = "ARS"; valorMonedaElegida = "USD"; }
                    case 3 -> { moneda = "USD"; valorMonedaElegida = "BRL"; }
                    case 4 -> { moneda = "BRL"; valorMonedaElegida = "USD"; }
                    case 5 -> { moneda = "USD"; valorMonedaElegida = "COP"; }
                    case 6 -> { moneda = "COP"; valorMonedaElegida = "USD"; }
                    case 7 -> { moneda = "USD"; valorMonedaElegida = "CLP"; }
                    case 8 -> { moneda = "CLP"; valorMonedaElegida = "USD"; }
                    case 9 -> { moneda = "USD"; valorMonedaElegida = "MXN"; }
                    case 10 -> { moneda = "MXN"; valorMonedaElegida = "USD"; }
                    case 11 -> { moneda = "USD"; valorMonedaElegida = "JPY"; }
                    case 12 -> { moneda = "JPY"; valorMonedaElegida = "USD"; }
                }

                try {
                    double resultado = servicio.convertir(moneda, valorMonedaElegida, cantidad);
                    historial.add(new HistorialConversion(moneda, valorMonedaElegida, cantidad, resultado));

                    System.out.printf(
                            "%nEl valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n%n",
                            cantidad, moneda, resultado, valorMonedaElegida
                    );
                } catch (Exception e) {
                    System.out.println("Error al convertir: " + e.getMessage());
                }

            } else if (opcion == 13) {
                if (historial.isEmpty()) {
                    System.out.println("No hay conversiones realizadas aún.");
                } else {
                    System.out.println("----- Historial de conversiones -----");
                    for (HistorialConversion item : historial) {
                        System.out.printf(
                                "%.2f [%s] => %.2f [%s] | Fecha y hora: %s%n",
                                item.getCantidadOriginal(),
                                item.getMonedaOrigen(),
                                item.getResultado(),
                                item.getMonedaDestino(),
                                item.getFechaHoraFormateada());
                    }
                    System.out.println("-------------------------------------");
                }
            } else if (opcion != 14) {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 14);

        System.out.println("Gracias por usar el conversor.");
        teclado.close();
    }
}
