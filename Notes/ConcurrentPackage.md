> *Topics --> Concurrent, . . .*\
> *Link to Codes --> [Codes](../Codes/Concurrency/)*


## `java.util.concurrent`
- The `java.util.concurrent` package provides tools for creating concurrent applications.
- 


### `synchronized` Keyword
> *Refer Keywords: Link --> [Notes](Keywords.md)*\
> *Link to Code --> [Codes](../codes/Concurrency/SynchronizedKeyword.java)*

---

### Executors Framework
> *Link to Codes --> [Codes](../Codes/Concurrency/ExecutorFramework.java)*

- The Java Executors Framework (part of `java.util.concurrent`) provides a higher-level API for managing threads, decoupling task submission from task execution. 
- It primarily uses thread pools to reuse threads, reducing overhead.


#### Thread Pool 
- A thread pool is a collection of pre-initialized, idle threads that are ready to perform tasks.
- tasks are submitted to the thread pool, which then assigns them to an available thread from its pool. When a thread finishes its task, it doesn't die; instead, it returns to the pool, becoming available for the next task.


#### `Executor` Interface
- Its sole purpose is to provide a standard way to submit `Runnable` tasks for execution. It abstracts away the details of how the task is run (e.g., in a new thread, in an existing pooled thread, or on the current thread).

- it has a single abstract method
  - `void execute(Runnable command);` --> Executes the given command at some time in the future. 


#### `ExecutorService` Interface
- The `ExecutorService` interface extends `Executor` and provides a more comprehensive set of features for managing the lifecycle of tasks and the executor itself.

- **Lifecycle Management**
  - `void shutdown()` --> Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
  - `List<Runnable> shutdownNow()` --> Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution. This is a more aggressive shutdown.
  - `boolean isShutdown()` --> Returns true if this executor has been shut down.
  - `boolean isTerminated()` --> Returns true if all tasks have completed following shut down. Note that isTerminated() is never true unless shutdown() or shutdownNow() was first called.
  - `boolean awaitTermination(long timeout, TimeUnit unit)` --> Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.


- **Task Submission Methods**
> *Link to Codes --> [Codes](../Codes/Concurrency/CallableFuture.java)*

  1. `Future<?> submit(Runnable task)`
     - Submits a `Runnable` task for execution.
     - Returns a `Future<?> object`. Since Runnable tasks don't return a value, the `Future`'s `get()` method will return `null` upon completion.

  2. `Future<T> submit(Callable<T> task)`
     - Submits a `Callable` task for execution. `Callable` is similar to `Runnable` but can return a result and throw checked exceptions.
     - Returns a `Future<T>` object, where `T` is the type of the result returned by the `Callable`. The `Future`'s `get()` method will return the actual result.

  3. `List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)`
     - Executes the given tasks.
     - Returns a `List` of `Future`s, one for each task, in the same order as the input collection.
     - `get()` on each `Future` will block until the corresponding task completes.
     - All tasks are submitted at once, and the method returns only when all tasks have completed (or the optional timeout expires).

  4. `T invokeAny(Collection<? extends Callable<T>> tasks)`
     - Executes the given tasks.
     - Returns the result of one of the tasks that has completed successfully (i.e., without throwing an exception).
     - If one task completes, the others are cancelled.
     - This is useful when you only need the result from the fastest task



#### `Executors` Utility Class
> *Link to Codes --> [Codes](../Codes/Concurrency/ThreadPools)*

- provides convenient static factory methods for creating various types of `ExecutorService` instances, pre-configured with common thread pool characteristics.

1. `newFixedThreadPool(int nThreads)`
   - Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue. If all threads are busy, new tasks wait in the queue.

2. `newCachedThreadPool()`
   - Creates a thread pool that creates new threads as needed, but reuses previously constructed threads when they are available. 
   - If a thread is idle for 60 seconds, it is terminated and removed from the cache. The pool size can grow without bound.

3. `newSingleThreadExecutor()`
   - Creates an `ExecutorService` that uses a single worker thread. Tasks are executed sequentially in the order they are submitted. It guarantees that tasks will never run concurrently.

4. `newScheduledThreadPool(int corePoolSize)`
   - Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically. It returns a `ScheduledExecutorService`.
   - Leading into `ScheduledExecutorService` --> This factory method creates an instance of `ScheduledExecutorService`, which extends `ExecutorService` and adds methods like `schedule()`, `scheduleAtFixedRate()`, and `scheduleWithFixedDelay()`.


