# Java 21
Released on 19-Sep-2023

## SwitchWithPatternMatching

Now we can use **null and objects in switch pattern**

```java

class Employee{
    int id;
    Employee(Integer id){
        this.id =id;
    }
}
public class SwitchWithPatternMatching {
    public static void main(String[] args) {
        //Switch can happen on null as well
        System.out.println(checkUsingSwitch(null));
        System.out.println("----------------");
        // Switch on Object
        Employee employee = new Employee(1);
        System.out.println(checkUsingSwitch(employee));
        System.out.println("----------------");
        // Switch on String
        String someName = "Atquil";
        System.out.println(checkUsingSwitch(someName));
    }

    private static String checkUsingSwitch(Object object) {
        String returnString = "";
        switch (object){
            case null -> returnString ="Null object";
            case Employee e -> returnString = "Employee Id:: "+e.id;
            case String s -> returnString = "Object is of type string:"+s;
            default ->returnString ="Default";

        }
        return returnString;
    }
}


```
Object:

```text
Null object
----------------
Employee Id:: 1
----------------
Object is of type string:Atquil
```
## HttpClient : close()

 - `close()` method which waits for any ongoing requests to finish before closing
 - `shutdown()` starts closing the HttpClient quickly without waiting for all tasks to end
 - `shutdownNow()` tries to shut down the HttpClient immediately, even if operations are ongoing
 - `awaitTerminationDuration` waits for the client to shut down within a specific time duration
 - `isTerminated()` is the method which checks if the HttpClient has fully shut down.

```java
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

```

Output:

```text
----------------------
close():Close the client gracefully 
[SUCCESS]
Client has terminated.
----------------------
shutdown() to wait for ongoing requests to finish
[SUCCESS]
Client terminated successfully.
----------------------
shutdownNow() to to forcefully shut down the client
[SUCCESS]
----------------------
shutdown() to wait for ongoing requests to finish
[SUCCESS]
Client terminated successfully.
```
## Virtual Thread

```text
Interview Question: 
Why we need virtual thread ?
One of the major issues with platform threads is that they run the code on the OS thread and capture the OS thread throughout its lifetime. There is a limit on the number of OS threads and this creates a scalability bottleneck.
```
The virtual threads are JVM-managed lightweight threads that will help in writing high-throughput concurrent applications

- With the introduction of virtual threads, it becomes possible to execute millions of virtual threads using only a few operating system threads. The most advantageous aspect is that there is no need to modify existing Java code. All that is required is instructing our application framework to utilize virtual threads in place of platform threads.
- Please note that virtual threads are not faster than platform threads. They should be used to scale the number of concurrent tasks that spend much of their time waiting.
- For example, server applications that handle many client requests and perform blocking I/O operations. For resource/processing-intensive tasks, continue using the traditional platform threads, as virtual threads will not provide any advantage.

