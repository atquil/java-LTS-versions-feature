package java_21._3_VirtualThread;

import static java.lang.StringTemplate.STR;

/**
 * @author atquil
 */
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
