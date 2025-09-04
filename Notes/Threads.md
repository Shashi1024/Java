> *Topics --> Threads, . . .*\
> *Link to Codes --> [Codes](../Codes/Concurrency/)*


## Threads
> *Link to Codes --> [Codes](../Codes/Concurrency/ThreadCreation.java)*

- thread is an independent path of execution within a program. 
- in java threads are of two types, User Threads (or Non-Daemon Threads) and Daemon Threads.
- we primarily interact with threads using the `java.lang.Thread` class or by implementing the `java.lang.Runnable` interface.

  ```
  // Example using Runnable
  class MyRunnable implements Runnable {
      public void run() {
          System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
      }
  }

  // Example using Thread class
  class MyThread extends Thread {
      public void run() {
          System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
      }
  }

  public class ThreadExample {
      public static void main(String[] args) {
          new Thread(new MyRunnable(), "RunnableThread").start();
          new MyThread().start(); // Name will be Thread-0, Thread-1 etc.
      }
  }
  ```



- **Interrupt flag**
  - The interrupt flag is a `boolean` flag associated with each thread, initially `false`. It serves as a cooperative signal that another thread has requested this thread to stop its current activity or terminate gracefully.

  - `Thread.currentThread().interrupt()` sets this interrupt flag of the current thread (the one executing this line of code) to `true`. It's commonly used within an `InterruptedException` `catch` block to re-assert the interruption signal, ensuring higher-level code or the thread's own loop can detect and respond to the original interruption request.




### OS Threads Vs. Java Threads
- modern JVMs typically implement Java threads as 1:1 mappings to native operating system (OS) threads.
- **OS Thread** --> fundamental unit of execution scheduled by the operating system kernel.
  - Each OS thread has its own program counter, stack, and registers, but shares the process's memory space (heap, code segment).

- **Java Thread** --> When you create a new `Thread()` in Java, the JVM makes a system call to the underlying OS to create a corresponding native OS thread. (lifecycle of java thread is largely managed by the OS)

- `Context Switching` --> When the OS switches between threads, it needs to save the state of the current thread and load the state of the next thread. (Costly operation)



### Thread States
- 6 states, defined by the `Thread.State` enum:

1. `NEW`
  - not yet started
  - `new Thread()` object, it is in the `NEW` state.
  - remains in this state until the `start()` method is invoked on it.


2. `RUNNABLE`
  - includes threads that are ready to run and waiting for CPU time, as well as threads that are currently executing in JVM.
  - thread enters the `RUNNABLE` state after its `start()` method has been invoked.

3. `BLOCKED`
  - thread that is blocked waiting for a monitor lock.
  - The thread will remain `BLOCKED` until the lock becomes available.

4. `WAITING`
  - thread that is waiting indefinitely for another thread to perform a particular action.
  - A thread enters the `WAITING` state by calling:
    - `Object.wait()` (without a timeout)
    - `Thread.join()` (without a timeout)
    - `LockSupport.park()`
  - A thread in `WAITING` state will only transition out of it when another thread explicitly calls `notify()` or `notifyAll()` (for `Object.wait()`) or the joined thread terminates (for `Thread.join()`), or `LockSupport.unpark()` is called.

5. `TIMED_WAITING`
  - thread that is waiting for another thread to perform an action for a specified waiting time.
  - thread enters the `TIMED_WAITING` state by calling:
    - `Thread.sleep(long millis)`
    - `Object.wait(long millis)`
    - `Thread.join(long millis)`
    - `LockSupport.parkNanos(long nanos)`
    - `LockSupport.parkUntil(long deadline)`
  - A thread in `TIMED_WAITING` state will transition out of it either when the specified time elapses, or when the required action occurs 

6. `TERMINATED`
  - thread that has exited
  - A thread enters the `TERMINATED` state when its `run()` method completes its execution (either normally or due to an uncaught exception).
  - once a thread is `TERMINATED`, it cannot be restarted.


#### Possible Transitions

- `NEW` 
  - to `start()` 
  - to `RUNNABLE`

- `RUNNABLE` 
  - to `synchronized` (lock unavailable) 
  - to `BLOCKED` 
  - to (lock available) 
  - to `RUNNABLE`

- `RUNNABLE` 
  - to `wait()` 
  - to `WAITING` 
  - to `notify()`/`notifyAll()` 
  - to `BLOCKED` (if lock needed) 
  - to `RUNNABLE`

