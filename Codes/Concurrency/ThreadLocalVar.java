package Codes.Concurrency;

public class ThreadLocalVar {
    private static ThreadLocal<String> tld = new ThreadLocal<>();

    public static void main(String[] args){
        System.out.println("Main Thread Started");

        Thread threadA = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Initial value of threadSpecificData: " + tld.get()); // null

            tld.set("Data for " + name); // Set value specific to Thread A
            System.out.println(name + ": Stored: " + tld.get());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(name + ": Retrieved again: " + tld.get());

            tld.remove(); // Clean up the thread-local value
            System.out.println(name + ": After remove(): " + tld.get()); // null
        }, "Thread-A");

        Thread threadB = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Initial value of threadSpecificData: " + tld.get());

            tld.set("Data for " + name); 
            System.out.println(name + ": Stored: " + tld.get());

            try {
                Thread.sleep(50); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(name + ": Retrieved again: " + tld.get());
        }, "Thread-B");

        threadA.start();
        threadB.start();

        try {
            threadA.join(); 
            threadB.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted while waiting for child threads.");
        }

        System.out.println("Main thread finished.");
    }
}
