| Theory | Algorithms |
|:--:|:--:|
| Java & OOPs | Searching |
| DAA (basics) ||
|Linear Data Structures||
|Collections||
|||



Java	
- Java & OOPs	
- time and space Complexities	
- Linear Data Structures	
- Collections (list, Stack, queue, map, priority queue)
- Comparator


DSA
- Arrays
- Strings
- Binary Search
- Sorting (brief Overview)
- Two Pointers
- Sliding Window
- recursion
- BFS
- DFS
- Disjoint Set Union
- Backtracking
- Trees
- Graphs
- Greedy
- Dynamic Programming



Mandatory
--> Strong OOPs
--> Strong Data Structures and Analysis of algorithms
--> OS

Basic Knowledge
--> CN
--> Cloud Computing
--> python
--> testing (types of testing, tools used, importance, ...)
--> SDLC


### 9. JVM's Role in Reflection: How it Works Under the Hood

The JVM is the runtime environment that enables reflection by maintaining and exposing rich metadata about loaded classes and providing mechanisms for dynamic access.

#### 9.1. Class Loading and Metadata Generation

1.  **Parsing and Bytecode Generation**: When the `javac` compiler processes your `.java` source file, it generates `.class` files containing bytecode. This bytecode is not just instructions; it also contains a significant amount of **metadata** about the class, its fields, methods, constructors, and annotations. This metadata includes names, types, modifiers, and relationships (superclass, interfaces).

2.  **Class Loading**: When the JVM's Class Loader Subsystem loads a `.class` file, it reads this bytecode and its associated metadata. During the **Linking** phase (specifically **Preparation** and **Resolution**), the JVM builds internal data structures representing this metadata.

3.  **`java.lang.Class` Object Creation**: For every class loaded, the JVM creates a single instance of `java.lang.Class`. This `Class` object acts as a runtime descriptor for the loaded class. It holds pointers to the internal metadata structures. This `Class` object itself resides in the **Method Area** (or Metaspace in modern JVMs).

#### 9.2. Accessing Metadata via Reflection APIs

When you use reflection APIs (e.g., `clazz.getMethod("myMethod", String.class)`):

1.  The JVM looks up the `Class` object for `clazz`.
2.  It then navigates through the internal metadata structures associated with that `Class` object to find the requested method, field, or constructor. This involves searching through tables of method descriptors, field descriptors, etc., which are essentially arrays or hash tables of metadata entries.
3.  If found, the JVM creates a corresponding `java.lang.reflect.Method`, `Field`, or `Constructor` object. These objects are essentially "handles" or "pointers" to the actual method/field/constructor definitions within the JVM's internal structures.

#### 9.3. Dynamic Invocation (`Method.invoke()`, `Field.set()`, `Constructor.newInstance()`)

This is where the "manipulation" aspect gets more complex and involves lower-level JVM operations:

1.  **Argument Validation and Unboxing**: Before invocation, the JVM performs runtime checks on the arguments passed to `invoke()` or `set()`. It ensures that the types of the arguments match the expected parameter types of the method/constructor or the type of the field. If primitive types are involved, autoboxing/unboxing might occur.

2.  **Access Control Checks**:
    * The JVM first checks the Java language access rules (e.g., is this method `private`?).
    * If `setAccessible(false)` (default) is in effect, and access is denied by Java language rules, an `IllegalAccessException` is thrown.
    * If `setAccessible(true)` has been called, the JVM generally bypasses these checks. However, it might still perform some sanity checks or be restricted by a `SecurityManager` or the Java Module System (which can prevent reflective access to non-exported packages).

3.  **JNI (Java Native Interface) Involvement**:
    * The actual invocation of a method or setting of a field via reflection often involves calls to **Java Native Interface (JNI)** code.
    * JNI is a framework that allows Java code running in the JVM to call and be called by native applications and libraries written in other languages (like C/C++).
    * The core reflection methods (`invoke`, `set`, `newInstance`) are implemented in native code within the JVM. This native code directly interacts with the JVM's internal data structures and memory layout to perform the requested operation. It's like the JVM temporarily steps out of the "Java world" into the "native world" to perform the low-level manipulation.

4.  **Dynamic Method Dispatch (for `Method.invoke()`):**
    * For instance methods, the JVM performs dynamic method dispatch, similar to regular polymorphic calls. It looks at the actual type of the object (`obj` parameter in `invoke`) at runtime to find the correct method implementation to execute.
    * For static methods, the JVM directly calls the method associated with the `Class` object.

5.  **JIT Compiler Interaction**:
    * Initially, reflective calls might be handled by the interpreter or a less optimized JIT-compiled stub.
    * However, if a reflective call site becomes a "hot spot" (frequently executed), modern JVMs (like HotSpot) employ techniques like **inflation** or **bytecode generation** to optimize subsequent reflective calls.
    * **Inflation**: The JVM might "inflate" the reflective accessor (e.g., `MethodAccessor`) from a slower JNI-based implementation to a faster, dynamically generated bytecode version. This generated bytecode performs the access directly, without the overhead of JNI calls.
    * **Inlining**: In some cases, the JIT compiler might even be able to inline the target method if it can determine the exact method being called, further reducing overhead. However, this is harder for highly dynamic reflective calls.

#### 9.4. Why Reflection is Slower

The "under the hood" operations reveal why reflection is slower than direct calls:

* **Runtime Lookup**: Direct calls are resolved at compile time. Reflection requires searching through metadata at runtime.
* **Access Checks**: Even with `setAccessible(true)`, there's an initial overhead of checking and potentially bypassing security mechanisms.
* **Argument Packaging/Unpacking**: Arguments for `invoke()` are passed as `Object[]`, requiring boxing/unboxing for primitives and array creation, incurring overhead.
* **JNI Overhead**: The transition between Java code and native (JNI) code has a performance cost.
* **Dynamic Nature**: The JVM has less information at compile time to perform aggressive optimizations (like inlining) that it can do for static calls.