- `RUNNABLE` 
  - to `sleep()` / `wait(timeout)` / `join(timeout)` 
  - to `TIMED_WAITING` 
  - to (timeout or notification) 
  - to `RUNNABLE`

- `RUNNABLE` 
  - to (`run()` completes) 
  - to `TERMINATED`



---

### Kernel and System Cores
- The kernel is the core of the operating system, responsible for managing system resources, including CPU time.(acts as the scheduler for OS threads)
- *Responsibilities of Kernel*
  - Scheduling
  - Context Switching


- **System Cores** --> Each core can execute one thread at a time.


### Virtual Threads (Project Loom)
> *Link to Codes --> [Codes](../Codes/Concurrency/VirtualThread.java)*

- introduced in Java 21, also known as Fibers or User-Mode Threads (provides higher concurrency)
- *Characteristics*
  - **Lightweight** --> are managed by the JVM, not directly by the OS. Their stack size is much smaller, and their creation/destruction overhead is minimal.
  - **Many-to-few Mapping** --> Instead of a 1:1 mapping, many virtual threads are mapped onto a smaller pool of underlying OS threads, called **carrier threads**.
    - When a virtual thread performs a blocking operation (e.g., network I/O, `Thread.sleep()`), the JVM can "unmount" it from its carrier thread. 
    - The carrier thread then becomes free to run another virtual thread. When the blocking operation completes, the virtual thread is "remounted" onto an available carrier thread to resume execution.

  ```
  // Example of Virtual Thread creation (Java 21+)
  public class VirtualThreadExample {
      public static void main(String[] args) throws InterruptedException {
          long startTime = System.currentTimeMillis();
          for (int i = 0; i < 1_000_000; i++) {
              Thread.ofVirtual().start(() -> {
                  try {
                      Thread.sleep(1); // Simulate some work
                  } catch (InterruptedException e) {
                      Thread.currentThread().interrupt();
                  }
              });
          }
          long endTime = System.currentTimeMillis();
          System.out.println("Created 1 million virtual threads in " + (endTime - startTime) + " ms.");
          // In a real app, you'd join them or use a structured concurrency construct
      }
  }
  ```








### Memory Allocation
- Heap --> all the objects are allocated here, all threads have access to heap, most shared variables reside here
- Stack --> each thread has its own private stack. it contains,
  - Local Variables
  - Method Call Frames
  - Partial results


- **`ThreadLocal`** variables --> provides a way to store data that is unique to each thread. 
> *Link to Codes --> [Codes](../Codes/Concurrency/ThreadLocalVar.java)*

  - Even though the variable name is shared, each thread gets its own independent copy of the value. 
  - *Methods*
    - `T get()`: Returns the value in the current thread's copy of this thread-local variable.
    - `void set(T value)`: Sets the current thread's copy of this thread-local variable to the specified value.
    - `void remove()`: Removes the current thread's value for this thread-local variable. This is crucial for preventing memory leaks, especially in thread pools.
    
    ```
    public class ThreadLocalExample {
        private static ThreadLocal<String> threadName = new ThreadLocal<>();

        public static void main(String[] args) {
            Runnable task = () -> {
                threadName.set(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " - Stored: " + threadName.get());
            };

            new Thread(task, "Thread-A").start();
            new Thread(task, "Thread-B").start();
        }
    }
    ```

### Thread Communication
> *Link to Codes --> [Codes](../Codes/Concurrency/Communication.java)*

- `wait()`, `notify()`, `notifyAll()` --> These methods (defined in `Object`) are used for inter-thread communication based on an object's monitor (lock).
  - `wait()` --> A thread releases the lock on an object and goes into a waiting state until another thread calls `notify()` or `notifyAll()` on the same object.
  - `notify()` --> Wakes up a single waiting thread.
  - `notifyAll()` --> Wakes up all waiting threads.

- `join()` --> A thread can call `join()` on another thread to wait for that thread to complete its execution.


### Native Operations(JNI)
- Java Native Interface (JNI) allows Java code to call and be called by native applications and libraries written in other languages (like C/C++).

- When a Java thread executes native code via JNI,
  - The JVM transitions the thread's state to "native."
  - The native code executes directly on the OS thread.
  - **Impact on Virtual Threads**: If a virtual thread calls native code that performs a blocking operation, the JVM cannot unmount the virtual thread from its carrier thread while the native code is executing. This means the carrier thread remains blocked until the native call returns.


### `Volatile` keyword
> *Link to Codes --> [Codes](../Codes/Concurrency/VolatileKeyword.java)*

