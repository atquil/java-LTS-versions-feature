package java_21._3_VirtualThread;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author atquil
 */
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
