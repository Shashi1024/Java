*Topics --> Abstraction, . . .*

## Abstraction

- It is the process of hiding the complex implementation details and showing only the essential features or functionalities to the user.
- Java primarily achieves abstraction through two mechanisms:
  - Abstract Classes
  - Interfaces


### Abstract Classes
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



### Interfaces

- An interface in Java is a blueprint of a class.
- It contains abstract methods (before Java 8, all methods were implicitly `public abstract`), and from Java 8 onwards, it can also contain `default` and `static` methods.
- From Java 9, `private` methods are also allowed.
- Interfaces define a contract: any class that implements an interface must provide an implementation for all its `abstract` methods.

- *Characteristics*,
  - All methods declared in an interface are implicitly `public abstract` by default.
  - From Java 8, interfaces can have `default` methods (with implementation) and `static` methods (with implementation).
  - From Java 9, interfaces can have `private` methods (for internal use by `default` or `static` methods).
  - All fields (variables) in an interface are implicitly `public static final` by default. They must be initialized at the time of declaration.
  - Interfaces cannot have constructors.