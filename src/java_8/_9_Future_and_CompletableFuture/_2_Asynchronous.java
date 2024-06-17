package java_8._9_Future_and_CompletableFuture;

import java.util.concurrent.*;

/**
 * @author atquil
 */
public class _2_Asynchronous {
    public static void main(String[] args)  {
        System.out.println("Main thread::"+Thread.currentThread().getName());
        // SupplyAsync
        try {

        CompletableFuture<String> someCounting = CompletableFuture
                .supplyAsync(()-> {
                    System.out.println("Thread Pool::"+Thread.currentThread().getName());
                    // Simulate long running task
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return "Done";
                });
        CompletableFuture<Integer> charLength = someCounting.thenApplyAsync(value -> {
                System.out.println("Thread Pool::"+Thread.currentThread().getName());
                return value.length();
            });
        System.out.println(charLength.get());
        } catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }

        // As we can see above all thread pool is overloaded with only ForkJoinPool.commonPool-worker-1. Let's add custom Executor

        //Custom Executor
        Executor someExecutor = Executors.newFixedThreadPool(2);
        try {

            CompletableFuture<String> someCounting = CompletableFuture
                    .supplyAsync(()-> {
                        System.out.println("Thread Pool Executor::"+Thread.currentThread().getName());
                        // Simulate long running task
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            throw new IllegalStateException(e);
                        }
                        return "Done";
                    },someExecutor);
            CompletableFuture<Integer> charLength = someCounting.thenApplyAsync(value -> {
                System.out.println("Thread Pool Executor::"+Thread.currentThread().getName());
                return value.length();
            },someExecutor);
            System.out.println(charLength.get());
        } catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }



        try {
            CompletableFuture<String> getNames = CompletableFuture
                    .supplyAsync(()->"Atquil");
            CompletableFuture<Integer> getViewers = CompletableFuture
                    .supplyAsync(()->10);

            //Now first, let's combine the result of both
            CompletableFuture<String> getNameAndViewers = getViewers.thenCombine(getNames,(first,second)->first +" "+ second);

            System.out.println(getNameAndViewers.get());

        }catch (InterruptedException |ExecutionException  e) {
            throw new RuntimeException(e);
        }


        //Handle Exception

        CompletableFuture<Integer> getNames = CompletableFuture
                .supplyAsync(()->101/0)
                .exceptionally(throwable -> 101010); //If exception return this value

        try {
            System.out.println("Return value::"+getNames.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
