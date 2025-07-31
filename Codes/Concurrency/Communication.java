package Codes.Concurrency;

class Message {
    private String msg;
    private boolean isEmpty = true; 

    public synchronized String take() {
        // if buffer is empty, consumer thread waits.
        while (isEmpty) {
            try {
                wait(); // Releases the lock and waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted while waiting.");
                return null;
            }
        }
        // if a message is available, set isEmpty to true (buffer now empty)
        // and notify producer that it can put a new message.
        isEmpty = true;
        notifyAll(); // Wakes up all waiting threads of Producer
        return msg;
    }

    public synchronized void put(String msg) {
        // if buffer not empty, the producer thread waits.
        while (!isEmpty) {
            try {
                wait(); // Releases the lock and waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted while waiting.");
                return;
            }
        }
        // if buffer is empty, put the message, set isEmpty to false,
        // and notify consumer that a message is available.
        this.msg = msg;
        isEmpty = false;
        notifyAll(); // Wakes up all waiting threads of Consumer
    }
}

class Producer implements Runnable {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        String[] messages = {"Hello", "World", "Java", "Concurrency", "DONE"};
        for (String msg : messages) {
            System.out.println(name + ": Producing: " + msg);
            message.put(msg);
            try {
                Thread.sleep((long) (Math.random() * 500)); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + ": Finished producing.");
    }
}

class Consumer implements Runnable {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        String msg;
        do {
            msg = message.take();
            System.out.println(name + ": Consuming: " + msg);
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } while (!msg.equals("DONE")); // Continue until "DONE" message is received
        System.out.println(name + ": Finished consuming.");
    }
}

public class Communication {
    public static void main(String[] args) throws Exception{
        Message sharedMessage = new Message();

        Thread producerThread = new Thread(new Producer(sharedMessage), "ProducerThread");
        Thread consumerThread = new Thread(new Consumer(sharedMessage), "ConsumerThread");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("Main thread finished.");
    }
}
