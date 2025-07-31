package Codes.Concurrency;

public class VirtualThread {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Main Thread Started");

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            long taskId = Thread.currentThread().threadId();
            System.out.println("Task " + taskId + " (Thread: " + threadName + ") started.");
            try {
                // When a virtual thread encounters a blocking operation, the JVM can "unmount"
                // it from its carrier thread, allowing the carrier thread to execute another
                // virtual thread.
                Thread.sleep(100); // Simulate 100ms of blocking I/O
                System.out.println("Task " + taskId + " (Thread: " + threadName + ") resumed after blocking.");
            } catch (InterruptedException e) {
                System.err.println("Task " + taskId + " (Thread: " + threadName + ") was interrupted.");
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
            System.out.println("Task " + taskId + " (Thread: " + threadName + ") finished.");
        };

        System.out.println("\n--- Creating a single Virtual Thread ---");
        Thread virtualThread1 = Thread.ofVirtual().name("MyVirtualThread-1").start(task);
        System.out.println("Virtual Thread 1 started. Is Virtual: " + virtualThread1.isVirtual());
        virtualThread1.join();
        System.out.println("Virtual Thread 1 completed.");

        System.out.println("\nMain Thread finished.");
    }
}
