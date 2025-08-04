> *Topics: Polymorphism (Compile-time, Runtime), . . .*\
> *Link to Codes --> [Codes](../Codes/)*



## Polymorphism
- allows objects to take on "many forms," meaning they can be treated as instances of their own class, their parent class, or any interface they implement. 
- It enables a single action or method to behave differently based on the object performing it.


- *Types of Polymorphism in Java*,
  - ***Compile-time Polymorphism (Static Polymorphism/ Early Binding)***
  - ***Runtime Polymorphism (Dynamic Polymorphism/ Late Binding)***


### Compile-time Polymorphism
- achieved through **Method Overloading**
- It's called "**compile-time**" because the compiler determines which overloaded method to call based on the method signature (number, type, and order of parameters) at compile time.

- ***Method Overloading***
  - occurs when a class has multiple methods with the same name but different parameter lists.
  - *Rules for Method Overloading*

    - Methods must have the same name.

    - Methods must have different parameter lists (different number of parameters, different types of parameters, or different order of parameters).

    - The return type can be the same or different (but changing only the return type is NOT sufficient for overloading).

    - Access modifiers can be the same or different.

    - `static` or non-`static` status can be the same or different.

  - it is called compile-time because compiler knows exactly which method to invoke based on the arguments passed.


### Runtime Plymorphism
- achieved through **Method Overriding**

- It's called "**runtime**" because the JVM determines which overridden method to call based on the actual type of the object (not the reference type) at runtime. This is also known as **Dynamic Method Dispatch**.

- ***Method Overriding***
  -  occurs when a subclass provides its own specific implementation for a method that is already defined in its superclass.
  
  - *Rules for Method Overriding*,
    - The method in the subclass must have the same name, same parameter list (same number, type, and order of parameters), and same return type (or a covariant return type, which means the return type can be a subclass of the superclass's return type).

    - The access modifier of the overriding method in the subclass cannot be more restrictive than the access modifier of the overridden method in the superclass (e.g., if superclass method is protected, subclass method can be protected or public, but not private).

    - The `private`, `static`, and `final` methods cannot be overridden.
      - `private` methods are not inherited, so they cannot be overridden.
      - `static` methods belong to the class, not an object, so they cannot be overridden (though a subclass can declare a `static` method with the same name, it's called "**method hiding**," not overriding).
      - `final` methods are designed to prevent overriding.

    - The overriding method cannot throw checked exceptions that are broader than those declared in the overridden method. It can throw narrower checked exceptions or no checked exceptions.

  - it is called runtime because the decision of which version of the method to execute is made by the JVM at runtime, based on the actual object type stored in the reference variable.

  - ***Upcasting (Implicit Casting)*** --> When a subclass object is referred to by a superclass reference variable, it's called upcasting. This is a fundamental aspect of runtime polymorphism.

  - ***Dynamic Method Dispatch (The Mechanism)***
    - the process by which a call to an overridden method is resolved at runtime rather than compile time.

    - When a method is called on an object reference, the JVM doesn't look at the type of the *reference variable*, instead it looks at the actual type of the object that the reference variable is pointing to on the Heap.
    - JVM then traverses the object's class hierarchy, starting from the object's actual class, to find the most specific implementation of that method.


### Polymorphism with Interfaces
- defines common behavior across unrelated classes.
- for more info take a look at [Interfaces](Interfaces.md)