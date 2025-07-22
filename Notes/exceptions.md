*Topics --> Exception Handling, . . .*

## Exception Handling

- **What are Exceptions?**
  - An exception is an event that disrupts the normal flow of a program's instructions.
  - When an error occurs within a method, it creates an object called an "exception object" and "throws" it.
  - That method may choose to handle the exception itself, or it can pass it on. At some point, an exception is caught and processed.


- **Exception vs Error**

  - `Exception`: Represents conditions that a reasonable application *might want to catch*. These are typically recoverable problems that can be handled by the program (e.g., file not found, network connection lost, invalid user input).

  - `Error`: Represents serious problems that a reasonable application *should not try to catch*. These usually indicate unrecoverable conditions in the JVM itself or the environment (e.g., out of memory, stack overflow). Programs typically terminate when an `Error` occurs.


#### The Exception Hierarchy
- All exceptions and errors in Java are subclasses of the `java.lang.Throwable` class

    ```
    java.lang.Object
    └── java.lang.Throwable
            ├── java.lang.Error             (Unchecked)
            │     ├── OutOfMemoryError
            │     └── StackOverflowError
            │     └── ...
            └── java.lang.Exception         (Checked or Unchecked)
                ├── java.lang.RuntimeException (Unchecked)
                │     ├── NullPointerException
                │     ├── ArrayIndexOutOfBoundsException
                │     ├── ArithmeticException
                │     ├── ClassCastException
                │     └── IllegalArgumentException
                │     └── ...
                └── Other Exceptions (Checked)
                        ├── IOException
                        ├── SQLException
                        ├── FileNotFoundException
                        ├── InterruptedException
                        └── ...
    ```

- **Checked Exceptions**
  - Exceptions that are checked by the compiler at compile time.
  - *if a method might throw a Checked Exception, it must either*,
    - Handle it using a `try-catch` block.
    - Declare it using the `throws` keyword in its method signature.
  - these are typically recoverable conditions, (ex. `IOException`, `SQLException`, `ClassNotFoundException`, `FileNotFoundException`)

- **Unchecked Exceptions (Runtime Exceptions)**
  - Exceptions that are not checked by the compiler at compile time. They are subclasses of `java.lang.RuntimeException`
  - not mandatory to handle these (ex. `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`, `IllegalArgumentException`, `ClassCastException`).
  - indicate programming logic errors or bugs (we can catch them, but better to fix the bug).

- **Errors**
  - Serious, unrecoverable problems that indicate issues with the JVM or its environment.
  - should not generally try to catch `Error`s, they are almost never recoverable.
  - ex. `OutOfMemoryError`, `StackOverflowError`, `VirtualMachineError`



#### Exception Handling Mechanisms

> `try`, `catch`, `finally`, `throw`, `throws`

***`try-catch` Block***
  - used to handle exceptions that might occur within a specific block of code.
    - `try` block: Contains the code that might throw an exception.
    - `catch` block: Contains the code that handles the exception if it is thrown in the try block. It takes an exception type as a parameter.
  - *Syntax*
    ```
    try {
        // Code that might throw an exception
    } catch (ExceptionType1 e1) {
        // Handle ExceptionType1
    } catch (ExceptionType2 e2) {
        // Handle ExceptionType2
    }
    // ... more catch blocks
    ```
  
  - we can have multiple `catch` blocks for each `try` block. *( More specific exception types must be caught before more general ones)*
  
  - **Multi-Catch (Java 7+)**
    - Allows a single `catch` block to handle multiple exception types
    - *Syntax* --> `catch (ExceptionType1 | ExceptionType2 | ... e)`
    - The exception variable `e` is implicitly final and its type is the least common supertype of all caught exceptions.

***`finally` Block***
  -  an optional block that is always executed, regardless of whether an exception occurred in the `try` block or not.

  - usually the order of execution is that first the try block, then catch(if an error is thrown), then finally.
    - If an exception *occurs and is not caught (or re-thrown)*: `try` block (until exception occurs) -> `finally` block -> exception propagates up the stack.

  - **When `finally` might NOT execute**
    - If the JVM exits (e.g., `System.exit(0)` is called within `try` or `catch`).
    - If a fatal error occurs (e.g., `OutOfMemoryError` or `StackOverflowError`) that prevents the JVM from continuing execution.
    - If the thread executing the `try` or `catch` block is killed.

  - `finally` and `return` --> If a `return` statement is present in the `try` or `catch` block, the `finally` block will still execute before the method actually returns. If the finally block itself contains a `return` statement, it will override any return from the try or catch block. (overriding here means, the value specified in the `finally`s return statement will be the one returned by the method).


***`try-with-resources` (Java 7 and later)***
- its a syntactic sugar for automatically closing resources that implement the `java.lang.AutoCloseable` interface (eliminates the need for explicit `finally` blocks for resource management)
- *Syntax*
    ```
    try (ResourceType resource1 = new ResourceType(...);
        ResourceType resource2 = new ResourceType(...)) {
        // Code that uses the resources
    } catch (ExceptionType e) {
        // Handle exceptions
    }
    // Resources are automatically closed when the try block exits,
    // whether normally or due to an exception.
    ```

- Resources declared int the `try` paranthesis are initialized and then the code in `try` block is executed

- *Regardless of how the `try` block exits (normally, by `return`, or by throwing an exception), the `close()` method of each resource is automatically called. Resources are closed in the reverse order of their declaration.*

- If an exception occurs during resource initialization or in the `try` block,(the first and the original exception) and (now JVM will try to close the resources due to the original exception) another exception occurs during closing, the original exception is preserved (and shown in the console), and the closing exception is suppressed (can be retrieved via `Throwable.getSuppressed()`).



***`throw` keyword***
- used to explicitly throw an exception from a method.
- *Syntax*: `throw new ExceptionType("message");`
- when `throw` is executed, the normal flow of execution stops and JVM tries to find the relevant `catch` block


***`throws` keyword***
- used in a method signature to declare that a method might throw one or more checked exceptions.
- It essentially delegates the responsibility of handling that exception to the calling method. (it informs the callers of a method about the checked exceptions they need to handle)
- *Syntax*
    ```
    returnType methodName(parameters) throws ExceptionType1, ExceptionType2 {
        // Method body that might throw ExceptionType1 or ExceptionType2
    }
    ```

- **Rule** --> If a method calls another method that declares a checked exception with throws, the calling method must either:,
- Handle the exception using `try-catch`.
- Declare the exception itself using `throws`.


***Custom (User-defined) Exceptions***
  - 


