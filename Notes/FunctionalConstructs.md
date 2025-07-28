> *Topics --> Anonymous Inner Classes, Lambda Expressions, Method References,  . . .*\
> *Link to Codes --> [Codes](../Codes/)*


### Anonymous Inner Classes
- it is an inner class without a name. (defined and instantiated in a single expression)
- used when we need to create an object of a class that implements a specific interface (or extends a class) but we only need one instance of that class.
- Before Java 8, if we needed to implement a single-method interface (or extend a class) on the fly, often for a one-time use, we would use an anonymous inner class.

- *Syntax*,
  ```
  new InterfaceName() {
      // Implementation of the interface's methods
      @Override
      public void abstractMethod() {
          // Method body
      }
      // You can also add other methods, but they won't be part of the interface contract
      public void anotherMethod() { /* ... */ }
  };
  ```
  ```
  // OR for extending a class:
  new ClassName() {
      // Override methods from ClassName
      @Override
      public void overriddenMethod() {
          // Method body
      }
  };
  ```

- *Characteristics*
  - No Name
  - Defined and instantiated simultaneously
  - single use only
  - Access to Enclosing Scope --> can access `final` or effectively `final` local variables from their enclosing scope.
  - can implement exactly one interface or extend exactly one class.
  - Can Have Members
  - `this` keyword inside an anonymous inner class refers to the instance of the anonymous class itself, not the enclosing class.
  - each anonymous inner class results in a separate `.class` file.

---

### Lambda Expressions
- introduced in Java 8
- lambda expression is a block of code that you can pass around as an object.
- It's an anonymous function that can be treated as an instance of a functional interface.
- *General Syntax*
  - `(parameters) -> { body }`
    - `parameters` : A comma-separated list of formal parameters.
    - `->` (Arrow Token) : Separates the parameters from the body.
    - `body` : The code that implements the abstract method of the functional interface. It can be a single expression or a block of statements.

- *Different Syntax forms*
  - **No Parameters**
    ```
    // Anonymous inner class:
    // new Runnable() { @Override public void run() { System.out.println("Hello"); } }

    // Lambda:
    Runnable r = () -> System.out.println("Hello");
    ```
  - **Single Parameter (type inferred, no parentheses needed)**
    ```
    // Anonymous inner class:
    // new Consumer<String>() { @Override public void accept(String s) { System.out.println(s); } }

    // Lambda:
    Consumer<String> printer = s -> System.out.println(s);
    ```
  - **Single Parameter (explicit type)**
    ```
    Consumer<String> printer = (String s) -> System.out.println(s);
    ```
  - **Multiple Parameters (parentheses required)**
    ```
    // Anonymous inner class:
    // new BiFunction<Integer, Integer, Integer>() { @Override public Integer apply(Integer a, Integer b) { return a + b; } }

    // Lambda:
    BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;
    ```
  - **Block Body (curly braces required, return statement if needed)**
    ```
    // Anonymous inner class:
    // new Predicate<Integer>() {
    //     @Override
    //     public boolean test(Integer n) {
    //         if (n % 2 == 0) {
    //             return true;
    //         } else {
    //             return false;
    //         }
    //     }
    // }

    // Lambda:
    Predicate<Integer> isEven = n -> {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    };
    // Or even more concisely: Predicate<Integer> isEven = n -> n % 2 == 0;
    ```

- Lambda expressions can only be used in contexts where a **functional interface** is expected. 
- *Characteristics*
  - Type Inference --> compiler can often infer the types of the parameters based on the context (the functional interface), reducing the need for explicit type declarations.
  - `this` Keyword Context --> Inside a lambda expression, the `this` keyword refers to the instance of the enclosing class, unlike anonymous inner classes where `this` refers to the anonymous class itself. 
  - No Separate `.class` Files

  - **Scope and Variable Capture**
    - Lambdas can "capture" or "close over" variables from their enclosing scope. However, these captured variables must be *effectively final*. (to prevent concurrency issues)
    - *Effectively Final* -->  A variable is effectively final if its value is not changed after it is initialized. You don't need to explicitly declare it `final`, but the compiler will treat it as such.
    ```
    String greeting = "Hello"; // Effectively final

    // This lambda captures 'greeting'
    Consumer<String> greeter = name -> System.out.println(greeting + ", " + name + "!");
    greeter.accept("Alice"); // Output: Hello, Alice!

    // greeting = "Hi"; // This would cause a compile-time error because greeting is effectively final
    ```


