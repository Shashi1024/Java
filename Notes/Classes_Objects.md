*Topics --> Classes and Objects, Instance Initializers, Constructors, Types of Classes(static, local, final, sealed, inner)...*

- *Class* --> is a blueprint, defines the structure (data/fields) and behavior (methods) that objects of that type will posses. its a logical construct.
- *Object* --> is a concrete instance of a class.

---

- ***Class***
  - it is a user-defined data type, that acts as a template for creating objects.
  - *Components*,
    - Fields
    - Methods
    - Constructors
    - Blocks 
      - Instance Initialization blocks -->  Code blocks executed every time an object is created, before the constructor.
      - Static Initialization blocks --> Code blocks executed once when the class is loaded into the JVM.
    - Nested Classes/Interfaces


- ***Object***
  - An object is a runtime entity that has state (Values), behavior(Methods), and identity(Memory address). It's a concrete manifestation of a class.
  - *Object Creation Process (`new` keyword)*,
    - Objects are created using the new keyword, which allocates memory on the Heap for the new object.
    - *Steps* (In short, detailed explanation in JVM Notes *Link: [JVM Notes](JVM.md)*)
      - Declaration --> declares a refernce variable on the Stack (only if a variable is being declared to store the object reference)
      - Instantiation --> (Memory Allocation) this includes space for all instance variables (initialized to their default values) and **Object Overhead**
      - Contructor Invocation --> after memory allocation, suitable constructor is invoked.
      - Reference Assignment --> The memory address of the newly created object on the Heap is returned and this address to assigned to the reference variable.




***Order of Execution when an object is Created***
- first of all all the static initializers and variables are initialized when the class is loaded into the memory (they are executed only once when the class is loaded into memory, no matter how many objects we create).

- then the instance variables are initialized and instance initialization blocks are executed in the order they appear.

- Next the Constructors are invoked.


## Initializers and Variables

### Instance Initializer Blocks
*these blocks of code are executed when an object is created, (executed prior to the execution of the constructors)*

### static Initializer Blocks
*these blocks of code are executed when a class is loaded into the memory, (executed only once irrespective of how many objects are created)*

### Numeric Literals
*You can include underscores (`_`) in numeric literals to improve readability (e.g., `int number = 1_000_000;`).*



---



**Constructors**
- usually used to initialize the state(instance variables) of the new object
  - Name is same as class name
  - No return type (not even `void`)
  - can only be invoked implicitly (with `new` keyword)

- Types,
  - Default Constructor
  - No-Argument User-defined Constructor
  - Parameterized Constructor

- Constructors can be overloaded, with each having unique parameter list (different number, type, order of parameters)

**Constructor Chaining (`this()` & `super()`)**
- `this()`
  - used to call another constructor within the same class
  - it must be the first statement in the constructor

- `super()`
  - used to call the constructor of a super class
  - it must be the first statement of the constructor
  - if not explicitly called, java implicitly calls the no parameter `super()` constructor.
  - if the superclass does not have a no-parameter constructor, `super(args)` must be invoked explicitly.(else compile-time error will occur)

- we cannot use `this()` and `super()` within a same constructor.

  ***Order of Execution***
  - lets say we have a Parent class and a child class each having a constructor, a static initializer, an instance initializer.
  
  - the Order of execution will be 

    - `Parent Static Initializer`
    - `Child Static Initializer`
    - `Parent Instance Initializer`
    - `Patent Constructor`
    - `Child Instance Initializer`
    - `Child Constructor`
    
  - This means first parent and then the child classes are loaded into memory, then the parent is Initialized and then the child is Initialized.


- `this` keyword
  - it is a reference variable that refers to the current object.
  - to refer current object instance members --> `this.memberName` / `this.memberName()`
  - to invoke current class constructors --> `this(args)`
  - to return current class instance --> `return this;`

- `static` keyword
  - it makes a member belong to a class itself
  - *static variables* --> stored in method area, shared by all objects of the class.
  - *static methods* --> they dont have this reference.




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


### Different Types of Classes

#### Abstract Classes
-  An abstract class is a class that cannot be instantiated directly. It's designed to be a base class (superclass) that other classes can extend. It can contain both abstract methods (methods without an implementation) and concrete methods (methods with an implementation).

- *Characteristics*,
  - Declared using the `abstract` keyword.
  - Can have both concrete methods and abstract methods (declared with `abstract` keyword, no body, ending with a semicolon).
  - Can have constructors (though you can't instantiate the abstract class directly, its constructor is called by the subclass's constructor via `super()`).
  - A class extending an abstract class must implement all its abstract methods, or it must also be declared `abstract`.
  - They can have instance variables and static variables.

