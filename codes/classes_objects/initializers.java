package codes.classes_objects;

// Demonstrates the execution order of static initializers, instance initializers,
// and constructor, along with different variable types.

class InitializationOrderDemo {
    // 1. Static Variable: Initialized when the class is loaded.
    static String staticMessage = "Static variable initialized.";

    // 2. Static Initializer Block: Executed once when the class is loaded.
    static {
        System.out.println("Static Initializer Block 1 executed.");
        // Can access static members
        System.out.println("From static block: " + staticMessage);
        staticMessage = "Static variable modified in block.";
    }

    // Another Static Initializer Block (executed in order)
    static {
        System.out.println("Static Initializer Block 2 executed.");
    }

    // 3. Instance Variable: Initialized when an object is created.
    String instanceMessage = "Instance variable initialized.";

    // 4. Instance Initializer Block: Executed every time an object is created, before the constructor.
    {
        System.out.println("Instance Initializer Block 1 executed.");
        // Can access instance and static members
        System.out.println("From instance block: " + instanceMessage);
        System.out.println("From instance block: " + staticMessage);
        instanceMessage = "Instance variable modified in block.";
    }

    // Another Instance Initializer Block (executed in order)
    {
        System.out.println("Instance Initializer Block 2 executed.");
    }

    // 5. Constructor: Executed after all instance initializers.
    public InitializationOrderDemo() {
        System.out.println("Constructor executed.");
        System.out.println("From constructor: " + instanceMessage);
    }

    // Another constructor (overloaded)
    public InitializationOrderDemo(String msg) {
        System.out.println("Parameterized Constructor executed with message: " + msg);
        this.instanceMessage = msg;
    }
}


public class initializers {
    public static void main(String[] args){
        System.out.println("--- Initializers and Variables Demonstration ---");

        System.out.println("\nCreating first object:");
        InitializationOrderDemo obj1 = new InitializationOrderDemo();
        System.out.println("Object 1 instance message: " + obj1.instanceMessage);
        System.out.println("Static message (via class): " + InitializationOrderDemo.staticMessage);

        System.out.println("\nCreating second object:");
        InitializationOrderDemo obj2 = new InitializationOrderDemo("Custom Message");
        System.out.println("Object 2 instance message: " + obj2.instanceMessage);
        System.out.println("Static message (via class): " + InitializationOrderDemo.staticMessage);
        // Note: Static blocks are NOT executed again for the second object.

        // Demonstrating Numeric Literals with Underscores
        System.out.println("\n--- Numeric Literals with Underscores ---");
        int million = 1_000_000;
        long creditCardNumber = 1234_5678_9012_3456L;
        double piValue = 3.141_592_653_589_793;

        System.out.println("Million: " + million);
        System.out.println("Credit Card Number: " + creditCardNumber);
        System.out.println("Pi Value: " + piValue);
    }
}
