Topics : ...

## Packages and Imports

* You cannot import two classes with the same name from different packages simultaneously.
    * To use two classes with the same name, you can import one and use the fully qualified name for the other (e.g., `packageName.ClassName`).
    * If you use wildcard imports (e.g., `java.util.*` and `java.sql.*`) that both contain a class with the same name (like `Date`), the compiler will throw an error.

---

## Initializers and Variables

### Instance Initializer Blocks
*these blocks of code are executed when an object is created, (executed prior to the execution of the constructors)*

### static Initializer Blocks
*these blocks of code are executed when a class is loaded into the memory, (executed only once irrespective of how many objects are created)*

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



---

## Object vs. Reference

| Object                                                                 | Reference                                                               |
| ---------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| Sits on the **heap** and has no name.                                    | A named variable that holds the memory address of an object.            |
| Can only be accessed through a reference.                              | Used to access the fields and methods of an object.                     |
| Cannot be assigned to another object or passed directly to a method.   | Can be assigned to another reference, passed to, or returned from a method. |
| Is what gets garbage collected.                                        | Can exist on the heap or the stack; it is not what gets collected by the GC. |
| Varies in size depending on its fields.                                | All references are the same size, regardless of the object type they point to. |