-  If a class has at least one `abstract` method, the class itself must be declared `abstract`.
- If a concrete class extends an abstract class, it must implement all the abstract methods of the parent abstract class. If it doesn't, it must also be declared abstract.
- Abstract classes can have final methods (which cannot be overridden) and static methods.
- They can have public, protected, default, and private access modifiers for their members.


#### Final Classes
- a `final` class cannot be subclassed (inherited from).
- *Characteristics*,
  - declared using the `final` keyword
  - all methods in a `final` class are implicitly `final` (cannot be overridden).
  - `java.lang.String` is a `final` class.



#### Immutable Classes
- An immutable class is a class whose instances cannot be modified after they are created. Once an object of an immutable class is instantiated, its state remains constant throughout its lifetime.
- *Characteristics (How to make a class immutable)*,
  - declare the class as `final`
  - declare all fields as `private` and `final`
  - No setter Methods
  - Initialize all fields via constructor
  - Perform deep copy for mutable object fields --> If the class holds references to mutable objects (e.g., Date, ArrayList), you must perform a "deep copy" in the constructor and in getter methods. This means creating new instances of those mutable objects instead of just copying references, to prevent external modification of the internal state.


#### Sealed Classes
- allows to explicitly declare which other classes or interfaces are permitted to extend or implement them. 
- this provides more control over the inheritence hierarchy than final (which prevents all inheritence) or abstract (which allows any class to extend).

- *Characteristics*,
  - declared using the `sealed` keyword, followed by the `permits` and a comma-separated list of permitted subclasses/implementations.
  - Permitted subclasses must be in the same module or package.
  - Permitted subclasses must be declared `final`, `sealed` or `non-sealed`



#### Inner Classes
- also called the nested classes, are classes defined within another class.(increases Encapsulation)

- when an inner class is compiled, java generated a separated `.class` file for it. 
- the naming convention for inner class `.class` files is typically `OuterClassName$InnerClassName.class`

- **Implicit Reference to Outer Class instance**
  - for non-static inner classes(member inner classes, local classes, anonymous classes), Java implicitly adds a **Synthetic field** to the inner class. 
  - This field holds reference to the instance of the outer class that created the inner class
  -This hidden reference is what allows a non-static inner class to directly access the outer class's instance members(even private ones) without explicit qualification.
  - The compiler handles the passing of this outer class reference automatically.


- **Types of Inner Classes**
  - Nested Inner Class(Non-static Inner Class/ Member Inner Class)
  - Static Nested Class
  - Local Class
  - Anonymous Class


- ***Nested Inner Class(Non-Static Inner Class/Member Inner Class)***
  - Inner class, not declated `static`
  - *Characteristics*,
    - can access all members, including `private` members, of outer class directly.
    - Requires an instance of outer class to be created before an instance of the inner class can be created.
    - Cannot declare `static` members itself, unless they are `final` constants
    - Can be declared with `public`, `private`, `protected`, or `default` access modifiers.
  
  - *Instantiation*
    ```
    OuterClass outer = new OuterClass();
    OuterClass.InnerClass inner = outer.new InnerClass();
    ```



- ***Static Nested Class***
  - a class defined within another class and declared with the `static` keyword.
  - *Characteristics*,
    - Behaves like a top-level class, but is nested for packaging convenience.
    - **Cannot access non-static(instance) members of outer class directly**. it can only access static members of the outer class.
    - Does not require an instance of the outer class to be created.
    - can declare `static` members itself
    - can be declared with `public`, `private`, `protected`, or `default` access modifiers

  - *Instantiation*
    ```
    OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
    ```


- ***Local Class***
  - a class defined inside a method, constructor or block
  - *Characteristics*
    - Has local scope, its only visible and usable within the block where its defined.
    - cannot be declared with access modifiers or `static`
    - can access members of the enclosing outer class (including private ones)
    - can access final or effectively final local variables and parameters of the enclosing block. (an **effectively final** variable is one whose value is never changed after it is initialized)

- ***Anonymous Class***
  - a class that has no name and is defined and instantiated in a single expression.
  - typically used to implement an interface or extend a class.
  - *Characteristics*
    - cannot have a constructor (because it has no name)
    - can access members of the enclosing outer class
    - can access `final` and effectively final local variables and parameters of the enclosing block
    - always implicitly extends a class or implements an interface
    - cannot be `static`

## Encapsulation
-  It refers to the bundling of data (fields) and the methods that operate on that data within a single unit (the class).
- provides, data hiding (private members) and controlled access (through methods like getters and setters).



## Access Modifiers
- private --> within class
- default --> package-private
- protected --> within same package and subclass (even of different package)
- public --> accessible from anywhere



