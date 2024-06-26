# Java Version 11
Oracle also stopped supporting Java 8 in January 2019
Oracle released Java 11 in September 2018

##
## HttpClient API

- HttpClient class is highly versatile it can handle 
  - **synchronous** : `HttpClient.send()`
  - **asynchronous** communication : `HttpClient.sendAsync()` method returns a **CompletableFuture**
- HttpClient also supports **HTTP/2 and WebSocket**

| Feature                   | HTTP/1.1                                   | HTTP/2                                          |
|---------------------------|--------------------------------------------|-------------------------------------------------|
| **Protocol format**       | Textual (plain text messages)              | Binary (messages in binary format)              |
| **Head of line blocking** | Yes (blocks subsequent requests)           | No (fully multiplexed)                          |
| **Connection reuse**      | Persistent connections (keep-alive header) | Single connection for multiple requests         |
| **Header compression**    | 	None                                      | HPACK (efficient header compression)            |
| **Resource inlining**     | Requests resource inlining                 | Server-initiated PUSH frames for multiple pages |

- http client API uses **builder pattern** for creating complex objects
- Uses `BodyPublishers.ofString()` to send the data
- Uses `BodyHandlers` class to receive the data
- NOTE : Though, HttpClient has several advantage, one potential pitfall is the HttpClient API is immutable, which means you can’t modify its state after it’s been created. This can lead to unexpected behavior if you’re not careful.
- `close()`: Closing the HttpClient ensures that any underlying resources (such as connections, threads, or sockets) are properly released. It’s especially important if your application runs for an extended period or if you create multiple HttpClient instances.
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


## Single File Compilation 

```java
public class SingleFileProgram{
  public static void main(String[] args) {
    System.out.println("I must be single file without any dependency");
  }
}
```

Earlier to run the java file we needed to compile then run

```
1. Switch to directory location
2. Compile the file : javac src/java_11/_2_SingleFileProgram/SingleFileProgram.java
      This will create a SingleFileProgram.class
3. Now run the file using : java SingleFileProgram
```
Now, you can directly do 

Output
```
❯ java src/java_11/_2_SingleFileProgram/SingleFileProgram.java
I must be single file without any dependency


```
```
Note that the program cannot use any external dependencies other than the java.base module. And program can be only single-file program
```

## String API Features

Java 11 adds a few new methods to the String class like

```
Interview Question
Why trim(), if we already have strip() ?
strip () is the “Unicode-aware” version of trim () whose definition of whitespace goes back from Java’s first versions.
```

```java
public class StringObjectFeatures {
    public static void main(String[] args) {

        System.out.println("---------Multiple Lines : lines(), isBlank(), isEmpty(), strip() ---------");
        // Multiple Lines Reading
        String multilineString = "Line1 \n Line2 \n Line3 \n  \nLine5" ;

        // Iterate on lines
        multilineString.lines()
                //Checks for blank and empty and removes Line4
                .filter(line ->!line.isBlank() && !line.isEmpty())
                // Strip
                .map(String::strip)
                .map(String::stripTrailing) // Remove from last
                .map(String::stripLeading)
                .forEach(System.out::println);


        System.out.println("---------Repeat ---------");
        System.out.println("Atquil will be repated 5 times:: "+"atquil".repeat(5));
        
    }
}

```

Output:

```
---------Multiple Lines : lines(), isBlank(), isEmpty(), strip() ---------
Line1
Line2
Line3
Line5
---------Repeat ---------
Atquil will be repated 5 times:: atquilatquilatquilatquilatquil
```

## Files.readString(), Files.writeString() with Path.of() 

Using overloaded methods, Java 11 has made it very easy to read and write on the file

We can create a path easily by using `Path.of()`, instead of using Paths.get() or File.toPath()

Adding a text file TextFile.txt
```text
Hi Everyone
This is a test file

Good By!
```

