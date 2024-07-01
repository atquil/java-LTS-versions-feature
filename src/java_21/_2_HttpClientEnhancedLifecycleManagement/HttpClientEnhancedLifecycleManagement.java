package java_21._2_HttpClientEnhancedLifecycleManagement;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

/**
 * @author atquil
 */
public class HttpClientEnhancedLifecycleManagement {
    public static void main(String[] args) throws InterruptedException {


        List<String> httpMethods = List.of("close()","shutdown()","shutdownNow()","shutdown()");

        httpMethods.forEach(HttpClientEnhancedLifecycleManagement::checkHttpClient);

    }

    private static void checkHttpClient(String method)  {

        URI uri = URI.create("https://atquil.com");
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Define a request
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(uri)
                        .header("Content-Type", "application/json")
                        .GET()
                        .build();
        try {
            System.out.println("----------------------");

            switch (method){
                case "close()" -> {
                    System.out.println("close():Close the client gracefully ");
                    HttpResponse<String> response =
                            client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.statusCode() == 200 ? "[SUCCESS]" : "[FAILURE]");
                    client.close();
                    if (client.isTerminated()) {
                        System.out.println("Client has terminated.");
                    }

                }
                case "shutdown()" -> {
                    System.out.println("shutdown() to wait for ongoing requests to finish");
                    HttpResponse<String> response =
                            client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.statusCode() == 200 ? "[SUCCESS]" : "[FAILURE]");
                    client.shutdown();
                    if (client.awaitTermination(Duration.ofMinutes(1))) {
                        System.out.println("Client terminated successfully.");
                    } else {
                        System.out.println("Client termination took longer than expected.");
                    }

                }
                case "shutdownNow()" -> {
                    System.out.println("shutdownNow() to to forcefully shut down the client");
                    HttpResponse<String> response =
                            client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.statusCode() == 200 ? "[SUCCESS]" : "[FAILURE]");
                    client.shutdownNow();
                }
                default -> System.out.println("Not found");
            }

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }


    }
}
