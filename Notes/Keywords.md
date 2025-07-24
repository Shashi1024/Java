> *Topics --> final, static, super, default, this, . . .*\
> *Link to Codes --> [Codes](../codes/Keywords/)*

## Keywords
- There are 53 keywords in java.
  - 50 keywords are actively used.
  - 2 keywords (const & goto) are reserved but not currently used.
  - 3 keywords (true, false & null) are technically literals but are often included in list of keywords due to their reserved nature.

---

### `this` Keyword

> *Link to Codes --> [Codes](../codes/keywords/ThisKeyword.java)*

- The primary purpose of `this` is to resolve ambiguity between instance variables and local variables (or method parameters) that have the same name.

- **To Refer to Current Class Instance Variables (Disambiguation)**
  - When an instance variable and a local variable (often a method parameter) have the same name, `this` is used to differentiate between them.

- **To Invoke Current Class Methods**
  - to explicitly call another method of the same class. (for better readability)

- **To Invoke Current Class Constructors (Constructor Chaining)**
  - `this()` (with parentheses, similar to a method call) is used to invoke another constructor of the same class.
  - *Rules*
    - It must be the first statement in the constructor.
    - A constructor can call `this()` or `super()`, but not both.
    - It can only be used within a constructor, not within a method.

- **To Pass the Current Class Instance as an Argument in Method Calls**
  - to pass the current object itself as an argument to another method. 

- **To Return the Current Class Instance from a Method**
  - we can return `this` from a method, which is commonly used in method chaining (or fluent API design). 
  - This allows us to call multiple methods on the same object in a single statement.
  - *Example* --> `Stream.filter().map().collect()`

- **In Inner/Nested Classes (Disambiguating `this` for Outer Class)**
  - `this` inside the inner class refers to the inner class's instance.
  - If you need to refer to the outer class's instance, you use `OuterClassName.this`.
  - *Example* --> `Outer.this.colour`



***Rules of `this`***
- can be use in **Non-Static Context Only**
- **Final Reference** --> `this` reference is implicitly `final`, meaning we cannot reassign it to point to a different object.


---


### `static` Keyword

*Link to Codes --> [Codes](../codes/keywords/StaticKeyword.java)*

- it is a non-access modifier
- It signifies that a member belongs to the class itself, rather than to any specific instance of that class
- only one copy of a static variable, shared by all objects of a class.
- static methods operate on class level data
- static members are loaded when the class is loaded into JVM

- **Not Inherently Thread-safe**

- **`static` Variables**
  - A variable declared with the `static` keyword inside a class, but outside any method, constructor, or block.
  - *Syntax* --> `[accessModifier] static dataType variableName [= initialValue];`
  - *Characteristics*
    - **Memory Location** --> stored in *Method Area* (part of Heap)
    - **Single Copy**
    - **Lifetime** --> as long as the class remains in JVM
  
  - *Access* --> `ClassName.staticVariable`
    - can also be accessed via an object reference (e.g., `objectName.staticVariable`)(discouraged)

  - - **No Static Local Variables**


- **`static` Methods**
  - method declared with the `static` keyword inside a class.
  - *Syntax* --> `[accessModifier] static returnType methodName([parameterList]) { /* ... */ }`
  - *Access* --> `ClassName.staticMethodName(arguments)`
    - can also be accessed via an object reference (e.g., `objectName.staticMethodName(arguments)`)(discouraged)
  
  - *Restrictions*
    - **Cannot Access Non-Static Members**
    - **No `this` or `super`**
    - **Cannot Override Instance Methods** --> A static method cannot override an instance method from a superclass. Similarly, an instance method cannot override a static method.



- **Static Blocks (Static Initializer Blocks)**
  - A block of code defined within a class, prefixed with the `static` keyword.
  - *Syntax*,
    ```
    static {
        // Code to be executed once when the class is loaded
    }
    ```
  - A class can have multiple static blocks. They are executed in the order they appear in the class.
  - A static block is executed only once when the class is first loaded into the JVM. (even before `main` Method)
  - To initialize `static` fields that require complex logic (more than a single expression).

  - *The order of execution when a class is loaded is*,

    - > `Static` blocks and `static` field initializations (in order of appearance).
    - > `main` method (if it's the entry point).
    - > Instance initializers and instance field initializations (when an object is created).
    - > Constructors (when an object is created).



- **Static Nested Classes**
  - A class defined within another class and explicitly declared with the `static` keyword.
  - *Syntax*
    ```
    class OuterClass {
        static class StaticNestedClass {
            // ...
        }
    }
    ```
  - Can only directly access `static` members (fields and methods) of the outer class.
  - Cannot directly access non-`static` (instance) members of the outer class.
  - *Instantiation* --> Does not require an instance of the outer class to be created.
    - `OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();`
  - Can declare its own `static` members (fields and methods), unlike non-`static` inner classes.



- **Static Imports (Java 5+)**
  - Allows us to import `static` members (fields and methods) of a class directly into our current class, so we can use them without qualifying them with the class name.
  - *Syntax*
    - `import static package.ClassName.staticMember;` (for a single static member)
    - `import static package.ClassName.*;` (for all static members of a class)
  

---


### `` Keyword