Reading and writing on file
```java
public class FileReadStringAndWriteString {
  public static void main(String[] args) throws IOException {

    System.out.println("------------ Reading Using FileSystem -------------");
    //Older ways of creating the path
    Path path = FileSystems.getDefault().getPath("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
    String multiLinesFile = Files.readString(path, StandardCharsets.UTF_8);

    Path betterWayOfCreatingPath = Path.of("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
    String multiLinesFileWithNewPath= Files.readString(betterWayOfCreatingPath, StandardCharsets.UTF_8);

    multiLinesFileWithNewPath.lines()
            .forEach(System.out::println);

    System.out.println("------------ Reading Using FileSystem with Path.of() -------------");
    multiLinesFileWithNewPath.lines()
            .forEach(System.out::println);


    System.out.println("------------ Reading Using BufferReader-------------");
    BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    reader.lines().forEach(System.out::println);

    System.out.println("------------ Writing on file -------------");
    Files.writeString(path,"I am a new line",StandardCharsets.UTF_8);
    Files.readString(path, StandardCharsets.UTF_8)
            .lines()
            .forEach(System.out::println);

    //We can also create Path of file instead of using Paths.get() or File.toPath() we can use

    //Relative Path <path>
    Path path1 = Path.of("src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
    Path path2 = Path.of("src","java_11","_4_FileReadStringAndWriteString","TestFile.txt");
    Path path3 = Path.of("src","java_11","_4_FileReadStringAndWriteString/TestFile.txt");

    //Absolute Path /<path>
    Path path5 = Path.of("/src/java_11/_4_FileReadStringAndWriteString/TestFile.txt");
    Path path6 = Path.of("/src","java_11","_4_FileReadStringAndWriteString","TestFile.txt");
    Path path7 = Path.of("/src","java_11","_4_FileReadStringAndWriteString/TestFile.txt");
    Path path8 = Path.of("/","/src","java_11","_4_FileReadStringAndWriteString/TestFile.txt");

  }
}


```

Output:
```
------------ Reading Using FileSystem -------------
Hi Everyone
This is a test file

Good By!
------------ Reading Using FileSystem with Path.of() -------------
Hi Everyone
This is a test file

Good By!
------------ Reading Using BufferReader-------------
Hi Everyone
This is a test file

Good By!
------------ Writing on file -------------
I am a new line

```

```
To define an absolute path, the first part must start with "/" on Linux and macOS – and with a drive letter, such as "C:" on Windows.
```

## Collection To Array
```java
public class CollectionToArray {
    public static void main(String[] args) {

        List<String> names = List.of("Atquil","Atul","Anand");


        //Convert to Array (fixed size)
        String[] namesArrBeforeJava11 = names.toArray(new String[names.size()]);		//Before Java 11
        String[] namesArray = names.toArray(String[]::new);

        Arrays.stream(namesArrBeforeJava11).forEach(System.out::println);
        System.out.println("-----------");
        Arrays.stream(namesArray).forEach(System.out::println);
    }
}

```
Output:

```
Atquil
Atul
Anand
-----------
Atquil
Atul
Anand
```

### Local Variable Syntax for Lambda parameters

What is an implicitly typed lambda expression?

```java
public class LocalVariableSyntaxForLambda {
    public static void main(String[] args) {

        List<String> someNames = List.of("ATQUIL", "ATUL", "ANAND");


        // Using an inline lambda expression with filter
        Optional<String> result = someNames.stream()
                .filter(name -> name.equals("ATUL"))
                .findFirst();

        System.out.println("--------Explicit Lambda Expression------");
        result.ifPresentOrElse(
                (String foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );
        System.out.println("--------Implicit Lambda Expression with var ------");
        result.ifPresentOrElse(
                (var foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );
        System.out.println("--------Implicit Lambda Expression without type ------");
        result.ifPresentOrElse(
                (foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );

        // But why write "var" when you can omit the types completely,
        System.out.println("--------Implicit Lambda Expression without type ------");
        result.ifPresentOrElse(
                (@NonNull var foundName) -> System.out.println("Found name: " + foundName),
                () -> System.out.println("Name not found")
        );
    }
}

```
Output
```
--------Explicit Lambda Expression------
Found name: ATUL
--------Implicit Lambda Expression with var ------
Found name: ATUL
--------Implicit Lambda Expression without type ------
Found name: ATUL
--------Implicit Lambda Expression without type ------
Found name: ATUL
```

