package java_21._2_HttpClientEnhancedLifecycleManagement;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;

/**
 * @author atquil
 */
public class HttpClientEnhancedLifecycleManagement {
    public static void main(String[] args) throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        List<String> httpMethods = List.of("shutdown()","shutdownNow()","shutdown()");

        httpMethods.forEach(method->checkHttpClient(method));

        // ... use the client for various tasks
        client.close();

        // ... maybe after some operations
        client.shutdown();
        // For instant shutdown, without waiting:
        client.shutdownNow();



        client.shutdown();
        if (client.awaitTermination(Duration.ofMinutes(1))) {
            System.out.println("Client terminated successfully.");
        } else {
            System.out.println("Client termination took longer than expected.");
        }


        client.shutdown();
// ... after some time
        if (client.isTerminated()) {
            System.out.println("Client has terminated.");
        }
    }

    private static void checkHttpClient(String method) {
        switch (method){
            case "shutdown()" ->,
            case "shutdownNow()" -> {},
            case "shutdown()" -> {},
            default -> System.out.println("Not found");
        }
    }
}