---

### `Callable` Interface
> *Link to Codes --> [Codes](../Codes/Concurrency/CallableFuture.java)*

- The `Callable` interface is similar to Runnable, but with two key differences,
  - *Returns a Result* --> Its `call()` method returns a value of type `T`.
  - *Can Throw Checked Exceptions* --> Its `call()` method can declare checked exceptions, which can then be caught by the code retrieving the result.
  - *Signature*
    ```
    @FunctionalInterface
    public interface Callable<V> {
        V call() throws Exception;
    }
    ```

---


### `Future` Interface
> *Link to Codes --> [Codes](../Codes/Concurrency/CallableFuture.java)*

- The `Future` interface represents the result of an asynchronous computation. 
- It provides methods to check if the computation is complete, to wait for its completion, and to retrieve the result of the computation.
- *Key Methods*
  - `boolean cancel(boolean mayInterruptIfRunning)` --> Attempts to cancel execution of this task.
    - `mayInterruptIfRunning = true` --> The thread executing this task should be interrupted.
    - `mayInterruptIfRunning = false` --> The thread executing this task should not be interrupted 
  - `boolean isCancelled()` --> Returns true if this task was cancelled before it completed normally.
  - `boolean isDone()` --> Returns true if this task completed. Completion may be due to normal termination, an exception, or cancellation.
  - `V get()` --> Waits if necessary for the computation to complete, and then retrieves its result. This method is blocking. If the task threw an exception, `get()` will throw an `ExecutionException`. If the task was cancelled, it will throw a `CancellationException`.
  - `V get(long timeout, TimeUnit unit)` --> Waits if necessary for at most the given timeout for the computation to complete, and then retrieves its result. Throws `TimeoutException` if the wait timed out.

  -  When you submit a `Callable` (or `Runnable`) to an `ExecutorService` using the `submit()` method, it returns a `Future` object. This `Future` object is your handle to the asynchronous task's result or status.


---

### Concurrent Collections
- traditional collections are not thread-safe, access/modifying them without external synchronization, may lead to race conditions, data corruption, . . .
- Concurrent Collections are thread-safe and provide better performance than using external synchronization on traditional collections.
- uses internal mechanisms such as fine-grained locking, optimistic locking, or Copy-On-Write strategies.


- **`ConcurrentHashMap`**
  - *Working*
    - Unlike `Hashtable` or `Collections.synchronizedMap(new HashMap())` which use a single global lock, `ConcurrentHashMap` uses a technique called segment locking (or more accurately, striped locking in older versions, and fine-grained locking with CAS operations in Java 8+). This allows multiple threads to read and write concurrently to different parts of the map without blocking each other.
    - read operations typically do not require locking
    - write operations only lock specific "bin" or segment being modified.

  - *Features*
    - No `null` keys or values
    - weakly consistent iterators --> iterators reflect the state of the Map at the time the iterator was created. They do not throw `ConcurrentModificationException` if the map is modified after the iterator is created, but they might not reflect all modifications that occur during iteration.


- **`CopyOnWriteArrayList and CopyOnWriteArraySet`**
  - *Working*
    - read operations are performed on the current, immutable snapshot of the underlying array.
    - when modification operations occurs, new copy of the underlying array is created. modification is applied to this new copy, and then the reference to the array is atomically updated to point to the new copy. 

  - *Features*
    - high read concurrency
    - high memory overhead
    - Iterators reflect the state of the collection at the time of creation of the iterator, subsequent modifications are not reflected. does not throw `ConcurrentModificationException`



- **`ConcurrentLinkedQueue` and `ConcurrentLinkedDeque`: Non-Blocking Queues**
  - They achieve thread safety using **CAS (Compare-And-Swap)** operations instead of explicit locks.
  - *Working*
    - CAS operations are low-level atomic CPU instructions that allow a thread to attempt to update a memory location only if its current value matches an expected value. If the values don't match (meaning another thread modified it), the operation fails, and the thread can retry. This avoids blocking threads, leading to higher throughput under high contention.

  - *Features*
    - high concurrency
    - Unbounded
    - weakly consistent iterators (just like the above two) and do not throw `ConcurrentModificationException`


