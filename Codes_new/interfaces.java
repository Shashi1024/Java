package Codes_new;

import java.io.Serializable; // A classic example of a Marker Interface
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer; // A built-in Functional Interface

// --- 1. Basic (Pre-Java 8) Interface ---
// All methods are implicitly public abstract. All fields are implicitly public static final.
interface Drawable {
    // Implicitly public static final
    int MAX_DRAW_LIMIT = 100;

    // Implicitly public abstract
    void draw();
    void setColor(String color);
}

// --- 2. Interface with Default, Static, and Private Methods (Java 8/9+) ---
interface Resizable {
    void resize(double factor); // Abstract method

    // Default method: Provides a default implementation. Can be overridden by implementing classes.
    default void printResizeInstructions() {
        System.out.println("  (Default method) Use resize(factor) to change dimensions.");
        logAction("Printed resize instructions."); // Calling private method
    }

    // Static method: Belongs to the interface itself, not to implementing objects.
    // Called using InterfaceName.staticMethod().
    static void showResizableInfo() {
        System.out.println("  (Static method) Resizable objects can change their size.");
        // logAction("Showed resizable info."); // Compile-time error: static methods cannot call private instance methods directly
        // Static methods can call private static methods
        logStaticAction("Showed resizable info from static method.");
    }

    // Private method (Java 9+): Helper method for default/static methods within the interface.
    private void logAction(String action) {
        System.out.println("  (Private method) Logging action: " + action);
    }

    // Private static method (Java 9+): Helper for static methods within the interface.
    private static void logStaticAction(String action) {
        System.out.println("  (Private static method) Logging static action: " + action);
    }
}

// --- 3. Functional Interface (Single Abstract Method - SAM Interface) ---
// Has exactly one abstract method. Can have default/static/private methods.
// @FunctionalInterface annotation is optional but recommended for compiler checks.
@FunctionalInterface
interface Calculator {
    int operate(int a, int b); // The single abstract method

    // Can have default methods
    default void printOperationType() {
        System.out.println("Performing a binary operation.");
    }

    // Can have static methods
    static void showCalculatorPurpose() {
        System.out.println("This calculator performs integer operations.");
    }
}

// --- 4. Marker Interface ---
// An empty interface used to "tag" a class with a special capability.
interface Cacheable extends Serializable { // Can extend other interfaces (even marker interfaces)
    // No methods or fields
}

// --- Implementing Classes ---

// Class implementing multiple interfaces
class MyShape implements Drawable, Resizable, Cacheable {
    private String shapeName;
    private String color;
    private double size;

    public String getShapeName() {
        return shapeName;
    }

    public MyShape(String name, String color, double size) {
        this.shapeName = name;
        this.color = color;
        this.size = size;
    }

    // Implementing abstract method from Drawable
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " " + shapeName + " of size " + size + " units.");
    }

    // Implementing abstract method from Drawable
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Set " + shapeName + " color to " + color);
    }

    // Implementing abstract method from Resizable
    @Override
    public void resize(double factor) {
        this.size *= factor;
        System.out.println(shapeName + " resized by factor " + factor + ". New size: " + size);
    }

    // Optionally overriding default method from Resizable
    @Override
    public void printResizeInstructions() {
        System.out.println("  (Overridden default method) Specific resize instructions for " + shapeName + ".");
    }
}

// Class to demonstrate Diamond Problem resolution with default methods
interface InterfaceA {
    default void commonMethod() {
        System.out.println("InterfaceA's commonMethod");
    }
}

interface InterfaceB {
    default void commonMethod() {
        System.out.println("InterfaceB's commonMethod");
    }
}

// If MyClass implements both InterfaceA and InterfaceB, and both have
// a default method with the same signature, MyClass *must* override it.
class DiamondProblemResolver implements InterfaceA, InterfaceB {
    @Override
    public void commonMethod() {
        System.out.println("DiamondProblemResolver: My own implementation of commonMethod.");
        // Can optionally call specific interface's default method if needed
        InterfaceA.super.commonMethod();
        InterfaceB.super.commonMethod();
    }
}

// Class method takes precedence over interface default method
class SuperClassWithMethod {
    public void conflictMethod() {
        System.out.println("SuperClass's conflictMethod.");
    }
}

interface ConflictInterface {
    default void conflictMethod() {
        System.out.println("ConflictInterface's default conflictMethod.");
    }
}

class ConflictResolver extends SuperClassWithMethod implements ConflictInterface {
    // No override needed here. SuperClassWithMethod's method takes precedence.
}


public class interfaces {

    public static void main(String[] args) {
        System.out.println("--- Java Interfaces Demonstration ---");

        // --- 1. Using Basic Interface and Implementing Class ---
        System.out.println("\n--- 1. Basic Interface (Drawable) ---");
        MyShape circle = new MyShape("Circle", "Red", 10.0);
        circle.draw();
        circle.setColor("Blue");
        System.out.println("Max draw limit: " + Drawable.MAX_DRAW_LIMIT); // Accessing interface constant

        // --- 2. Using Interface with Default, Static, and Private Methods ---
        System.out.println("\n--- 2. Interface with Default, Static, Private Methods (Resizable) ---");
        MyShape square = new MyShape("Square", "Green", 5.0);
        square.resize(2.0);
        square.printResizeInstructions(); // Calls the overridden default method
        Resizable.showResizableInfo(); // Calling static method on the interface itself

        // --- 3. Functional Interface and Lambda Expressions ---
        System.out.println("\n--- 3. Functional Interface (Calculator) and Lambdas ---");
        // Using lambda expression to implement the single abstract method of Calculator
        Calculator adder = (a, b) -> a + b;
        System.out.println("10 + 5 = " + adder.operate(10, 5));

        Calculator multiplier = (num1, num2) -> num1 * num2;
        System.out.println("10 * 5 = " + multiplier.operate(10, 5));

        adder.printOperationType(); // Calling default method on lambda instance
        Calculator.showCalculatorPurpose(); // Calling static method on functional interface

        // Using a built-in functional interface (Consumer)
        Consumer<String> greeter = message -> System.out.println("Greeting: " + message);
        greeter.accept("Hello World!");

        // --- 4. Marker Interface ---
        System.out.println("\n--- 4. Marker Interface (Cacheable) ---");
        MyShape triangle = new MyShape("Triangle", "Yellow", 8.0);
        if (triangle instanceof Cacheable) {
            System.out.println(triangle.getShapeName() + " is Cacheable (implements Cacheable interface).");
        } else {
            System.out.println(triangle.getShapeName() + " is NOT Cacheable.");
        }
        // Marker interfaces are typically used by frameworks or the JVM for special processing.
        // For example, Serializable is a marker interface.

        // --- 5. Multiple Inheritance of Type (via Interfaces) ---
        System.out.println("\n--- 5. Multiple Inheritance of Type ---");
        System.out.println("MyShape implements Drawable, Resizable, and Cacheable.");
        System.out.println("This allows a single class to conform to multiple contracts.");

        // --- 6. Diamond Problem Resolution with Default Methods ---
        System.out.println("\n--- 6. Diamond Problem Resolution ---");
        DiamondProblemResolver resolver = new DiamondProblemResolver();
        resolver.commonMethod(); // Calls the overridden method in DiamondProblemResolver

        ConflictResolver conflict = new ConflictResolver();
        conflict.conflictMethod(); // Calls the method from SuperClassWithMethod (class method wins)
    }
}

