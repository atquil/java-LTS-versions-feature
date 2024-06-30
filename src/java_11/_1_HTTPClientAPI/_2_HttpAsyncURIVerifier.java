package java_11._1_HTTPClientAPI;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author atquil
 */

public class _2_HttpAsyncURIVerifier {

    public static void main(String[] args) {
        // List of Domain to verify  URIs (Uniform Resource Identifiers)
        List<URI> uris = Stream.of(
                        "https://www.google.com/",
                        "https://www.github.com/",
                        "https://www.yahoo.com/",
                        "https://alphabetagamaaasfdasdfasdfasdfadfasd.com/",
                        "https://atquil.com/"
                )
                .map(URI::create)
                .collect(toList());

        // Configure an HttpClient instance, with 10 seconds timeout and follows redirects
        HttpClient configHttpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        // Asynchronously verify all URIs
        CompletableFuture[] verifyAllUris = verifyAllUris(uris, configHttpClient);

        System.out.println("Verifying URLS");
        // Wait for completion (optional)
        CompletableFuture.allOf(verifyAllUris).join();
        configHttpClient.close();
    }

    //Verify all URIs
    private static CompletableFuture[] verifyAllUris(List<URI> uris, HttpClient httpClient) {
        return uris.stream()
                .map(uri -> verifyUri(httpClient, uri))
                .toArray(CompletableFuture[]::new);
    }

    // Asynchronously verify a single URI
    private static CompletableFuture<Void> verifyUri(HttpClient httpClient, URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .timeout(Duration.ofSeconds(5))
                .uri(uri)
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .thenApply(statusCode -> statusCode == 200)
                .exceptionally(ex -> false)
                .thenAccept(valid -> {
                    if (valid) {
                        System.out.println("[SUCCESS] Verified " + uri);
                    } else {
                        System.out.println("[FAILURE] Could not verify " + uri);
                    }
                });
    }
}