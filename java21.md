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

```text
Runnable runnable = () -> System.out.println("Inside Runnable");

//1
Thread.startVirtualThread(runnable);

//2
Thread virtualThread = Thread.ofVirtual().start(runnable);

//3
var executor = Executors.newVirtualThreadPerTaskExecutor();
executor.submit(runnable);
```


The virtual threads are JVM-managed lightweight threads that will help in writing high-throughput concurrent applications

- With the introduction of virtual threads, it becomes possible to execute millions of virtual threads using only a few operating system threads. The most advantageous aspect is that there is no need to modify existing Java code. All that is required is instructing our application framework to utilize virtual threads in place of platform threads.
- Please note that virtual threads are not faster than platform threads. They should be used to scale the number of concurrent tasks that spend much of their time waiting.
- For example, server applications that handle many client requests and perform blocking I/O operations. For resource/processing-intensive tasks, continue using the traditional platform threads, as virtual threads will not provide any advantage.
- Virtual threads now always support thread-local variables. 
- Virtual threads are created through the Thread.Builder API are also monitored through their lifetime and observable in the new thread dump

`Thread.ofVirtual()` : Creating specific virtual thread. 
```java
public class _1_ThreadOfVirtual {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
        System.setProperty("jdk.virtualThreadScheduler.minRunnable", "1");

        Thread v1 = Thread.ofVirtual().name("long-running-thread").start(
                () -> {
                    var thread = Thread.currentThread();
                    while (true) {
                        try {
                            Thread.sleep(250L);
                            System.out.println(STR."[\{thread}] - Handling http request ....");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        Thread v2 = Thread.ofVirtual().name("entertainment-thread").start(
                () -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    var thread = Thread.currentThread();
                    System.out.println(STR."[\{thread}] - Executing when 'http-thread' hit 'sleep' function");
                }
        );

        //Execution::
        v1.join();
        v2.join();
    }
}

```
Output::
```text
[VirtualThread[#21,long-running-thread]/runnable@ForkJoinPool-1-worker-1] - Handling http request ....
[VirtualThread[#21,long-running-thread]/runnable@ForkJoinPool-1-worker-1] - Handling http request ....
[VirtualThread[#21,long-running-thread]/runnable@ForkJoinPool-1-worker-1] - Handling http request ....
[VirtualThread[#23,entertainment-thread]/runnable@ForkJoinPool-1-worker-1] - Executing when 'http-thread' hit 'sleep' function
[VirtualThread[#21,long-running-thread]/runnable@ForkJoinPool-1-worker-1] - Handling http request ....
[VirtualThread[#21,long-running-thread]/runnable@ForkJoinPool-1-worker-1] - Handling http request ....
......
```
`newVirtualThreadPerTaskExecutor() `: This executor creates a new virtual thread for each task
```java
public class _2_NewVirtualThreadPerTaskExecutor {
    public static void main(String[] args) {
        //For each task, creating a virtual thread
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.rangeClosed(1, 10).forEach(i -> {
                executor.submit(() -> {
                    var thread = Thread.currentThread();
                    System.out.println("Creating a virtual thread (" + thread + "): " + i);
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }
}

```

Output:
```text
Creating a virtual thread (VirtualThread[#25]/runnable@ForkJoinPool-1-worker-4): 4
Creating a virtual thread (VirtualThread[#30]/runnable@ForkJoinPool-1-worker-8): 9
Creating a virtual thread (VirtualThread[#26]/runnable@ForkJoinPool-1-worker-5): 5
Creating a virtual thread (VirtualThread[#28]/runnable@ForkJoinPool-1-worker-7): 7
Creating a virtual thread (VirtualThread[#31]/runnable@ForkJoinPool-1-worker-2): 10
Creating a virtual thread (VirtualThread[#21]/runnable@ForkJoinPool-1-worker-1): 1
Creating a virtual thread (VirtualThread[#29]/runnable@ForkJoinPool-1-worker-8): 8
Creating a virtual thread (VirtualThread[#23]/runnable@ForkJoinPool-1-worker-2): 2
Creating a virtual thread (VirtualThread[#27]/runnable@ForkJoinPool-1-worker-6): 6
Creating a virtual thread (VirtualThread[#24]/runnable@ForkJoinPool-1-worker-3): 3
```
## String Improvements

### String Template

Java offers several mechanisms to compose strings with string literals and expressions. Some of these are `String concatenation`, `StringBuilder class`, `String class format() method`, and the `MessageFormat` class

![img.png](src%2Fjava_21%2F_4_StringTemplate%2Fimg.png)

`STR`: Template Processor
`Template`: The template strings can contain variables, methods or fields, computed at run time, to produce a formatted string as output.