```
Interview Question ?
But why write "var" when you can omit the types completely,
An annotation, must have placed at the type i.e. an explict type or "var". 
```
## Epsilon Garbage Collector : Epsilon GC
```
Interview Question ? What is Garbage Collector ? 
In Java, garbage collection (GC) is the process by which the Java Virtual Machine (JVM) automatically manages memory.
- When Java programs run on the JVM, they create objects in heap. Here if object is unreachable i.e no part of program maintains a reference (null values), then GC identifies and removes objects thus freeing up memory.  
- The garbage collector runs as a daemon thread in the background.
```

```
In a production environment, you would typically use a garbage collector that actually reclaims memory, such as G1 or Shenandoah.
```
Epsilon GC: 
- It manages the allocation of objects on the heap – but it has no garbage collection process to release the objects again.
- What is the purpose of a garbage collector that does not collect garbage?
  - **Performance tests**: In micro benchmarks, for example, where you compare different implementations of algorithms with each other, a regular garbage collector is a hindrance, as it can influence the execution times and thus falsify the measurement results. By using Epsilon GC, you can exclude such influences.
  - **Extremely short-lived applications**, such as those developed for AWS Lambda, should be terminated as quickly as possible. A garbage collection cycle would be a waste of time if the application was terminated a few milliseconds later anyway.
  - **Eliminating latencies**: If developers have a good understanding of the memory requirements of their application and entirely (or almost entirely) dispense with object allocations, Epsilon GC enables them to implement a latency-free application.
- This is a ‘no-op’ (no operation) garbage collector, meaning it `allocates memory but does not actually reclaim it`.
Type this in command line to activate.
```
-XX:+UseEpsilonGC
```
Run the program using this 
```
// No problem, as GC is managed
java src/java_11/_7_EpsilonGarbageCollector/EpsilonGarbageCollector.java
```
```
//Will throw OutOfMemoryError, as Epsilon does not reclaim the memory. 
java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC src/java_11/_7_EpsilonGarbageCollector/EpsilonGarbageCollector
```
This program simply allocates a block of memory (1MB) in a loop. If you run this program with a standard garbage collector, it will run without any problems because the garbage collector will periodically free up memory that is no longer in use.
```java
public class EpsilonGarbageCollector {
    public static void main(String[] args) {
        // Let's creat 1MB size for byte
        for (int i = 0; i < 1000; i++) {
            byte[] b = new byte[1024 * 1024];
        }
    }
    // java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC EpsilonGarbageCollector
}
```

| Topic                   | Epsilon                                                                                                                                                                                                                                                                         | GarbageFirst(G1)                                                                                                                                                    | Shenandoah                                                                                                                                                                                                                                                                         |
|-------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Description             | Epsilon is a non-operational or passive garbage collector. It allocates memory for the application but doesn’t collect unused objects. When the application exhausts the Java heap, the JVM shuts down. Essentially, Epsilon allows applications to run out of memory and crash | G1 is a server-style collector designed for multiprocessor machines with a large amount of memory. It aims to achieve high throughput along with short pause times. | Shenandoah is a low pause time garbage collector that performs more garbage collection work concurrently with the running Java program. It can relocate objects concurrently, reducing pause times. Unlike G1, Shenandoah’s pause times are not directly proportional to heap size |
| Concurrent Collection   | No                                                                                                                                                                                                                                                                              | Yes                                                                                                                                                                 | Yes                                                                                                                                                                                                                                                                                |
| Pause Times             | N/A                                                                                                                                                                                                                                                                             | Variable                                                                                                                                                            | Low                                                                                                                                                                                                                                                                                |
| Heap Size Consideration | N/A                                                                                                                                                                                                                                                                             | Large heaps are beneficial for G1, as they amortize collection work more effectively. However, larger heaps may result in longer individual pause times             | Shenandoah excels in scenarios where minimizing pause times is critical, even for large heaps.                                                                                                                                                                                     |

## Java Flight Recorder and JDK Mission Control are powerful tools for monitoring and diagnosing Java applications

```text
In summary, JFR and JMC together create a complete toolchain for continuous monitoring and post-incident analysis of Java applications, helping developers and administrators keep their systems healthy and efficient
```
