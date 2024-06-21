package java_11._1_HTTPClientAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @author atquil
 */
public class _1_HttpSyncUriVerifier {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Initialize a URI representing the website to verify
        URI uri = URI.create("https://atquil.com");

        //Default
        // HttpClient client = HttpClient.newHttpClient();

        // Configure an HttpClient instance
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        verifyURI(uri,httpClient);
    }

    //Synchronous Request
    private static void verifyURI(URI uri, HttpClient client) throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(uri)
                        .header("Content-Type", "application/json")
                        .GET()
                        //.POST(HttpRequest.BodyPublishers.ofString(someData))
                        .build();
        try {
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print response headers
        response.headers()
                .map().forEach((header,value)-> System.out.println(header+":"+value));
        System.out.println(response.statusCode() == 200 ? "[SUCCESS]" : "[FAILURE]");
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }


}
