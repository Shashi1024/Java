package Codes.Concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionObject {
   private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition(); // Condition for consumer to wait
    private final Condition notFull = lock.newCondition();  // Condition for producer to wait

    private final Queue<Integer> buffer = new LinkedList<>();
    private final int CAPACITY = 5;

    public void produce(int item) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == CAPACITY) {
                System.out.println("Buffer is full. Producer " + Thread.currentThread().getName() + " waiting...");
                notFull.await(); 
            }
            buffer.add(item);
            System.out.println("Producer " + Thread.currentThread().getName() + " produced: " + item + ". Buffer size: " + buffer.size());
            notEmpty.signalAll(); 
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty. Consumer " + Thread.currentThread().getName() + " waiting...");
                notEmpty.await();
            }
            int item = buffer.remove();
            System.out.println("Consumer " + Thread.currentThread().getName() + " consumed: " + item + ". Buffer size: " + buffer.size());
            notFull.signalAll(); 
            return item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionObject example = new ConditionObject();

        // Producer thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    example.produce(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-1").start();

        // Consumer thread 1
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    example.consume();
                    Thread.sleep(150); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-1").start();

        // Consumer thread 2
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    example.consume();
                    Thread.sleep(100); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-2").start();
    }
}
