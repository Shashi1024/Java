package Codes.Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPools {
    public static void main(String[] args) throws InterruptedException {

        // --- 1. newCachedThreadPool() ---
        System.out.println("--- newCachedThreadPool Example ---");
        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            cachedExecutor.submit(() -> {
                System.out.println("Cached Task " + taskId + " running on: " + Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        cachedExecutor.shutdown();
        cachedExecutor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("CachedThreadPool finished.\n");


        // --- 2. newSingleThreadExecutor() ---
        System.out.println("--- newSingleThreadExecutor Example ---");
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            singleExecutor.submit(() -> {
                System.out.println("Single Task " + taskId + " running on: " + Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        singleExecutor.shutdown();
        singleExecutor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("SingleThreadExecutor finished.\n");


        // --- 3. newScheduledThreadPool(int corePoolSize) ---
        System.out.println("--- newScheduledThreadPool Example ---");
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);

        // task to run after a delay
        System.out.println("Scheduling a task to run in 1.5 seconds...");
        scheduledExecutor.schedule(() -> {
            System.out.println("Scheduled Task (one-time) executed at: " + System.currentTimeMillis() +
                    " on " + Thread.currentThread().getName());
        }, 1500, TimeUnit.MILLISECONDS);

        // Schedule a task to run periodically (fixed rate) after an initial delay of 1 sec
        System.out.println("Scheduling a task to run every 1 second (fixed rate)...");
        scheduledExecutor.scheduleAtFixedRate(() -> {
            System.out.println("Scheduled Task (fixed rate) executed at: " + System.currentTimeMillis() +
                    " on " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 0, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(5);

        scheduledExecutor.shutdown();
        System.out.println("ScheduledThreadPool shutdown initiated.");
        scheduledExecutor.awaitTermination(1, TimeUnit.SECONDS);
        
        System.out.println("ScheduledThreadPool finished.");
    }
}
