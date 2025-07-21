*Topics --> Interfaces, . . .*

## Interface

- An interface in Java is a blueprint of a class.
- It contains abstract methods (before Java 8, all methods were implicitly `public abstract`), and from Java 8 onwards, it can also contain `default` and `static` methods.
- From Java 9, `private` methods are also allowed.
- Interfaces define a contract: any class that implements an interface must provide an implementation for all its `abstract` methods.


- *Characteristics*,
  - All methods declared in an interface are implicitly `public abstract` by default.
  - Prior to Java 8, interfaces were very restrictive, allowing only abstract methods and public static final fields. 
  - From Java 8, interfaces can have `default` methods (with implementation) and `static` methods (with implementation).
  - From Java 9, interfaces can have `private` methods (for internal use by `default` or `static` methods).
  - All fields (variables) in an interface are implicitly `public static final` by default. They must be initialized at the time of declaration.
  - Interfaces cannot have constructors.

- *Introduced in Java 8*
  - ***`default` Methods***
    - `default` methods allow you to add new methods to an interface without breaking existing classes that implement that interface.
    - they provide default implementation which can be used directly or can be overridden
    - implicitly public
    
  - ***`Static` Methods***
    - `static` methods in interfaces are utility methods that belong to the interface itself, not to any implementing class instance.
    - implicitly public
    - cannot be overridden by implementing classes.
    - They are called directly using the interface name (e.g., `InterfaceName.staticMethod()`).

- ***`private` methods (Introduced in Java 9)***
  - `private` methods can be used within an interface to share common code between `default` and `static` methods.
  - They cannot be abstract.
  - not accessible from outside the interface.



### Types of Interfaces

**Normal (or Regular) Interfaces**
-  They contain one or more abstract methods (and optionally default, static, private methods from Java 8/9+).


**Functional Interfaces (Single Abstract Method - SAM Interfaces)**
- A functional interface is an interface that contains exactly one abstract method. (introduced in Java 8)
- They can have any number of `default` methods, `static` methods, and `private` methods. The key is that they must have only one abstract method.
- They are primarily used to enable lambda expressions and method references, which provide a concise way to represent instances of functional interfaces.
- The `@FunctionalInterface` annotation is optional but highly recommended. It helps the compiler enforce the "single abstract method" rule.


**Marker Interfaces (Tag Interfaces)**
- These are interfaces that contain no methods or fields at all. They are empty interfaces.
- Their sole purpose is to "mark" or "tag" a class, indicating that the class possesses a certain capability or property.
- The JVM or a framework can then check if a class implements a specific marker interface to apply special behavior.




#### Why Interfaces are Not Prone to the Diamond Problem?
