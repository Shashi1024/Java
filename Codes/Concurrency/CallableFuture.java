package Codes.Concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


// for the invokeAll() just take a collection like List<Callable<String>> and add all tasks to it and use invokeAll()
// it will return a list of futures List<Futures<String>>

// for the invokeAny() same as above consider a collection and use invokeAny() method it it executes a task and returns in result
// all other tasks are discarded



public class CallableFuture {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> callableTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": Callable task starting...");
            try {
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(threadName + ": Callable task interrupted!");
                throw new InterruptedException("Callable was interrupted");
            }
            System.out.println(threadName + ": Callable task finished.");
            return "Result from Callable Task!";
        };

        // submit callable for execution and it will return a Future<T>
        Future<String> futureResult = executor.submit(callableTask);

        // isDone()
        System.out.println("Is task done (immediately after submission)? " + futureResult.isDone());

        // cancel(), isCancelled()
        // boolean cancelled = futureResult.cancel(false);
        // System.out.println("Attempted to cancel task (non-interrupting): " + cancelled);
        System.out.println("Is task cancelled? " + futureResult.isCancelled());

        // get() it is a Blocking call (until the tasks completes)
        System.out.println("Waiting for task to complete and get result...");
        try {
            String result = futureResult.get(); 
            System.out.println("Received result: " + result);
        } catch (ExecutionException e) {
            // ExecutionException is thrown if the task itself threw an exception
            System.err.println("Task threw an exception: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            // CancellationException is thrown if the task was cancelled
            System.err.println("Task was cancelled: " + e.getMessage());
        } catch (InterruptedException e) {
            // InterruptedException is thrown if the current thread was interrupted
            System.err.println("Main thread interrupted while waiting for result: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        // get() with timeout
        Callable<String> timeoutCallable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": Timeout Callable task starting...");
            try {
                TimeUnit.MILLISECONDS.sleep(2500); // This will take longer than the timeout
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(threadName + ": Timeout Callable task interrupted!");
            }
            return "Timeout task completed!";
        };

        Future<String> timeoutFuture = executor.submit(timeoutCallable);
        System.out.println("\nWaiting for timeout task with a 1-second timeout...");
        try {
            String resultWithTimeout = timeoutFuture.get(1, TimeUnit.SECONDS);
            System.out.println("Received result with timeout: " + resultWithTimeout);
        } catch (TimeoutException e) {
            System.err.println("TimeoutException: Task did not complete within the specified time.");
            // if it timed out and is no longer needed just cancel it
            timeoutFuture.cancel(true); // Interrupt and cancel
        } catch (ExecutionException | CancellationException | InterruptedException e) {
            System.err.println("Error with timeout task: " + e.getMessage());
            Thread.currentThread().interrupt(); 
        }


        System.out.println("Is task done (after get() call)? " + futureResult.isDone());

        
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("ExecutorService terminated.");
    }
}
