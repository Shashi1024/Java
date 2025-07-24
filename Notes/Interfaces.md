> *Topics --> Interfaces, . . .*\
> *Link to Codes --> [Codes](../codes/)*



## Interface

- An interface in Java is a blueprint of a class.
- It contains abstract methods (before Java 8, all methods were implicitly `public abstract`), and from Java 8 onwards, it can also contain `default` and `static` methods.
- From Java 9, `private` methods are also allowed.
- Interfaces define a contract: any class that implements an interface must provide an implementation for all its `abstract` methods.
-  Interfaces achieve 100% abstraction (conceptually, before Java 8 default methods).


- *Characteristics*
- **Pre-Java 8**
  - All methods are implicitly `public abstract`. You don't need to write `public abstract`.
  - All fields are implicitly `public static final`. They are constants.
  - Cannot have concrete methods.
  - Cannot have Constructors
  - A class implements an `interface` using the `implements` keyword.
  - A class implementing an interface must provide implementations for all its abstract methods, or be declared `abstract` itself.
  - A class can `implement` multiple interfaces (achieving multiple inheritance of type).
  - An interface can `extend` multiple other interfaces.

- **Java 8**
  - `default` Methods --> These are methods with an implementation provided directly within the interface (using `default` keyword). these methods can be overridden by the subclasses.
  - `static` Methods --> These are methods that belong to the interface itself, not to any implementing object. They are called using the interface name.

- **Java 9+**
  - `private` Methods -->  These are helper methods that can be used by `default` or `static` methods within the interface itself. They cannot be called from outside the interface.

**Current Characteristics**
  - `interface` keyword to define an interface
  - can have abstract methods (implicitly `public abstract`)
  - can have concrete `default` methods
  - can have concrete `private` methods
  - can only have `public static final` fields
  - cannot have constructors
  - `implement` for classes and `extend` for interfaces





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

- *Interfaces define Contracts, not Implementations*
  - If a class `C` implements two interfaces, `InterfaceA` and `InterfaceB`, and both `InterfaceA` and `InterfaceB` declare a method void `doSomething()`;, there's no ambiguity. Class `C` must provide its own single implementation for `doSomething()`. The compiler forces `C` to resolve the ambiguity by defining the method itself.

- *Resolution with Default Methods*
  - If a class `implements` two interfaces with conflicting `default` methods (same signature), the class must override that method itself. The compiler will issue an `error` if the class doesn't provide its own implementation, forcing a clear resolution.
  - If a class inherits a `default` method from an interface and also inherits a concrete method (with the same signature) from a superclass, the class method always "wins." Class methods take precedence over interface default methods.
