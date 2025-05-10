import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialConversion {
        private final String monedaOrigen;
        private final String monedaDestino;
        private final double cantidadOriginal;
        private final double resultado;
        private LocalDateTime fechaHora;

        public HistorialConversion(String monedaOrigen, String monedaDestino, double cantidadOriginal, double resultado) {
            this.monedaOrigen = monedaOrigen;
            this.monedaDestino = monedaDestino;
            this.cantidadOriginal = cantidadOriginal;
            this.resultado = resultado;
            this.fechaHora = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return String.format("%.2f %s => %.2f %s",
                    cantidadOriginal, monedaOrigen, resultado, monedaDestino);
        }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getCantidadOriginal() {
        return cantidadOriginal;
    }

    public double getResultado() {
        return resultado;
    }

    public String getFechaHoraFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }
}