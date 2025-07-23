*Topics --> final, static, super, default, this, . . .*
*Link to Codes --> [Codes](../codes/keywords/)*

## Keywords
- There are 53 keywords in java.
  - 50 keywords are actively used.
  - 2 keywords (const & goto) are reserved but not currently used.
  - 3 keywords (true, false & null) are technically literals but are often included in list of keywords due to their reserved nature.

---

### `this` Keyword

*Link to Codes --> [Codes](../codes/keywords/ThisKeyword.java)

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