--- 

### Method References
- it is a compact way to represent a lambda expression that does nothing more than call an existing method. 
- Instead of providing the full body of a lambda expression, you simply provide a reference to the method itself.
- `::` operator is used.
- *Syntax* --> `ClassName::methodName` or `objectName::methodName`

- **Why Method References?**
  - to pass a function as an argument we had to use anonymous inner classes, but next came the lambda expressions.
    ```
    // Lambda expression calling an existing method
    list.forEach(item -> System.out.println(item));
    ```
  - This lambda `item -> System.out.println(item)` is essentially just forwarding its argument to the `System.out.println` method. 

  - then came the Method References
    ```
    // Equivalent using a method reference
    list.forEach(System.out::println);
    ```

#### Types of Method References
  1. **Reference to a Static Method**
     - refers to the static methods of a class
     - The functional interface's abstract method parameters must match the static method's parameters, and their return types must be compatible.
     - *Syntax* --> `ClassName::staticMethodName`

  2. **Reference to an Instance Method of a Particular Object**
     - refers to an instance method of a specific, existing object.
     - The functional interface's abstract method parameters must match the instance method's parameters, and their return types must be compatible.
     - *Syntax* --> `objectName::instanceMethodName`

  3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type**
     - It refers to an instance method that will be invoked on an arbitrary object of a particular type.(perhaps the most nuanced type)
     - The key here is that the first parameter of the functional interface's abstract method becomes the target object on which the instance method is invoked.
     - *Syntax* --> `ClassName::instanceMethodName`
     - *Example*
       ```
       // Functional interface: Predicate<T> has an abstract method: boolean test(T t);
       // Instance method: String.isEmpty()
       Predicate<String> isEmptyChecker = String::isEmpty; // Equivalent to: s -> s.isEmpty()
       boolean result = isEmptyChecker.test("hello"); // result will be false
       boolean result2 = isEmptyChecker.test("");    // result2 will be true
       ```
      - In this case, `String::isEmpty` means "take the String argument passed to `test()` and call `isEmpty()` on it."

  4. **Reference to a Constructor**
     - refers to a constructor
     - The functional interface's abstract method parameters must match the constructor's parameters, and its return type must be compatible with the type constructed by the constructor.
     - *Syntax* --> `ClassName::new`
     - *Example*
       ```
       // Functional interface: Function<Integer, String[]> has: String[] apply(Integer i);
       // Constructor: String[](int size)
       Function<Integer, String[]> stringArrayCreator = String[]::new; // Equivalent to: size -> new String[size]
       String[] array = stringArrayCreator.apply(5); // Creates a new String[5]
       ```


#### How does it work under the Hood?
- When the Java compiler encounters a method reference, it translates it into an equivalent lambda expression.
- This lambda expression then implements the single abstract method of the target functional interface.
- The compiler performs type inference to determine which specific method the reference points to, based on the context (the functional interface it's being assigned to).
- It checks for compatibility in:
  - Number of parameters --> same no. of parameters (except for type-3 refs)
  - Parameter types --> The types must be compatible (e.g., `String` can be passed where `Object` is expected).
  - Return type --> The return types must be compatible.
  - Checked exceptions --> Any checked exceptions thrown by the referenced method must be handled by the functional interface's abstract method signature.



---

### Relationship: Anonymous Inner Classes -> Lambda Expressions -> Method References

- **Anonymous Inner Classes (Pre-Java 8)**
  - The original way to implement interfaces or extend classes inline. Verbose.
    ```
    Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.println("AIC");
        }
    };
    ```

- **Lambda Expressions (Java 8)**
  - A concise replacement for anonymous inner classes when dealing with functional interfaces.
    ```
    Runnable r = () -> System.out.println("Lambda");
    ```

- **Method References (Java 8)**
  - An even more concise shorthand for lambdas that simply call an existing method.
    ```
    // Assuming a method: void printMessage() { System.out.println("Method Ref"); }
    // Runnable r = this::printMessage; // If printMessage is an instance method
    // Runnable r = MyClass::staticPrintMessage; // If static
    ```
