*Topics: JVM, JRE, JDK ...*


## JVM
-  It's an abstract machine that provides a runtime environment in which Java bytecode can be executed.
- The JVM is responsible for loading, verifying, executing Java bytecode, and managing memory.
- It makes Java a "write once, run anywhere" language by abstracting the underlying operating system and hardware.

### JVM Architecture Overview
- can be braodly divided into subsystems,
  - *Class Loader Subsystem*
  - *Runtime Data Areas*
  - *Execution Engine*
  - *Java Native Interface*
  - *Native Method Libraries*

- ***Class Loader Subsystem***, *(The Gatekeeper of Classes)*
  - responsible for loading `.class` files from the file system, network, or other sources into the JVM's memory.
  - It performs 3 main functions : `Loading`, `Linking`, `Initialization`
  
  - ***Phases of Class Loading***
    - **Loading**
      - finds `.class` for a given class name
      - it reads the binary data from `.class` file
      - It creates a `java.lang.Class` object in the Method Area for that class. This Class object contains all the metadata about the class (fields, methods, constructors, interfaces, superclass, etc.).
      - For each loaded class, the JVM stores its fully qualified name, its superclass's fully qualified name, and whether it's a class, interface, or enum.

    - **Linking** (has 3 sub-steps)
      - *Verification* -->  The bytecode verifier checks the loaded bytecode to ensure it adheres to the JVM specification and does not pose any security threats (e.g., ensuring type safety, correct operand stack manipulation, proper access to private members). 
        - If verification fails, a `VerifyError` is thrown.
    
      - *Preparation* --> The JVM allocates memory for static variables (class variables) in the Method Area and initializes them to their default values.
        - It does not execute any code or assign explicit initial values defined in the source code at this stage.

      - *Resolution* --> This is the process of replacing symbolic references (e.g., references to other classes, methods, or fields by their names) with direct references (memory addresses) from the Runtime Constant Pool.
        -  This step is often performed lazily, meaning it happens only when a symbolic reference is actually used for the first time.

    - **Initialization**
        - It's when the static initializers and static blocks of the class are executed.
        - Static variables are assigned their actual values as defined in the source code.
        - Initialization occurs only once per class.
        - The JVM ensures that a class is initialized only after its direct superclass has been initialized.


  - ***Class Loader Delegation Heirarchy**
  - Java employs a delegation model for class loading, which ensures security and prevents malicious code from replacing core Java classes. 
  - There are three built-in class loaders:
    1. **Bootstrap Class Loader (Primordial Class Loader)**
        - Loads core Java API classes (e.g., `java.lang.*`, `java.util.*`) from the `rt.jar` (runtime library) and other core library JARs in the `<JAVA_HOME>/jre/lib` directory.
        - It's implemented in native code (C/C++) and doesn't have a parent.
        - *Parent* : `null`

    2. **Extension Class Loader**
        - Loads classes from the extension directories, typically `<JAVA_HOME>/jre/lib/ext` or any directory specified by the `java.ext.dirs` system property.
        - These are extensions to the standard Java platform.
        - *Parent* : Bootstrap Class Loader
    
    3. **Application Class Loader (System Class Loader)**
        - Loads application-specific classes from the classpath (specified by the `CLASSPATH` environment variable, `-classpath` or `-cp` command-line options).
        - This is the class loader that typically loads your application's classes.
        - *Parent* : Extension Class Loader.

    - **Delegation Principle** --> When a Class Loader is asked to load a class, it first delegates the request to its parent. Only if the parent cannot find or load the class does the current Class Loader attempt to load it itself. This ensures that core Java classes are always loaded by the Bootstrap Class Loader, preventing them from being overridden.


- ***Execution Engine*** (Brings Bytecode to Life)
  - responsible for executing the bytecode that has been loaded and linked by the Class Loader.
  - it consists of,
    1. **Interpreter**
      - reads and executes bytecode instructions one by one
      - It's relatively slow because it interprets each instruction every time it's encountered. (Good for code that is executed only few times)

    2. **JIT (Just-In-Time) Compiler** (modern JVMs like HotSpot uses this)
      - It identifies "hot spots" (frequently executed code paths, like loops or frequently called methods)
      - It compiles these bytecode sequences into highly optimized native machine code during runtime.
      - Once compiled, the native code is stored in the code cache and can be executed directly by the CPU, bypassing the interpreter for subsequent calls. This significantly speeds up execution.
      - The JIT compiler also performs various optimizations (e.g., inlining, dead code elimination).

    3. **Garbage Collector (GC)**
      - Automatic Memory Management on the Heap.



### Garbage Collection (GC): Automatic Memory Management

- The Garbage Collector is a daemon thread that automatically manages memory on the Heap.
- frees up memory occupied by objects that are no longer "reachable" or "live" (i.e., no longer referenced by any active part of the program).
- Avoids Memory Leaks, Dangling Pointers (references to memory that has already been freed), provides simplicity

- ***How GC Identifies Garbage? : Reachability***
  - The GC determines if an object is "live" or "dead" based on reachability.
  - An object is considered reachable if it can be accessed directly or indirectly from a set of **GC Roots**.

  - **GC Roots** include,
    - Local variables and parameters in the currently executing methods on the Stack.
    - Static variables (class variables) in the Method Area.
    - References from JNI (Java Native Interface) code.
    - Threads that are currently running.

- If an object is not reachable from any GC Root, it is considered garbage and eligible for collection.












+-------------------------------------------------------------------+
|                           JVM Architecture                        |
+-------------------------------------------------------------------+
|                                                                   |
| +-------------------------+                                       |
| | Class Loader Subsystem  |                                       |
| |                         |                                       |
| | - Loading               |                                       |
| | - Linking               |                                       |
| |   - Verification        |                                       |
| |   - Preparation         |                                       |
| |   - Resolution          |                                       |
| | - Initialization        |                                       |
| +-------------------------+                                       |
|              |                                                    |
|              V                                                    |
| +---------------------------------------------------------------+ |
| |                    Runtime Data Areas                         | |
| |                                                               | |
| | +-----------------------+  +--------------------------------+ | |
| | |    Method Area        |  |          Heap                  | | |
| | | (Class Data, Statics) |  | (Objects, Instance Variables,  | | |
| | +-----------------------+  |  Arrays)                       | | |
| |                            +--------------------------------+ | |
| |                                                               | |
| | +-----------------------+  +--------------------------------+ | |
| | |    JVM Stacks         |  |          PC Registers          | | |
| | | (Local Vars, Frames)  |  | (Current Instruction Address)  | | |
| | +-----------------------+  +--------------------------------+ | |
| |                                                               | |
| | +-----------------------+                                       |
| | | Native Method Stacks  |                                       |
| | +-----------------------+                                       |
| +---------------------------------------------------------------+ |
|              |                                                    |
|              V                                                    |
| +---------------------------------------------------------------+ |
| |                    Execution Engine                           | |
| |                                                               | |
| | +---------------+  +-------------------+  +-----------------+ | |
| | | Interpreter   |  | JIT Compiler      |  | Garbage         | | |
| | | (Bytecode     |  | (HotSpot          |  | Collector       | | |
| | |  Execution)   |  |  Optimization)    |  | (Memory         | | |
| | +---------------+  +-------------------+  |  Management)    | | |
| +---------------------------------------------------------------+ |
|                                                                   |
| +-------------------------+  +--------------------------------+ | |
| | Java Native Interface   |  | Native Method Libraries        | | |
| | (JNI)                   |  | (C/C++ Code, etc.)             | | |
| +-------------------------+  +--------------------------------+ | |
+-------------------------------------------------------------------+