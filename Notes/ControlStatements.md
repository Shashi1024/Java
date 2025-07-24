> *Topics: Control Flow Statements, ...*\
> *Link to Codes --> [Codes](../codes/collections/)*



## Control Flow Statements

### Conditional (Decision-Making) Statements
  - These statements allow your program to choose different paths of execution based on whether a specified boolean condition evaluates to `true` or `false`.

  - *Statements*,
    - ***`if` Statement***
    - ***`if-else` Statement***
    - ***`if-else-if` ladder***
    - ***`Switch` Statement***


#### `Switch` Statement
- The `switch` statement allows you to select one of many code blocks to be executed.
- ***Pre-Java 12***
    - *Supported Types* --> The `switch` expression can be of type `byte`, `short`, `char`, `int`, `String` (since Java 7), `enum`, or their corresponding wrapper classes (`Byte`, `Short`, `Character`, `Integer`). `long`, `float`, `double`, and `boolean` are not supported.

        ```
        switch(expr){
            case a: code goes here
                break;
            case b: code goes here
                break;
            default: code goes here
        }
        ```

- ***Evolution of switch (Java 12+ Preview, Java 14+ Standard)***
  - addresses the **"fall-through"** issue and allowing `switch` to be used as an **expression**.
  - *New features*,
    - **Arrow Syntax** (`->`): Replaces the colon (`:`) and implicitly handles `break`. This eliminates accidental fall-through.
    - **Multiple Case Labels**: Multiple `case` labels can be separated by commas, making it more concise when several cases share the same logic.
    - **`switch` Expressions**: Allows the `switch` statement to return a value, making it usable in assignments. This is a major change, transforming `switch` from just a statement to an expression.
    - **`yield` Keyword (for `switch` expressions with blocks)**: If a case block in a switch expression needs multiple statements, you can use yield to specify the value to be returned by the switch expression.
    
        ```
        // As an expression, assigning a value
        dataType result = switch (expression) {
            case value1 -> valueToReturn1;

            case value2, value3 -> valueToReturn2; // Multiple labels

            default -> { // Block for more complex logic
                // multiple statements
                System.out.println("Complex default logic.");
                yield valueToReturnDefault; // 'yield' returns the value
            }
        };

        // As a statement
        switch (expression) {
            case value1 -> System.out.println("Case 1 logic");

            case value2 -> {
                // multiple statements
                System.out.println("Case 2 start");
                System.out.println("Case 2 end");
            }
            default -> System.out.println("Default logic");
        }
        ```

- **Fall-Through** --> when a case is matched in a switch statement, not only the correct case but all the cases below it will get executed.


---

### Looping (Iterative) Statements
  - These statements allow a block of code to be executed repeatedly as long as a certain condition remains `true`.
  - *Statements*,
    - ***`for` Loop***
    - ***Enhanced `for` Loop (for-each loop)***
    - ***`while` Loop***
    - ***`do-while` Loop***


  - **`for-each` Loop**
    - introduced in java 5
    - simpler way to iterate over elements of arrays and collections (classes that implement `Iterable`).
    - its read-only.

---

### Branching (Jump) Statements
  - These statements allow you to transfer control to a different part of your program, typically within loops or `switch` statements.
  - *Statements*,
    - ***`break` Statement*** --> terminates the innermost Loop. inside `switch` prevents "fall-through" to the next `case`
    - ***`continue` Statement*** --> skips the rest of the current iteration of the innermost loop.
    - ***`return` Statement*** --> used to exit a method.



### Labeled `break` and `continue`
- Java supports labeled `break` and `continue` statements, which allow you to break out of or continue a specific outer loop in nested loop structures. 

    ```
    labelName: // A valid Java identifier followed by a colon
    statement; // The loop or block to which the label applies
    ```

- The label must immediately precede the loop or block statement it applies to.

- usage,
  ```
  break labelName;
  continue labelName;
  ```
