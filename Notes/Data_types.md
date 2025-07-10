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



