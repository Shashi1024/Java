> *Topics --> Concurrent, . . .*
>


## `java.util.concurrent`
- The `java.util.concurrent` package provides tools for creating concurrent applications.
- 


### `synchronized` Keyword
- *Refer Keywords: Link --> [Notes](Keywords.md)*

---

### Executors Framework
- The Java Executors Framework (part of `java.util.concurrent`) provides a higher-level API for managing threads, decoupling task submission from task execution. 
- It primarily uses thread pools to reuse threads, reducing overhead.


#### Thread Pool 
- A thread pool is a collection of pre-initialized, idle threads that are ready to perform tasks.
- tasks are submitted to the thread pool, which then assigns them to an available thread from its pool. When a thread finishes its task, it doesn't die; instead, it returns to the pool, becoming available for the next task.


#### `Executor` Interface
- Its sole purpose is to provide a standard way to submit `Runnable` tasks for execution. It abstracts away the details of how the task is run (e.g., in a new thread, in an existing pooled thread, or on the current thread).

- it has a single abstract method
  - `void execute(Runnable command);`