- **`ConcurrentSkipListMap` and `ConcurrentSkipListSet`: Concurrent Sorted Maps/Sets**
  - *Working*
    - They use a Skip List data structure, which is a probabilistic data structure that allows efficient searching, insertion, and deletion operations (logarithmic time complexity) while being highly amenable to concurrent modifications without requiring global locks. Like `ConcurrentLinkedQueue`, they rely heavily on CAS operations.

  - *Features*
    - Sorted Order
    - high concurrency
    - weakly consistent Iterators


---

### Blocking Queues
> *Link to Codes --> [Codes](../Codes/Concurrency/BlockingQ.java)*

- blocks threads when the queue is either empty (for consumers) or full (for producers).
- fundamental for implementing the Producer-Consumer pattern, where one or more threads produce items and add them to a queue, and one or more threads consume items from the same queue.


- **BlockingQueue Interface**
  - `BlockingQueue` interface extends `java.util.Queue` and adds methods that block when the queue is full or empty.
  - *Methods*
    - `void put(E e)` --> Inserts the specified element into this queue, waiting if necessary for space to become available. *Blocks if the queue is full.*
    - `E take()` --> Retrieves and removes the head of this queue, waiting if necessary until an element becomes available. Blocks if the queue is empty.
    - `boolean offer(E e)` --> Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions. Returns `true` on success, `false` if no space is currently available. (Non-blocking)
    - `boolean offer(E e, long timeout, TimeUnit unit)` --> Inserts the specified element into this queue, waiting up to the specified wait time if necessary for space to become available.
    - `E poll()` --> Retrieves and removes the head of this queue, or returns `null` if this queue is empty. (Non-blocking)
    - `E poll(long timeout, TimeUnit unit)` --> Retrieves and removes the head of this queue, waiting up to the specified wait time if necessary for an element to become available.
    - `int remainingCapacity()` --> Returns the number of additional elements that this queue can ideally (in the absence of memory or resource constraints) contain without blocking.


#### Implementations of `BlockingQueue`

- **`ArrayBlockingQueue` (Bounded, Array-Backed)**
  - *Characteristics*
    - Bounded
    - fixed-size array
    - FIFO

  - *Mechanism*
    - Uses a single internal lock (`ReentrantLock`) and two `Condition` objects (`notEmpty` and `notFull`) to manage blocking for producers and consumers. 
    - When a producer tries to `put()` into a full queue, it waits on `notFull`. When a consumer tries to `take()` from an empty queue, it waits on `notEmpty`.


- **`LinkedBlockingQueue` (Optionally Bounded, Linked-List Backed)**
  - *Characteristics*
    - optionally Bounded --> If no capacity is specified, it defaults to `Integer.MAX_VALUE` (meaning unbounded by default)
    - FIFO

- *Mechanism*
  - Uses two separate locks (one for insertions, one for removals) and two `Condition` objects.
  - This dual-lock approach can provide higher throughput than `ArrayBlockingQueue` under high contention, as producers and consumers can operate more independently.


- **`PriorityBlockingQueue` (Unbounded, Priority-Based)**
  - *Characteristics*
    - unbounded
    - orders elements according to their natural order, or by a `Comparator` provided at queue construction time.

  - *Mechanism*
    -  Uses a min-heap data structure and a single lock (`ReentrantLock`) with a single `Condition` object.


- **`SynchronousQueue` (Zero-Capacity, Direct Handoff)**
  - *Characteristics*
    - Zero Capacity
    - direct Handoff Mechanism
    -  It doesn't actually store any elements. Instead, each `put()` operation must wait for a corresponding `take()` operation by another thread, and vice-versa. 

  - *Mechanism*
    - Uses internal synchronization to pair producers and consumers.


- **`DelayQueue` (Elements Only Taken After a Delay)**
  - *Characteristics*
    - unbounded blocking queue of Delayed elements
    - An element can only be taken from the queue when its delay has expired.
    - Elements are ordered by their remaining delay.

  - *Mechanism*
    - Uses a `PriorityQueue` internally to manage elements based on their delay and a `ReentrantLock` with a `Condition` for blocking.

  
---

### Locks
> *Link to Codes --> [Codes](../Codes/Concurrency/Locks.java)*

- `java.util.concurrent.locks`
- offer features not available with intrinsic locks, such as fair locking, non-blocking lock acquisition, interruptible lock acquisition, and multiple condition queues per lock.

#### `Lock` Interface
- provides a more explicit and flexible locking mechanism than the `synchronized` keyword. 



---

### Conditions