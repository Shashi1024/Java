package Codes.Concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// in this code output you might see that the writer will acquire the lock
// and some other thread acquires lock before the writer releases it, this because unlock() and print() are two separate statements
// control might go to other threads immediately after unlock() statement (due to which other thread's acquire print() is executed
// before the writer's unlock() print)

// just try changing the order of those statements and you will see the difference



public class ReentrantReadWrite {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    private String data = "Initial Data";
    
    public String readData(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" acquired read lock. Reading Data...");
            Thread.sleep(100);
            return data;
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return null;
        }finally{
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+" released read lock");
        }
    }

    public void writeData(String newData){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" acquired the write lock. Writing Data");
            Thread.sleep(200);
            this.data = newData;
            System.out.println(Thread.currentThread().getName()+" Wrote "+newData);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }finally{
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+" released write lock");
        }
    }

    public static void main(String[] args) {
        ReentrantReadWrite example = new ReentrantReadWrite();

        // Multiple readers can read concurrently
        new Thread(() -> System.out.println("Read 1: " + example.readData()), "Reader-1").start();
        new Thread(() -> System.out.println("Read 2: " + example.readData()), "Reader-2").start();
        new Thread(() -> System.out.println("Read 3: " + example.readData()), "Reader-3").start();

        // A writer will block all readers and other writers
        new Thread(() -> {
            try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            example.writeData("New Data from Writer-A");
        }, "Writer-A").start();

        // Another reader and writer trying to access while Writer-A holds the lock
        new Thread(() -> {
            try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            System.out.println("Read 4: " + example.readData());
        }, "Reader-4").start();

        new Thread(() -> {
            try { Thread.sleep(180); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            example.writeData("New Data from Writer-B");
        }, "Writer-B").start();
    }


}