# Java Version 11
Oracle also stopped supporting Java 8 in January 2019
Oracle released Java 11 in September 2018

##
## HttpClient API

- HttpClient class is highly versatile it can handle 
  - **synchronous** : `HttpClient.send()`
  - **asynchronous** communication : `HttpClient.sendAsync()` method returns a **CompletableFuture**
- HttpClient also supports **HTTP/2 and WebSocket**
- http client API uses **builder pattern** for creating complex objects
- Uses `BodyPublishers.ofString()` to send the data
- Uses `BodyHandlers` class to receive the data
- NOTE : Though, HttpClient has several advantage, one potential pitfall is the HttpClient API is immutable, which means you can’t modify its state after it’s been created. This can lead to unexpected behavior if you’re not careful.

### Synchronous 
```java
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

```

Output:

```
status:[200]
content-length:[114]
content-type:[text/html]
date:[Fri, 21 Jun 2024 03:03:37 GMT]
[SUCCESS]
```

### Asynchronous

```java
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
```

Output:
```
Verifying URLS
[SUCCESS] Verified https://atquil.com/
[FAILURE] Could not verify https://alphabetagamaaasfdasdfasdfasdfadfasd.com/
[SUCCESS] Verified https://www.github.com/
[SUCCESS] Verified https://www.yahoo.com/
[SUCCESS] Verified https://www.google.com/
```








## Epsilon Garbage Collector

This is a ‘no-op’ (no operation) garbage collector, meaning it `allocates memory but does not actually reclaim it`.