```java
public class _1_StringTemplate {
    public static void main(String[] args) {

        String tutorial = "Atquil";
        //let message = `Greetings ${ tutorial }!`;  			//TypeScript
        String welcomeText = STR."Welcome to \{tutorial}";
        System.out.println(welcomeText);
    }
}

```
Output:

```text
Welcome to Atquil
```

### New String Methods

The String class has been extended by the following methods:

1. `String.indexOf(String str, int beginIndex, int endIndex)` – searches the specified substring in a subrange of the string.
2. `String.indexOf(char ch, int beginIndex, int endIndex) `– searches the specified character in a subrange of the string.
3. `String.splitWithDelimiters(String regex, int limit)` – splits the string at substrings matched by the regular expression and returns an array of all parts and splitting strings. The string is split at most limit-1 times, i.e., the last element of the array could be further divisible.

```java
public class _2_StringMethods {
    public static void main(String[] args) {
        String string = "the red brown fox jumps over the lazy dog";

        System.out.println("Finding index of red in string:"+string.indexOf("red",1,10));

        String[] parts = string.splitWithDelimiters(" ", 2);
        Arrays.stream(parts).forEach(System.out::println);
        System.out.println(String.join("", parts));
    }
}

```

Output:
```text
Finding index of red in string:4
the
 
red brown fox jumps over the lazy dog
the red brown fox jumps over the lazy dog
```

### StringBuilder and StringBuffer

Both StringBuilder and StringBuffer have been extended by the following two methods:

1. `repeat(CharSequence cs, int count)` – appends to the StringBuilder or StringBuffer the string cs – count times.
2. `repeat(int codePoint, int count)` – appends the specified Unicode code point to the StringBuilder or StringBuffer – count times. A variable or constant of type char can also be passed as code point.

```java
public class _3_StringBuilderStringBuffer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.repeat("Hello Everyone ", 2);
        sb.repeat(0x1f600, 5);
        sb.repeat('!', 3);
        System.out.println(sb);
    }
}

```

```text
Hello Everyone Hello Everyone 😀😀😀😀😀!!!
```

## Sequenced Collection

Sequenced collections introduce a new built-in Java API, enhancing operations on **ordered datasets**

SequencedCollection defines the following methods:

* `void addFirst(E)` – inserts an element at the beginning of the collection 
* `void addLast(E)` – appends an element to the end of the collection 
* `E removeFirst()` – removes the first element and returns it 
* `E removeLast()` – removes the last element and returns it
* `reversed()`
For immutable collections, all four methods throw an` UnsupportedOperationException`.
![img.png](src%2Fjava_21%2F_5_SequencedCollection%2Fimg.png)

```java
public class SequencedCollection {
    public static void main(String[] args) {

        //List.of() will give immutable list which cannot be modified. 
        List<String> names = new ArrayList<>(); // Use ArrayList for mutability
        names.add("Anand");
        names.add("Atul");
        names.add("Atquil");

        //Earlier to get Last Element --> names.size()-1
        var lastName = names.getLast();
        var firstName = names.getFirst();
        System.out.println("Last Name:"+lastName+" and First name:"+firstName);

        System.out.println("-------------");
        names.reversed();
        names.forEach(System.out::println);

        System.out.println("-------------");
        names.removeLast();//Error
        names.removeFirst();
        names.addFirst("Done");
        names.forEach(System.out::println);

    }
}
```
```text
Last Name:Atquil and First name:Anand
-------------
Anand
Atul
Atquil
-------------
Done
Atul
```
## Record Pattern

Records in Java are transparent and immutable carriers for data (similar to POJO).

The record patterns eliminate the declaration of local variables for extracted components and initialize the components by invoking the accessor methods when a value is matched against the pattern

### Record Pattern - Class
```java
interface Shape {
    default void myShape(String myshape) {
        System.out.println("My Shape is ::" + myshape);
    }
}

record Circle(String type, long unit) implements Shape {
    @Override
    public void myShape(String myshape) {
        System.out.println("Circle shape: " + myshape);
    }
}

record Square(String type, long unit) implements Shape {
    @Override
    public void myShape(String myshape) {
        System.out.println("Square shape: " + myshape);
    }
}

public class RecordPattern {
    public static void main(String[] args) {
        Circle circle = new Circle("Circle", 50);
        circle.myShape("Circle");
        System.out.println(circle.type() + " with radius: " + circle.unit());

        Square square = new Square("Square",33);
        // Switch with Record
        switch ((Shape) circle) {
            case Circle c -> System.out.println("Switched to Circle");
            //Now can be shown like this as well
            case Square (String x, long size) -> System.out.println("Switched to Square");
            default -> System.out.println("Unknown shape");
        }
    }
}

```

Output::
```text
Circle with radius:50
```



