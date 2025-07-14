*Topics: JVM, JRE, JDK ...*


## JVM
-  It's an abstract machine that provides a runtime environment in which Java bytecode can be executed.
- The JVM is responsible for loading, verifying, executing Java bytecode, and managing memory.
- It makes Java a "write once, run anywhere" language by abstracting the underlying operating system and hardware.




















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