package Codes.Concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQ {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3); 

        ExecutorService executor = Executors.newFixedThreadPool(2); // One producer, one consumer

        // --- Producer Task ---
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 7; i++) { 
                    System.out.println(Thread.currentThread().getName() + ": Attempting to put " + i);
                    // put() method: Blocks if the queue's capacity is full.
                    // It will wait on the 'notFull' condition.
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName() + ": Successfully put " + i + ". Queue size: " + queue.size());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " Producer interrupted.");
            }
        };

        // --- Consumer Task ---
        Runnable consumer = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500); // allowing producer to insert initially
                for (int i = 0; i < 7; i++) { 
                    System.out.println(Thread.currentThread().getName() + ": Attempting to take item...");
                    // take() method: Blocks if the queue is empty.
                    // It will wait on the 'notEmpty' condition.
                    Integer item = queue.take();
                    System.out.println(Thread.currentThread().getName() + ": Successfully took " + item + ". Queue size: " + queue.size());
                    TimeUnit.MILLISECONDS.sleep(300); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " Consumer interrupted.");
            }
        };

        executor.submit(producer);
        executor.submit(consumer);

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
       
        System.out.println("\nArrayBlockingQueue Mechanism Finished.");
    }
}
