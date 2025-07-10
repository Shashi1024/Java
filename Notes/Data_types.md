> *Topics: Primitive, Non-primitive (reference), Wrapper Classes*

# Data Types
- *Specifies the size and type of values that can be stored in a variable.*

## Primitive Data Types
- They store the actual value directly in memory.
- No methods are associated with primitive Data Types.
- Memory is allocated on Stack.


    |Type| Size |Default|Range|Remarks|
    |---|---|---|---|---|
    |`byte`|8 bits| `0` | -128 to 127 | Nothing |
    |`short`| 16 bits| `0` | -32,768 to 32,767 | Nothing|
    |`int`| 32 bits | `0` | 4.2 billion | Nothing|
    |`long`| 64 bits | `0L` | -- | append 'L' to the literal|
    |`float`| 32 bits | `0.0F` | -- | append 'F' at the end|
    |`double`| 64 bits | `0.0D` | -- | append 'D' at the end|
    |`char`| 16 bits | `\u0000` |0 to 65,535 | stores single unicode character
    |`boolean`| 1 bit or 1 byte (varies)| `false` | `true` `false`| Nothing

### JVM Interpretation of these types
- `Integer` types
  - stored using **two's compliment** representation.
- `Floating-Point` types
  - stored using **IEEE 754 Standard** (contains a sign bit, exponent, mantissa)
  - `float` use single-precision, `double` uses double-precision.
- `Character` type
  - Stored as an **unsigned 16-bit integer** representing a Unicode character. 
- `Boolean` type
  - Conceptually can be represented using a single bit, but actual implementation varies. (may be 1 bit or 1 byte).


## Non-Primitive (Reference) Data Types
- dont store the value directly
- they store a reference (a memory address) to the object's location in the heap memory. [a pointer to an object].
- Objects are allocated memory on Heap, and references on Stack.
- `default`: `null`
- `size` : the size of a reference variable is typically fixed (4 bytes or 8 bytes based on the JVM)
  - size of the object it refers to can vary.
- Non-primitive types are created using `new` keyword (except for String literals).
- They have methods associated with them.

- `class`
  - User-defined blueprints for creating objects.
- `Interface`
  - A blueprint of a class. It can have abstract methods and static and default methods.

  - Variables of an interface type can hold references to objects of classes that implement that interface.

- `Array`
  - An array is an object that holds a fixed number of values of a single data type.

  - Arrays can hold both primitive and non-primitive types.


### JVM Interpretation of these types
- **Reference Variables**
  -  the variable itself is stored on the stack (if it's a local variable) or in the heap (if it's an instance variable of an object).

- **Object Data**
  - allocated on **heap**.
  - An object on the heap consists of:

    - `Object Header`: Contains metadata about the object, such as its hash code, garbage collection information, and a pointer to its class definition in the Method Area.

    - `Instance Data`: The actual values of the object's instance variables.

    - `Padding`: Bytes added to ensure the object's size is a multiple of 8 bytes for memory alignment, optimizing access.



## Wrapper Classes
- Wrapper classes provide a way to use primitive Data Types as Objects.
- For each primitive type, there is a corresponding wrapper class in the java.lang package.
- for each primitive type, the name of wrapper class starts with Capital letter and has same name (except for char).
- "Why Wrapper Classes?"
  - Collection framework can only store objects, not primitive types (wrapper allow us to store primitive values in collections)
  - allows `null` values
  - provides utility methods
  - Generics

- **Autoboxing & Unboxing**
  - **Autoboxing**: Automatic conversion of a primitive type to its corresponding wrapper class object.

  - **Unboxing**: Automatic conversion of a wrapper class object to its corresponding primitive type.



## Literals

- a literal is a fixed value that is directly represented in the source code. It's a way of representing a constant value that can be assigned to a variable or used directly in an expression.

- `Integer` Literals

  - used to represent whole numbers. 

  - Decimal (Base 10)
  - Octal (Base 8)
  - Hexadecimal (Base 16)
  - Binary (Base 2)



  >- If the value exceeds the range of int, you must explicitly specify it as a long by appending L or l (e.g., `1234567890123L`).

  >- Underscores (_) can be used in numeric literals for readability (e.g., 1_000_000 for one million). They are ignored by the compiler.
  
.

- `Floating-Point` Literals

    - represent numbers with a fractional part.
    - By default, floating-point literals are treated as double.

    - To specify a float literal, append f or F (e.g., 3.14f).

    - To explicitly specify a double literal, append d or D (though it's optional as double is the default).

    - Scientific Notation: Can also be represented using scientific notation (e.g., 1.23e-5 for 1.23
    times10 
    −5
    ).

- `Character` Literals

    - represent a single character. They are enclosed in single quotes (').

    - Escape Sequences: Special characters can be represented using escape sequences, which start with a backslash (\).

            \n: Newline

            \t: Tab

            \r: Carriage return

            \\: Backslash

            \': Single quote

            \": Double quote

            \b: Backspace

            \f: Form feed



    - Unicode Representation: Can also be represented using their Unicode value, prefixed with \u.


- `String` Literals

  - represent a sequence of characters. They are enclosed in double quotes (").

  - Immutability: String literals (and String objects in general) are immutable. Once created, their value cannot be changed.

String Pool (String Interning): For efficiency, Java maintains a "String Pool" (or String Constant Pool) in the heap. When a string literal is encountered, the JVM first checks if an identical string already exists in the pool.

If it exists, the JVM reuses the reference to that existing string.

If it doesn't exist, a new String object is created in the pool, and its reference is returned.

This optimization saves memory, especially when the same string literal is used multiple times.

Escape Sequences: Like character literals, string literals can also use escape sequences.

Example:

String message = "Hello, World!";
String path = "C:\\Users\\Documents\\file.txt"; // Using double backslash for literal backslash
String multiLine = "Line 1\nLine 2";

Real-time Example: String userName = "Alice";, String query = "SELECT * FROM Users";, String welcomeMessage = "Welcome to our application!";

e. Boolean Literals

Boolean literals represent logical truth values.

Values: There are only two boolean literals: true and false.

Example:

boolean isActive = true;
boolean isFinished = false;

Real-time Example: if (isLoggedIn) { ... }, while (hasMoreData) { ... }

f. Null Literal

The null literal represents the absence of a value for a reference type.

Type: It can be assigned to any non-primitive (reference) variable. It cannot be assigned to primitive variables.

Meaning: It indicates that the reference variable does not currently point to any object in memory.

Example:

String name = null;
java.util.List<String> myList = null;
// int primitive = null; // Compile-time error

Real-time Example: Representing an optional field that hasn't been set yet (e.g., a middle name), or indicating that an object could not be found or created.

Conclusion

Literals are the fundamental building blocks for providing constant values directly within your Java source code. By understanding the different types of literals, their specific syntax, and how they are handled by the JVM (especially the String Pool), you gain a deeper appreciation for how data is represented and managed in your Java applications. This knowledge is crucial for writing clear, correct, and sometimes more optimized code.