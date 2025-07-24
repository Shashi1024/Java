> *Topics: Primitive, Non-primitive (reference), Wrapper Classes, Literals, Type Casting, Variables, Methods . . .*\
> *Link to Codes --> [Codes](../Codes/DataTypes&Variables/)*



# Data Types
- *Specifies the size and type of values that can be stored in a variable.*

## Primitive Data Types
- They store the actual value directly in memory.
- No methods are associated with primitive Data Types.
- If declared as **local variables or method parameters**: Stored directly on the Stack as part of the method's stack frame.
- If declared as **instance variables**: Stored on the Heap as part of the object they belong to.
- If declared as **static variables**: Stored in the Method Area.


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
- Objects are allocated memory on Heap, and the allocation for reference variable depends on whether it is instance, static or local same as the primitive types.
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



## Object vs. Reference

| Object                                                                 | Reference                                                               |
| ---------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| Sits on the **heap** and has no name.                                    | A named variable that holds the memory address of an object.            |
| Can only be accessed through a reference.                              | Used to access the fields and methods of an object.                     |
| Cannot be assigned to another object or passed directly to a method.   | Can be assigned to another reference, passed to, or returned from a method. |
| Is what gets garbage collected.                                        | Can exist on the heap or the stack; it is not what gets collected by the GC. |
| Varies in size depending on its fields.                                | All references are the same size, regardless of the object type they point to. |




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

  - **Immutability**: String literals (and String objects in general) are immutable. Once created, their value cannot be changed.

  - ***String Pool (String Interning)***: For efficiency, Java maintains a "String Pool" (or String Constant Pool) in the heap. When a string literal is encountered, the JVM first checks if an identical string already exists in the pool.

    - If it exists, the JVM reuses the reference to that existing string.

    - If it doesn't exist, a new String object is created in the pool, and its reference is returned.

    - This optimization saves memory, especially when the same string literal is used multiple times.



- `Boolean` Literals

  - represent logical truth values.

  - Values: true and false.


f. `Null` Literal

  - It indicates that the reference variable does not currently point to any object in memory.

  - Type: It can be assigned to any non-primitive (reference) variable. It cannot be assigned to primitive variables.




## Type Casting
- process of converting value of one data type into another data type
- can be done for both primitive and Non-primitive data types [provides compatibility and polymorphism]

- **Implicit Type Casting (Widening)**
  - Target type is larger than the source type. (no loss of data)
  - automatic and safe

- **Explicit Type Casting (Narrowing)**
  - target type is smaller than the source type
  - conversion may not always be safe (data loss or loss of precision)

### Type Casting with Reference Types
-  involves converting an object reference from one class type to another. This is deeply tied to inheritance and polymorphism.
- **Upcasting**
  - casting a subclass object to a superclass reference.
  - **Reason**: A subclass object is always an instance of its superclass. The superclass reference can point to any of its subclass objects.

  - **Effect**: The object itself doesn't change, but the reference now treats the object as its superclass type. This means you can only access the methods and fields defined in the superclass (or overridden by the subclass).

- **Downcasting**
  -  casting a superclass reference to a subclass type.
  - **Reason**: A superclass reference might or might not be pointing to an actual object of the target subclass.

  - **Potential Issue**: If the superclass reference is not actually pointing to an object of the target subclass (or one of its sub-subclasses), a java.lang.ClassCastException will be thrown at runtime.
  
  - ***`instanceof` Operator for safe Downcasting***
    - To prevent `ClassCastException` during downcasting, you should always use the instanceof operator to check the actual type of the object before performing the cast.


## Variables
-  a variable is a name given to a memory location.
- each variable has a data type.
- data type determines the size, layout of memory, range of values, set of operations that can be applied

### Variable Types

* **Local Variables**
    - allocated on Stack Memory. (each time a method is called a `Stack Frame` is created and these variables reside in that frame)
    * Declared inside a method.
    * Cannot have access modifiers (public, protected, private) or be declared `static`.
    * Only the `final` keyword is permitted.
    * **Must be initialized** before use; they don't have a default value and will cause a compiler error if used without initialization.

