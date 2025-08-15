import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // Tu API Key de ExchangeRate-API
    private static final String API_KEY = "e9df138a7b5e7351bce24935";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Construir URL correcta
            String url = BASE_URL + API_KEY + "/latest/USD";
            System.out.println("Consultando: " + url);

            // Crear cliente HTTP y request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviar request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar respuesta
            if (response.statusCode() != 200) {
                System.out.println("Error en la solicitud: Código " + response.statusCode());
                System.out.println(response.body());
                return;
            }

            // Parsear JSON
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            String result = jsonObject.get("result").getAsString();

            if (!"success".equals(result)) {
                System.out.println("La API devolvió un error: " + response.body());
                return;
            }

            // Obtener tasas de conversión
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
            Map<String, Double> tasas = Map.of(
                    "ARS", rates.get("ARS").getAsDouble(),
                    "BOB", rates.get("BOB").getAsDouble(),
                    "BRL", rates.get("BRL").getAsDouble(),
                    "CLP", rates.get("CLP").getAsDouble(),
                    "COP", rates.get("COP").getAsDouble(),
                    "USD", rates.get("USD").getAsDouble()
            );

            // Menú interactivo
            while (true) {
                System.out.println("\n=== Conversor de Monedas ===");
                System.out.println("1) USD a ARS");
                System.out.println("2) USD a BOB");
                System.out.println("3) USD a BRL");
                System.out.println("4) USD a CLP");
                System.out.println("5) USD a COP");
                System.out.println("6) Salir");
                System.out.print("Elija una opción: ");
                int opcion = scanner.nextInt();

                if (opcion == 6) {
                    System.out.println("¡Gracias por usar el conversor!");
                    break;
                }

                System.out.print("Ingrese el monto en USD: ");
                double monto = scanner.nextDouble();

                String moneda = switch (opcion) {
                    case 1 -> "ARS";
                    case 2 -> "BOB";
                    case 3 -> "BRL";
                    case 4 -> "CLP";
                    case 5 -> "COP";
                    default -> null;
                };

                if (moneda != null) {
                    double convertido = monto * tasas.get(moneda);
                    System.out.printf("💱 %.2f USD = %.2f %s%n", monto, convertido, moneda);
                } else {
                    System.out.println("Opción inválida.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
