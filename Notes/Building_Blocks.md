Topics : ...

## Packages and Imports

* You cannot import two classes with the same name from different packages simultaneously.
    * To use two classes with the same name, you can import one and use the fully qualified name for the other (e.g., `packageName.ClassName`).
    * If you use wildcard imports (e.g., `java.util.*` and `java.sql.*`) that both contain a class with the same name (like `Date`), the compiler will throw an error.

---

## Initializers and Variables

### Instance Initializer Blocks
*Also known as static initializers, these blocks of code are executed when an object is created.*

### Numeric Literals
*You can include underscores (`_`) in numeric literals to improve readability (e.g., `int number = 1_000_000;`).*



---

## Java File Structure

* Multiple classes can be defined in the same `.java` file, but **only one** can be declared `public`. A file can also contain multiple non-public classes.

| Element             | Required? | Ordering                  |
| ------------------- | :-------: | ------------------------- |
| **Package** |    No     | First line of the file    |
| **Imports** |    No     | Immediately after package |
| **Class Declaration** |    Yes    | Immediately after imports |
| **Field Declaration** |    No     | Anywhere inside a class   |
| **Method Declaration**|    No     | Anywhere inside a class   |

---

## Destroying Objects

All Java objects are stored in a memory area called the **heap**. The heap is a large pool of memory allocated to your application.

### Garbage Collection (GC)
Garbage Collection is the automatic process of freeing up memory on the heap by deleting objects that are no longer "reachable."

* **What does "no longer reachable" mean?**
    * The object has no active references pointing to it.
    * All references to the object have gone out of scope (e.g., the method they were declared in has finished executing).
* `static` objects are generally not eligible for garbage collection because they are tied to the class and exist for the program's entire lifecycle.

* **`System.gc()`**
    * This method **suggests** to the Java Virtual Machine (JVM) that now might be a good time to run the garbage collector.
    * It does **not guarantee** that the GC will run. The JVM is free to ignore this request.

* **`finalize()` method**
    * A method from the `Object` class that is called by the garbage collector just before an object is destroyed.
    * It was intended for releasing non-Java resources (like file handles or database connections).
    * Its execution is **unpredictable** (it might not run at all) and it has been **deprecated** in modern Java.

---

## Object vs. Reference

| Object                                                                 | Reference                                                               |
| ---------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| Sits on the **heap** and has no name.                                    | A named variable that holds the memory address of an object.            |
| Can only be accessed through a reference.                              | Used to access the fields and methods of an object.                     |
| Cannot be assigned to another object or passed directly to a method.   | Can be assigned to another reference, passed to, or returned from a method. |
| Is what gets garbage collected.                                        | Can exist on the heap or the stack; it is not what gets collected by the GC. |
| Varies in size depending on its fields.                                | All references are the same size, regardless of the object type they point to. |