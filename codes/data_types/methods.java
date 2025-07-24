package Codes.data_types;

// Demonstrates different types of methods in Java: instance, static, and constructors,
// along with method overloading and pass-by-value.

class Calculator {
    // Instance variable
    String name;

    // Constructor 1: No arguments
    public Calculator() {
        this.name = "Basic Calculator";
        System.out.println("Calculator created: " + this.name);
    }

    // Constructor 2: With arguments (Overloaded Constructor)
    public Calculator(String customName) {
        this.name = customName;
        System.out.println("Custom Calculator created: " + this.name);
    }

    // Instance Method: Belongs to an object, can access instance variables
    public int add(int a, int b) {
        System.out.println("Using instance method: " + this.name);
        return a + b;
    }

    // Overloaded Instance Method: Same name, different parameters
    public double add(double a, double b) {
        System.out.println("Using overloaded instance method (double): " + this.name);
        return a + b;
    }

    // Overloaded Instance Method: Different number of parameters
    public int add(int a, int b, int c) {
        System.out.println("Using overloaded instance method (three ints): " + this.name);
        return a + b + c;
    }

    // Static Method: Belongs to the class, cannot access instance variables directly
    public static int multiply(int x, int y) {
        // System.out.println("Calculator name: " + name); // Compile-time error: cannot access instance variable
        System.out.println("Using static method for multiplication.");
        return x * y;
    }

    // Method to demonstrate Pass by Value for primitive types
    public void modifyPrimitive(int value) {
        System.out.println("Inside modifyPrimitive: Before change, value = " + value);
        value = 200; // This changes the local copy of 'value'
        System.out.println("Inside modifyPrimitive: After change, value = " + value);
    }

    // Method to demonstrate Pass by Value for reference types
    public void modifyObject(Calculator calcObj) {
        System.out.println("Inside modifyObject: Before change, calcObj name = " + calcObj.name);
        calcObj.name = "Modified Calculator"; // This changes the state of the object the reference points to
        System.out.println("Inside modifyObject: After changing state, calcObj name = " + calcObj.name);

        // Reassigning the parameter reference to a new object
        calcObj = new Calculator("New Object Calc"); // This changes the local copy of the reference
        System.out.println("Inside modifyObject: After reassigning reference, calcObj name = " + calcObj.name);
    }
}

public class methods {
    public static void main(String[] args){
        System.out.println("--- Methods Demonstration ---");

        // 1. Constructors
        Calculator calc1 = new Calculator(); // Calls the no-argument constructor
        Calculator calc2 = new Calculator("Scientific Calc"); // Calls the overloaded constructor

        // 2. Instance Methods
        System.out.println("\n--- Instance Methods ---");
        int sum1 = calc1.add(5, 7); // Calling instance method on calc1 object
        System.out.println("Sum (int): " + sum1);

        double sum2 = calc2.add(10.5, 20.3); // Calling overloaded instance method on calc2 object
        System.out.println("Sum (double): " + sum2);

        int sum3 = calc1.add(1, 2, 3); // Calling another overloaded instance method
        System.out.println("Sum (three ints): " + sum3);

        // 3. Static Methods
        System.out.println("\n--- Static Methods ---");
        int product = Calculator.multiply(4, 6); // Calling static method using class name (recommended)
        System.out.println("Product: " + product);

        // Can also be called via object reference, but not recommended
        int product2 = calc1.multiply(3, 8); // This works but is confusing
        System.out.println("Product (via object - not recommended): " + product2);

        // 4. Pass by Value
        System.out.println("\n--- Pass by Value ---");

        // For Primitive Types
        int originalValue = 10;
        System.out.println("Before modifyPrimitive: originalValue = " + originalValue);
        calc1.modifyPrimitive(originalValue);
        System.out.println("After modifyPrimitive: originalValue = " + originalValue); // originalValue remains 10

        // For Reference Types
        Calculator originalCalc = new Calculator("Original Calc");
        System.out.println("Before modifyObject: originalCalc name = " + originalCalc.name);
        calc1.modifyObject(originalCalc);
        System.out.println("After modifyObject: originalCalc name = " + originalCalc.name);
        // The object's state was changed ("Modified Calculator"), but the originalCalc reference still points
        // to the same object. The reassignment inside modifyObject only affected the local copy of the reference.

        // 5. Method Signature
        // The signature for 'add' methods are:
        // add(int, int)
        // add(double, double)
        // add(int, int, int)
        // Return type (int, double) and access modifier (public) are NOT part of the signature.
    }
}
