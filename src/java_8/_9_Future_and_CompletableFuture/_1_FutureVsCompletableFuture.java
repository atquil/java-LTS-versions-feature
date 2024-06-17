package java_8._9_Future_and_CompletableFuture;

import java.util.concurrent.*;

/**
 * @author atquil
 */
public class _1_FutureVsCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);


        Future<Integer> future = executorService.submit(() -> {
            // Simulate long running task
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Future Thread Pool Executor::"+Thread.currentThread().getName());
            return 123;
        });

        // --- We can do some calculation this the timeOut
        System.out.println("Executed First");

        //Blocking, as if we need to use the future return value it will be blocked here
        Integer result = future.get(); // This line blocks, waiting for the result
        executorService.shutdown();

        System.out.println("CompletableFuture");
        // CompletableFuture
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
        // Simulate long running task
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("CompletableFuture Thread Pool Executor::"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 123;
        });

        // ... do something else ...
        System.out.println("Non-blocking");
        // Non-blocking and can handle the result with a callback
        completableFuture.thenAccept(result2 -> System.out.println(result));

        System.out.println("Still non blocking");
        // Or block and get the result directly, if needed
        Integer result2 = completableFuture.get();
    }
}
