> *Topics : Memory Allocation,...*\
> *Link to Codes --> [Codes](../codes/collections/)*



# Memory Allocation
- Java's memory management is handled by the Java Virtual Machine (JVM)
- it divides the memory it uses into different Runtime data areas

- Some Primary ones are,
  - Method Area (Class Area/ Metaspace)
  - Heap
  - JVM Stack (Thread Stacks)
  - PC Register (Program Counter Register)
  - Native Method Stacks

- **Method Area**
  - *Purpose* -->  stores class-level data for each loaded class. It's a shared resource among all threads.
  - *Contents*,
    - Class Structures -->  Runtime constant pool (including field and method data), static variables, method code (bytecodes).
    - Static Variables
    - Method Data --> The bytecode instructions for all methods (both static and instance methods) are stored here.

  - *Lifetime* -->  The data in the Method Area exists for the entire lifetime of the application, as long as the class remains loaded.
  - *Garbage Collection*: While it's logically part of the Heap, it can be garbage collected (e.g., for unloading classes that are no longer needed). 
    - In older JVMs it was called `PermGen`(Permanent Generation), now replaced by Metaspace.


- **Heap**
  - *Purpose* --> This is the runtime data area from which memory for all class instances (objects) and arrays is allocated. It's a shared resource among all threads.
  - *Contents*,
    - Objects --> all objects created using `new` keyword
    - Instance Variables --> stored as part of the object in the Heap.
    - Arrays --> all arrays

  - *Lifetime* -->  Heap remain there as long as they are "reachable" (i.e., referenced by active parts of the program).
    -  When an object is no longer referenced, it becomes eligible for Garbage Collection.
  - *Garbage Collection* --> The Heap is the primary area managed by the Garbage Collector. It's further divided into generations (Young Generation, Old Generation, etc.) to optimize GC performance.


- **JVM Stacks**
  - *Purpose* -->  Each thread in a Java application has its own private JVM Stack. It's used for method execution.
  - *Contents*,
    - Stack Frames (created when a method is invoked)
    - Local Variables (part of a stack frame)
    - Parameters (part of a stack frame)
    - Operand Stack (used for immediate computations)
    - Return address
  - *Lifetime* --> a stack frame exists only for the duration of a method call
  - if a thread requires more stack space than available, a StackOverFlow error occurs.

- **PC Register**
  - *Purpose* --> each JVM thread has its own PC register, holds the address of currently executing JVM instruction.
  - *Contents*,
    - if the method currently being executed is not a native method, PC Register holds the address of the JVM instruction currently being executed.
    - if it is a native method, the value of the PC Register is undefined.
  - *Lifetime* --> tied to the thread's execution.

- **Native Method Stacks**
  - *Purpose* --> similar to JVM Stacks but used for Native methods.
  - *Contents* --> stores information for native method calls
  - If a thread requires more native stack space, a StackOverflowError or OutOfMemoryError can be thrown.

  