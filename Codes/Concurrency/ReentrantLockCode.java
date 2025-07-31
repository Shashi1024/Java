package Codes.Concurrency;

import java.util.concurrent.locks.ReentrantLock;

// bro just tweak the lock and unlock to see what happens


public class ReentrantLockCode {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            // Demonstrate reentrancy: current thread already holds the lock
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + " acquired lock (reentrant).");
            }
            count++;
            System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
        } finally {
            lock.unlock(); 
        }
    }

    public void tryIncrement() {
        if (lock.tryLock()) { // Try to acquire the lock non-blocking
            try {
                count++;
                System.out.println(Thread.currentThread().getName() + " successfully acquired lock and incremented to: " + count);
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to acquire lock.");
        }
    }

    public static void main(String[] args) {
        ReentrantLockCode example = new ReentrantLockCode();

        // lock() and unlock()
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                example.increment();
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }, "Thread-A").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                example.increment();
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }, "Thread-B").start();

        // tryLock()
        new Thread(() -> {
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            example.tryIncrement();
        }, "Thread-C").start();

        new Thread(() -> {
            try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            example.tryIncrement();
        }, "Thread-D").start();
    }
}
