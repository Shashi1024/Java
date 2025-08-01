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
- separates the lock acquisition and release into distinct methods
- *Methods*
  - `void lock()` --> Acquires the lock. 
    - If the lock is not available, the current thread is disabled for thread scheduling.
    - This is a blocking call.
  - `void unlock()` --> Releases the lock. 
    - This method should always be called in a `finally` block to ensure the lock is released even if an exception occurs.
  - `boolean tryLock()` --> Acquires the lock only if it is free at the time of invocation. 
    - Returns `true` if the lock was acquired, `false` otherwise. 
    - This is a non-blocking call.
  - `boolean tryLock(long timeout, TimeUnit unit)` --> Acquires the lock if it is free within the given waiting time and the current thread has not been interrupted.
  - `void lockInterruptibly()` --> Acquires the lock unless the current thread is interrupted. If the thread is interrupted while waiting for the lock, an `InterruptedException` is thrown.


- **`ReentrantLock` (Reentrant Mutual Exclusion Lock)**
  - a concrete implementation of `Lock` interface
  - "Reentrant" means that a thread that already holds the lock can acquire it again without blocking itself.



#### `ReadWriteLock` Interface
- provides a pair of associated locks, one for read-only operations and one for write operations. 
- *Concept*
  - **Read Lock** --> Multiple threads can acquire the read lock concurrently, as long as no thread holds the write lock.
  - **Write Lock** --> Only one thread can acquire the write lock at a time. When the write lock is held, no other threads (readers or writers) can acquire either the read or write lock.


- **`ReentrantReadWriteLock`
  - concrete implementation of the `ReadWriteLock` interface. 
  - its reentrant (a thread holding a write lock can acquire read locks or re-acquire the write lock.)
  - *Methods*
    - `Lock readLock()` --> Returns the lock used for reading.
    - `Lock writeLock()` --> Returns the lock used for writing.
  - Each of these returned Lock objects (`ReentrantReadWriteLock.ReadLock` and `ReentrantReadWriteLock.WriteLock`) implements the `Lock` interface, so they have `lock()`, `unlock()`, `tryLock()`, etc., methods.


---

### `Condition` Objects (Condition Interface)
> *Link to Codes --> [Codes](../Codes/Concurrency/Conditions.java)*

- used in conjunction with `Lock` implementations to provide more flexible and powerful `wait()`/`notify()` mechanisms
- this is a replacement for `Object`'s intrinsic `wait()`, `notify()`, and `notifyAll()` methods

- A `Condition` object is always created from a `Lock` instance using the `newCondition()` method. 
- This means that a thread must hold the associated `Lock` before calling any `Condition` methods (e.g., `await()`, `signal()`).

- *Methods*
  - `void await()` --> Causes the current thread to wait until another thread invokes `signal()` or `signalAll()` for this `Condition`. Similar to `Object.wait()`, it releases the associated lock and re-acquires it before returning.
  - `void awaitUninterruptibly()` --> Similar to `await()`, but ignores `InterruptedException`.
  - `boolean await(long time, TimeUnit unit)` --> Causes the current thread to wait until either the signal is received or the specified waiting time elapses.
  - `void signal()` --> Wakes up one waiting thread. Similar to `Object.notify()`.
  - `void signalAll()` --> Wakes up all waiting threads. Similar to `Object.notifyAll()`.

- *Advantages over `Object.wait()`/`notify()`*
  - **multiple condition queues per lock**
    - With `Object.wait()`/`notify()`, all threads waiting on a single object's monitor are in the same "wait set." When `notifyAll()` is called, all of them wake up, even if only a subset of them are interested in the condition that just changed (this is called "notification storm" or "spurious wakeups" in a broader sense).
    - `Condition` objects allow you to have separate "wait sets" (condition queues) for different conditions. A thread can wait on a specific `Condition` and only be woken up when `signal()` or `signalAll()` is called on that specific `Condition` object. 

  - **Interruptible Waiting** --> `await()` methods are interruptible, allowing threads to be woken up by an `InterruptedException`.




### Atomic Variables
- `java.util.concurrent.atomic` package provides classes that support atomic operations on single variables.
- Atomic operations are single, indivisible operations that are guaranteed to complete without interference from other threads. 

