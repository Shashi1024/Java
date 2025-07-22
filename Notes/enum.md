*Topics --> Enum, . . .*

## Enum

- an enum (short for enumeration) is a special kind of class that represents a fixed set of named constants.
- An enum defines a type that has a limited, predefined set of possible values.
- Enums are **type-safe**. You can only assign predefined enum constants.


- An enum is declared using the `enum` keyword, followed by the enum's name and a comma-separated list of its constants.
  - *Syntax*
    ```
    [accessModifier] enum EnumName {
        CONSTANT1, CONSTANT2, CONSTANT3, ... ; // Semicolon is optional if no other members
    }
    ```


***How Enums are Implemented Internally***
- the Java compiler treats an enum as a special kind of `final` class that implicitly extends `java.lang.Enum`.

- *Sample Declaration*
  ```
  public enum Day {
        MONDAY, TUESDAY;
    }
  ```

- The compiler essentially generates code similar to this (simplified, conceptual view):
  ```
    public final class Day extends java.lang.Enum<Day> {
        // Implicit private constructor
        private Day(String name, int ordinal) {
            super(name, ordinal);
        }

        // Public static final instances for each constant
        public static final Day MONDAY = new Day("MONDAY", 0);
        public static final Day TUESDAY = new Day("TUESDAY", 1);

        // Array to hold all constants (returned by values())
        private static final Day[] ENUM$VALUES = {MONDAY, TUESDAY};

        public static Day[] values() {
            return ENUM$VALUES.clone(); // Returns a clone to prevent external modification
        }

        public static Day valueOf(String name) {
            return (Day) Enum.valueOf(Day.class, name);
        }
        // Other inherited methods like toString(), equals(), hashCode(), compareTo()
    }
  ```

- **Implications of this implementation**,
  - `final` class --> an `enum` cannot be subclassed (extended). prevents further modificaiton of its fixed set of constants
  - extends `java.lang.Enum` --> all enums implicitly inherit from `java.lang.Enum` (this is why we cannot explicitly extend another class if we declare an enum)
  - **Implicit Constructor** --> enum constructors are implicitly `private`. (this ensures enum constants can only be created internally by the enum itself, guranteeing the fixed set)
  - **Singleton Instances** --> each enum constant is `public static final` instance of the enum type. (there will be only one instance of each constant in the JVM)
  - ***`name()` & `ordinal()` Methods*** (provided by `java.lang.enum`)
    - `name()` --> returns the name of this enum constant, exactly as declared in the enum declaration.
    - `ordinal()` -->  Returns the ordinal (position) of this enum constant in its enum declaration (initial constant is assigned an ordinal of 0)



### Enums with Fields, Constructors, and Methods

- allows us to associate data and behavior with each enum constant.

- **Rules for Adding Members**,
  - The list of enum constants must be terminated by a semicolon (`;`) if you declare any other members (fields, constructors, methods).
  - Constructors are implicitly `private`
  - Constructors are called implicitly when the enum constants are declared.
  - Fields can be instance fields (associated with each constant) or static fields (shared by all constants).

- *Example*,
  ```
    public enum TrafficLight {
        RED(30, "Stop"),       // Calls constructor with (30, "Stop")
        YELLOW(5, "Prepare"),  // Calls constructor with (5, "Prepare")
        GREEN(25, "Go");       // Calls constructor with (25, "Go")

        private final int durationSeconds; // Instance field
        private final String action;       // Instance field

        // Constructor (implicitly private)
        private TrafficLight(int durationSeconds, String action) {
            this.durationSeconds = durationSeconds;
            this.action = action;
        }

        // Instance method
        public int getDurationSeconds() {
            return durationSeconds;
        }

        // Instance method
        public String getAction() {
            return action;
        }

        // You can also override methods from Object, like toString()
        @Override
        public String toString() {
            return name() + " (" + action + " for " + durationSeconds + "s)";
        }

        public static void main(String[] args) {
            TrafficLight currentLight = TrafficLight.RED;

            System.out.println("Current light: " + currentLight.name()); // RED
            System.out.println("Duration: " + currentLight.getDurationSeconds() + " seconds"); // 30
            System.out.println("Action: " + currentLight.getAction()); // Stop
            System.out.println(currentLight); // RED (Stop for 30s) - thanks to overridden toString()

            System.out.println("\n--- All Lights ---");
            for (TrafficLight light : TrafficLight.values()) {
                System.out.println(light.name() + ": " + light.getAction() + " for " + light.getDurationSeconds() + "s");
            }
        }
    }

  ```

***Enum in `switch` Statements***
- *Refer the code example: Link --> [Coming Soon]()*


---

- there are Enum-specific methods (`java.lang.Enum`) like `String name()`, `int ordinal()`, `static EnumType[] values()`, `static EnumType valueOf(String name)`, `int compareTo(EnumType other)`

- and there are specialized collections for Enum like,
  - `EnumSet`
  - `EnumMap`