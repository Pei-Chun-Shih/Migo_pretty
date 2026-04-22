import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class AccountServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/accounts", (HttpExchange exchange) -> {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStream inputStream = exchange.getRequestBody();
                String requestBody = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                String response = "{ \"message\": \"Account created successfully\", \"request\": " + requestBody + " }";

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(201, response.getBytes(StandardCharsets.UTF_8).length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            } else {
                String response = "{ \"error\": \"Method not allowed\" }";
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(405, response.getBytes(StandardCharsets.UTF_8).length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        });

        server.start();
        System.out.println("Server started on http://localhost:5000");
    }
}