- **Working --> Compare-And-Swap (CAS)**
  - Atomic classes achieve their thread safety primarily through a low-level CPU instruction called Compare-And-Swap (CAS).
  - *The CAS operation takes three operands*
    1. `V (Memory Location)` --> The memory address of the variable to be updated.
    2. `A (Expected Value)` --> The value that the memory location is expected to have.
    3. `B (New Value)` --> The new value to set the memory location to.
  
  - it reads the current value at memory location V. If this current value is equal to the expected value A (old copy of the value read by this thread from V), then it writes the new value B to V. 
  - Otherwise (if the current value is not A), it does nothing. In either case, it returns the value that was actually read from V before the attempt to write.


-  A thread attempts to make a change, assuming no other thread has interfered. If interference is detected (the value has changed), the operation fails, and the thread can retry.



#### Basic Atomic Classes --> `AtomicInteger`, `AtomicLong`, `AtomicBoolean`
- These classes provide atomic operations for primitive types.

- `AtomicInteger`, `AtomicLong`, `AtomicBoolean` --> Provides atomic operations on `int`, `long`, `boolean` values respectively

- *Methods*
  - `get()` --> Atomically returns the current value.
  - `set(newValue)` --> Atomically sets the value to newValue.
  - `compareAndSet(expectedValue, updateValue)` --> Atomically sets the value to `updateValue` if the current value is `expectedValue`. Returns `true` if successful, `false` otherwise. This is the core CAS operation.
  - `incrementAndGet()` --> Atomically increments the current value by one and returns the updated value.
  - `getAndIncrement()` --> Atomically increments the current value by one and returns the old value.

  - also has methods for decrementing, adding and setting values.


#### `AtomicReference` --> Atomic Operations on Object References
- allows us to atomically set, update, retrieve an object reference
- can be used to change a shared object pointer in a thread-safe manner.
- *Methods* --> similar to `AtomicInteger`, it provides `get()`, `set()`, and `compareAndSet(expectedRef, newRef)`.


#### Addressing the ABA Problem: `AtomicStampedReference` and `AtomicMarkableReference`
- It occurs when a memory location is read as value A, then changed to B, and then changed back to A. A subsequent CAS operation might incorrectly succeed because it only checks if the current value is A, without knowing that the value has undergone intermediate changes.

- *Scenario,*
  - Thread 1 reads value `A`.
  - Thread 2 changes value from `A` to `B`, then back to `A`.
  - Thread 1 attempts a CAS operation from `A` to `C`. The CAS succeeds because the value is currently `A`, even though it was modified by Thread 2. This can be problematic if the intermediate change to `B` invalidated some invariant that Thread 1 was relying on.


- **`AtomicStampedReference`**
  - To atomically update an object reference and detect if the reference has changed any number of times between reads.
  - solves the ABA problem by associating a stamp (an integer version number) with the reference.
  - it requires both the expected reference and the expected stamp to match. If either doesn't match, the operation fails.

  - *Methods*
    - `V getReference()` --> Returns the current value of the reference.
    - `int getStamp()` --> Returns the current value of the stamp.
    - `boolean compareAndSet(V expectedReference, V newReference, int expectedStamp, int newStamp)` --> Atomically sets the value of the reference to `newReference` and the stamp to `newStamp` if the current reference is `expectedReference` and the current stamp is `expectedStamp`.


- **`AtomicMarkableReference`**
  - It associates a single **boolean mark** with the reference. (a bit simpler than stamp) (detects if the reference has changed(marked) atleast once)
  - It's useful when you only need to know if the reference has been changed at all, rather than tracking a version number.

  - *Methods*
    - `V getReference()` --> Returns the current value of the reference.
    - `boolean isMarked()` --> Returns the current value of the mark.
    - `boolean compareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark)` --> Atomically sets the value of the reference to `newReference` and the mark to `newMark` if the current reference is `expectedReference` and the current mark is `expectedMark`.



#### High-Performance Adders/Accumulators --> `LongAdder`, `LongAccumulator`, `DoubleAdder`, `DoubleAccumulator`
- Instead of a single shared variable, these classes maintain a dynamically sized array of internal variables (often called "cells"). 
- When multiple threads attempt to update the value concurrently, they are directed to update different cells in the array. This effectively "stripes" the updates across multiple memory locations, reducing contention on any single cache line. 
- When the actual sum or value is needed (`sum()`, `get()`), all the individual cell values are summed up.



---

### Daemon Threads





---

### Fork/Join Pool