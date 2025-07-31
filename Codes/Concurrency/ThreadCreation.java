package Codes.Concurrency;

class RunnableTask implements Runnable{
    private String name;

    public RunnableTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Runnable Thread running "+name);
        try{
            Thread.sleep(50);
        }catch(Exception e){
            System.out.println("Runnable Interrupted "+name);
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Runnable Thread has finished "+name);
    }
}

class ThreadClass extends Thread{
    private String name;

    public ThreadClass(String name) {
        super(name); // sets the Thread name
        this.name = name;
    }

    @Override
    public void run(){
        System.out.println("Thread class is running "+name);
        try{
            Thread.sleep(50);
        }catch(Exception e){
            System.out.println("Thread Class Interrupted "+name);
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Thread Class has finished "+name);
    }

}

public class ThreadCreation {
    public static void main(String[] args){
        System.out.println("Main Thread Started");

        // Create and start a thread using the Runnable interface
        RunnableTask runnableTask1 = new RunnableTask("Task-1");
        Thread thread1 = new Thread(runnableTask1, "RunnableThread-1");
        thread1.start(); // Invokes the run() method of MyRunnableTask

        // Create and start another thread using the Runnable interface
        RunnableTask runnableTask2 = new RunnableTask("Task-2");
        Thread thread2 = new Thread(runnableTask2); // No explicit name, JVM will assign one (e.g., Thread-0)
        thread2.start();

        // Create and start a thread by extending the Thread class
        ThreadClass threadClass1 = new ThreadClass("MyCustomThread-1");
        threadClass1.start(); // Invokes the run() method of MyThreadClass

        // Create and start another thread by extending the Thread class
        ThreadClass threadClass2 = new ThreadClass("MyCustomThread-2");
        threadClass2.start();

        System.out.println("Main thread continuing its work.");

        // wait for threads to complete 
        try {
            thread1.join();
            thread2.join();
            threadClass1.join();
            threadClass2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting for child threads.");
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread finished.");
    }
}
