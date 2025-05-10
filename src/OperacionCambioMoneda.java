import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OperacionCambioMoneda implements VariablesConversion {
    private double cantidadGuardada;
    private final Gson gson = new Gson();
    private final String API_KEY = "PON_AQUI_TU_API_KEY";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    @Override
    public double convertir(String moneda, String valorMonedaElegida, double cantidad) throws Exception {
        this.cantidadGuardada = cantidad;
        URI direccion = URI.create(API_URL + API_KEY + "/latest/" + moneda);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            ResultadoDeConversion resultado = gson.fromJson(response.body(), ResultadoDeConversion.class);

            if (!"success".equals(resultado.getResult())) {
                throw new Exception("No se pudo obtener la tasa de cambio.");
            }
            Double tasa = resultado.getConversion_rates().get(valorMonedaElegida);
            if (tasa == null) {
                throw new Exception("La moneda de destino '" + valorMonedaElegida + "' no está disponible.");
            }
            return cantidad * tasa;
        } catch (Exception e) {
            throw new Exception("Error al convertir: " + e.getMessage(), e);
        }

    }
}



