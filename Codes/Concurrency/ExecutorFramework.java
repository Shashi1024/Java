package Codes.Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {
    public static void main(String[] args) throws InterruptedException {

        // creating a thread pool of fixed size (reuses those threads)
        ExecutorService executor = Executors.newFixedThreadPool(3); // Pool of 3 threads

        System.out.println("Submitting tasks...");

        Runnable task1 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Task 1 running on: " + threadName);
            try {
                TimeUnit.MILLISECONDS.sleep(200); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println(threadName + " was interrupted.");
            }
        };

        Runnable task2 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Task 2 running on: " + threadName);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(threadName + " was interrupted.");
            }
        };

        Runnable task3 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Task 3 running on: " + threadName);
            try {
                TimeUnit.MILLISECONDS.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(threadName + " was interrupted.");
            }
        };

        Runnable task4 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Task 4 running on: " + threadName);
            // This task will wait in the queue until a thread becomes available
        };


        executor.execute(task1); // execute() method (from Executor interface)
        executor.submit(task2);  // submit() method (from ExecutorService)
        executor.execute(task3);
        executor.submit(task4);

        System.out.println("All tasks submitted.");

        // shutdown(): Initiates an orderly shutdown in which previously submitted
        // tasks are executed, but no new tasks will be accepted.
        executor.shutdown();
        System.out.println("ExecutorService shutdown initiated.");
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("ExecutorService terminated.");
    }
}