* **Instance Variables (Non-static)**
    - allocated on `heap` memory.
    * Associated with an object (non-static fields).
    * Do not require explicit initialization; they are given a default value (e.g., `0` for numbers, `false` for booleans, `null` for objects).

* **Class Variables (Static)**
    - created when class is loaded into memory by JVM
    * Associated with the class itself, declared using the `static` keyword.
    * Like instance variables, they receive a default value if not explicitly initialized.
    - Stored in a special area of the Heap memory known as the Method Area (or sometimes referred to as PermGen/Metaspace in older/newer JVMs, respectively).

### Variable Scope

* **Local variables**: In scope only from their declaration to the end of the method or block they are defined in.
* **Instance variables**: In scope as long as the object they belong to exists.
* **Class (static) variables**: In scope for the entire lifetime of the program.

#### `final` Keyword
- applied to variables to make them constant.
- for primitive variables the value remains constant
- for reference variables Once a `final` reference variable is initialized, it can only point to that specific object. You cannot reassign it to point to another object.



## Methods
- block of code that defines the behaviour of objects and classes
  - Encapsulation
  - Modularity
  - Reusability
  - Abstraction

- Method Declaration:
  - Access Modifier (optional)
  - static/non-static(instance method) (optional)
  - returnType
  - Method name
  - parameter List (optional)
  - Method body
  - Return value (optional)

- **Method Signature**
  - consists of *Method Name* and *Parameter List (number, type, order)*
  - return type, access modifier are not part of Signature.
  - Signature is used to uniquely identify a method in a class.


- **Types of Methods**
  - `Instance` Methods
    - belongs to an object
    - can access instance and static varaibles of the class, and call other instance and static methods.
    - it must be called on an object reference
    - object is created on heap, instance methods are associated with the object (actual code for the method resides in the Method area(part of the heap) and is shared among all instances).
    - when an instance method is invoked, a stack frame is created on the Stack.
  - `Static` Methods
    - belongs to class itself
    - can directly access static variables and call other static methods of the class.
    - cannot access instance variables or call instance methods without object reference.
    - it can be directly called using class name or object reference(not recommended).
    - Static Methods are loaded into the Method Area along with the class definition, when the JVM loads the class into Memory.
    - when invoked, a stack frame is created on the Stack.
  - `Constructors`
    - gets invoked when an instance of a class is created using the `new` keyword.
    - it has no return type (not even `void`)
  - `Abstract` Methods
    - Just Wait!



- Method Parameters & Arguments
  - **Parameters**: variables declared in the method signature.
  - **Arguments**: actual values passed to a method when it is invoked.

  - Java uses `Pass by Value` for arguments,
    - *Primitive types*: when passed a copy of the vlaue is made.
    - *Reference types*: when passed a copy of the reference is made. (this is a bit tricky, it is pass by value because the actual value of the reference is copied (doesnt matter even if that value is an address because we passed the reference variable not the object)).

- **Method Overloading**
  - allows a class to have multiple methods with same name but different method signatures.
    - different no. of parameters
    - different data types of parameters
    - different order of data types of parameters
  - we can use any modifier and return type of the overloaded method as long as the parameter list is different.
  
- **The `main` Method**
  - The `public static void main(String[] args)` method is the entry point for any standalone Java application.
  - `public`: for JVM to access it from anywhere
  - `static`: JVM can call it without creating the an object of the class
  - `void`: it doesnt return any value to the Operating System
  - `main`: the specific name recognized by the JVM
  - `String[] args`: array of string objects to receive the command line arguments



- When a Method is called a Stack frame is created and pushed in to the stack,which contains,
  - Local variables
  - parameters
  - return address
  - other operational data

- when the method completes its execution, the stack frame is popped out of the stack.


