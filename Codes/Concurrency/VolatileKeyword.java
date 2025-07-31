package Codes.Concurrency;

public class VolatileKeyword {
    private static volatile boolean running = true;
    // here if we remove the volatile keyword the while loop in reader will not terminate

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started.");

        Thread readerThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": Starting to read 'running' flag.");
            long counter = 0;
            while (running) { 
                counter++;
            }
            System.out.println(threadName + ": 'running' flag changed to false. Loop executed " + counter + " times.");
        }, "ReaderThread");

        
        Thread writerThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": Will set 'running' to false in 2 seconds.");
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                System.out.println(threadName + ": Interrupted during sleep.");
                Thread.currentThread().interrupt();
            }
            running = false; 
            System.out.println(threadName + ": 'running' flag set to false.");
        }, "WriterThread");

        
        readerThread.start();
        writerThread.start();

        
        readerThread.join();
        writerThread.join();

        System.out.println("Main thread finished.");
    }
}
