*Topics: Inheritance, . . .*

## Inheritance
- In Java, inheritance is a mechanism that allows one class to inherit the fields and methods of another class.
- It represents an "is-a" relationship
- ***Superclass***
- ***Subclass***

- A subclass inherits all `public` and `protected` members from its superclass. It also inherits `default` (package-private) members if the subclass is in the same package. `private` members of the superclass are inherited but are not directly accessible by the subclass, they can only be accessed indirectly via public/protected methods provided by the superclass.

- the `extends` keyword is used to establish an inheritance relationship between two classes.

### Types of Inheritance in Java
  - **Single Inheritance**
    - a class inherits from only one superclass
  - **Multilevel Inheritance**
    - A class inherits from another class, which in turn inherits from another class. This forms a chain of inheritance.
  - **Hierarchial Inheritance**
    - Multiple subclasses inherit from a single superclass


- **Java Does Not Support Multiple Inheritance (for Classes)**
  - Java does not support multiple inheritance for classes (i.e., a class cannot directly extend more than one class).
  - `Class C extends Class A, Class B` is not allowed
  - ***The Diamond Problem***
    - when a child class is inherits the properties from more than one parents and the methods for the parents are same (Method name and parameters are exactly the same) then child gets confused about which method will be called. This problem in Java is called the Diamond problem.
    - This usually happens when a child class inherits from two or more classes which in turn inherit from a common ancestor.

- **MUltiple Inheritance an be achieved using Interfaces**
  - Refer Interfaces : *Link --> [Notes](interface.md)*

- **Method Overriding in Inheritance (Runtime Polymorphism)**
  - Refer Polymorphism : *Link --> [Notes](Polymorphism.md)*


- **`super` keyword**
  - `super(args)` can be used to Invoke Superclass Constructor
  - `super.memberName` can be used to access or invoke superclass members
  - can be used for *constructor chaining*
    - If the superclass does not have a no-argument constructor, `super(args)` must be inoved explicitly.
  
  > for more info refer: *Link --> [Notes](Classes_Objects.md)*


### The Object Class: The Root of All Classes
- In Java, every class, directly or indirectly, inherits from the `java.lang.Object` class.
- If a class does not explicitly extend any other class, it implicitly extends `Object`.
- This means all classes in Java inherit methods like `equals()`, `hashCode()`, `toString()`, `getClass()`, `notify()`, `wait()`, etc., from the `Object` class.
- This forms a single, unified class hierarchy, which is fundamental to Java's type system and polymorphism.