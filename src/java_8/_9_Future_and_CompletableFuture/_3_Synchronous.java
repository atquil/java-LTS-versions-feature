package java_8._9_Future_and_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author atquil
 */
public class _3_Synchronous {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main thread::"+Thread.currentThread().getName());

        CompletableFuture<String> name = CompletableFuture.supplyAsync(() ->{
                    System.out.println("Async so different thread"+Thread.currentThread().getName());
                    return "Atquil";
        });
        CompletableFuture<Integer> nameLength = name.thenApply(value -> {
            System.out.println("Synchronous, so parent thread::"+Thread.currentThread().getName());
            return value.length();
        });
        System.out.println("Name:: "+name.get());
        System.out.println("CharLength::"+nameLength.get());


    }
}