- it ensures visibility of changes to a shared variable across threads.
- *Visibility* --> When a variable is declared `volatile`, any write to that variable by one thread is immediately visible to other threads. This prevents threads from caching stale values in their local CPU caches or registers.
- *Happens-Before Guarantee*: A write to a `volatile` variable "happens-before" any subsequent read of that same `volatile` variable. This means that not only the `volatile` variable itself, but also all variables visible to the writing thread before the `volatile` write, become visible to any thread that subsequently reads the `volatile` variable.
- it guarantees only visibility, not Atomicity. and is insufficient to prevent race conditions.



### Shared Variables
- instance/static variables that can be accessed and modified by multiple threads.



---


### Daemon Threads
- a low priority thread that runs in the background to perform tasks for other threads.
- its primary purpose is to serve user threads.
- JVM does not wait for daemon threads to complete their execution before exiting. (if the only threads remeaining are daemon threads, the JVM will terminate)

- In User Thread is a high-priority thread that performs foreground tasks and the JVM will wait for all user threads to complete their execution before terminating.

- *Characteristics*,
  - Low priority
  - Inheritance --> a new thread inherits its daemon status from the thread creates it. (if a daemon thread creates a new thread, it will be a daemon thread by default) (if a user thread creates a thread, it will be a user thread by default)

- a thread's daemon status can be set using `setDaemon(boolean on)` method of the `Thread` class. this method must be called before the thread is started, else it will throw `IllegalThreadStateException`

- Ex. JVM itself, logging, spell checkers, ...

- Memory management in daemon threads is very important, as these threads can be terminated abruptly, we have to make sure it does not hold any critical resources that require explicit cleanup or they must be implemented with some robust shutdown hooks or `try-finally`


---

### Fork/Join Pool
- Fork/Join framework introduced in Java 7

- **Fork/Join Framework**
  - a framework for parallelizing tasks that can be broken down into smaller subtasks(forking) and whose results can be combined(joinin). (divide and conquer algorithms)

- `ForkJoinPool` --> an implementation of `ExecutorService` interface that manages worker threads and provides support for `ForkJoinTask`s. it is oprimized for work-stealing, and helps keep all processor cores busy.

- *Principles*
  - **Fork** --> a task splits itself into smaller subtasks and submits them to the `ForkJoinPool`.
  - **Join** --> a task waits for the results of its subtasks and then combines them to produce its own result.
  - **Work-Stealing** --> `ForkJoinPool` employs work-stealing algorithm. when a worker thread finishes its own tasks, it looks for tasks in the queues of other busy worker threads and steals them maximising CPU Utilization.

- *Components*
  - `ForkJoinPool` --> thread pool where `ForkJoinTask`s are executed. typically creates as many worker threads as there are available processor cores.
  - `ForkJoinTask` --> an abstract class representing a task that can be executed in a `ForkJoinPool`, it has two main concrete subclasses,
    - `RecursiveAction` --> a `ForkJoinTask` that does not return a result (like a `Runnable`)
    - `RecursiveTask<V>` --> a `ForkJoinTask` that returns a result of type `V` (like a `Callable`)


- *Working*
  - **Task Submission** --> submitting a task using methods like `execute()`, `submit()`, `invoke()`
  - **Worker Threads** --> `ForkJoinPool` maintains a set of worker threads, usually one per CPU core, each thread has its own double ended queue of tasks
  - **Forking** --> when a `ForkJoinPool` decides to split itself, if `fork()`s new subtasks. these subtasks are typically pushed onto the head of the current worker thread's dequeue
  - **Joining** --> when a task needs the result of a subtask, it calls `join()` method on that subtask.
    - if the subtask is already completed, its result is returned immediately
    - if the subtask is not yet completed, the current thread might try to execute other tasks from its own deque (or even steal tasks from other threads) while waiting, to avoid blocking
  - **Work-Stealing** --> if a worker thread's own deque is empty, it attempts to steal tasks from the tail of other busy worker threads' deques.
  - **Result Combination** --> once all subtasks are completed and joined, their results are combined to produce the final result of the original task

- *Memory*
  - each `ForkJoinTask` object (and its state) consumes heap memory, each worker thread has its own stack, deques and both consume memory.
  - work-stealing mechanism itself also creates some overhead for managing deques and synchronization
  - no intermediate collections are used, these are entirely executed in a pipeline fashion just like the streams.

- parallel streams (like `collection.parallelStream()`) internally use the `ForkJoinPool.commonPool()`.