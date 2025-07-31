package Codes.Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedKeyword {
    private int count = 0;

    // synchronized method
    public synchronized void incrementSynchronizedMethod() {
        count++;
        System.out.println(Thread.currentThread().getName() + " (Method) - Count: " + count);
    }

    public void incrementSynchronizedBlock() {
        // synchronized block
        // 'this' object is used as the lock. Any other thread trying to
        // acquire the lock on 'this' will be blocked.
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + " (Block) - Count: " + count);
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedKeyword example = new SynchronizedKeyword();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        System.out.println("--- Demonstrating synchronized method ---");
        example.count = 0; 
        for (int i = 0; i < 10; i++) {
            executor.submit(example::incrementSynchronizedMethod); 
        }
        executor.shutdown(); 
        executor.awaitTermination(1, TimeUnit.SECONDS); 
        System.out.println("Final count (synchronized method): " + example.getCount() + "\n");


        System.out.println("--- Demonstrating synchronized block ---");
        example.count = 0; 
        ExecutorService executor2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor2.submit(example::incrementSynchronizedBlock); 
        }
        executor2.shutdown();
        executor2.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Final count (synchronized block): " + example.getCount());

    }
}
