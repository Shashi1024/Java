package Codes;

// --- 1. Runtime Polymorphism (Method Overriding & Dynamic Method Dispatch) ---

// Superclass
class Vehicle {
    // Private method cannot be overridden (it's not inherited)
    private void startEnginePrivate() {
        System.out.println("Vehicle: Starting engine privately.");
    }

    // Final method cannot be overridden
    public final void honk() {
        System.out.println("Vehicle: Honk! Honk!");
    }

    // Static method cannot be overridden (it's method hiding if redefined)
    public static void identifyType() {
        System.out.println("Vehicle: This is a generic vehicle.");
    }

    // Method to be overridden by subclasses
    public void drive() {
        System.out.println("Vehicle: Driving generally.");
    }

    // Method with a return type that can be covariant
    public Vehicle getVehicleInstance() {
        return new Vehicle();
    }
}

// Subclass overriding methods from Vehicle
class Car extends Vehicle {
    // Method Overriding: Same signature, same or less restrictive access modifier
    @Override
    public void drive() {
        System.out.println("Car: Driving on the road.");
        // Calling superclass's drive method
        super.drive();
    }

    // Method Hiding (not overriding): Static method with same name in subclass
    public static void identifyType() {
        System.out.println("Car: This is a car.");
    }

    // Covariant return type: Car returns a Car instance, which is a subclass of Vehicle.
    @Override
    public Car getVehicleInstance() {
        return new Car();
    }

    // Specific method for Car
    public void openTrunk() {
        System.out.println("Car: Trunk opened.");
    }
}

class Motorcycle extends Vehicle {
    @Override
    public void drive() {
        System.out.println("Motorcycle: Riding on two wheels.");
    }
}

// --- 2. Compile-time Polymorphism (Method Overloading) ---
class Calculator {
    // Overloaded method: Same name, different number of parameters
    public int add(int a, int b) {
        System.out.println("Calculator: Adding two integers.");
        return a + b;
    }

    // Overloaded method: Same name, different types of parameters
    public double add(double a, double b) {
        System.out.println("Calculator: Adding two doubles.");
        return a + b;
    }

    // Overloaded method: Same name, different order of parameters (though less common for simple types)
    public String add(String s1, String s2) {
        System.out.println("Calculator: Concatenating two strings.");
        return s1 + s2;
    }

    // Overloaded method: Same name, different number of parameters
    public int add(int a, int b, int c) {
        System.out.println("Calculator: Adding three integers.");
        return a + b + c;
    }

    // This would NOT be overloading (only return type changed)
    // public double add(int a, int b) { return (double)a + b; } // Compile-time error
}

// --- 3. Polymorphism with Interfaces ---
interface Flyable {
    void fly(); // Abstract method
}

class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird is flying high.");
    }
}

class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Airplane is soaring through the sky.");
    }

    public void takeOff() {
        System.out.println("Airplane taking off.");
    }
}

public class polymorphism {

    public static void main(String[] args) {
        System.out.println("--- Polymorphism Demonstration ---");

        // --- Runtime Polymorphism (Method Overriding) ---
        System.out.println("\n--- 1. Runtime Polymorphism (Method Overriding) ---");

        // Upcasting: Superclass reference pointing to a subclass object
        Vehicle myVehicle1 = new Car(); // myVehicle1 is a Vehicle reference, but points to a Car object
        Vehicle myVehicle2 = new Motorcycle(); // myVehicle2 is a Vehicle reference, but points to a Motorcycle object

        System.out.println("\n--- Dynamic Method Dispatch ---");
        // The actual method called depends on the object's *actual type* at runtime, not the reference type.
        myVehicle1.drive(); // Calls Car's drive() method
        myVehicle2.drive(); // Calls Motorcycle's drive() method

        // Accessing methods specific to the subclass requires downcasting (with instanceof for safety)
        if (myVehicle1 instanceof Car) {
            Car carFromVehicle = (Car) myVehicle1;
            carFromVehicle.openTrunk();
        }

        // Final methods cannot be overridden
        myVehicle1.honk(); // Calls Vehicle's honk()
        myVehicle2.honk(); // Calls Vehicle's honk()

        // Static methods are hidden, not overridden. The method called depends on the reference type.
        Vehicle.identifyType(); // Calls Vehicle's static method
        Car.identifyType();     // Calls Car's static method
        myVehicle1.identifyType(); // Calls Vehicle's static method (because myVehicle1 is a Vehicle reference)

        // Covariant return types
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Car();

        Vehicle returnedV1 = v1.getVehicleInstance();
        Vehicle returnedV2 = v2.getVehicleInstance(); // This actually returns a Car object

        System.out.println("Returned instance from Vehicle: " + returnedV1.getClass().getSimpleName());
        System.out.println("Returned instance from Car (via Vehicle reference): " + returnedV2.getClass().getSimpleName());


        // --- Compile-time Polymorphism (Method Overloading) ---
        System.out.println("\n--- 2. Compile-time Polymorphism (Method Overloading) ---");
        Calculator calc = new Calculator();

        // Compiler determines which 'add' method to call based on arguments' types/number
        System.out.println("Sum of integers: " + calc.add(5, 10));
        System.out.println("Sum of doubles: " + calc.add(5.5, 10.3));
        System.out.println("Concatenated strings: " + calc.add("Hello", " World"));
        System.out.println("Sum of three integers: " + calc.add(1, 2, 3));

        // --- Polymorphism with Interfaces ---
        System.out.println("\n--- 3. Polymorphism with Interfaces ---");

        // Interface reference pointing to implementing class objects
        Flyable obj1 = new Bird();
        Flyable obj2 = new Airplane();

        // Polymorphic behavior: calling fly() on different objects via the same interface reference
        obj1.fly(); // Calls Bird's fly()
        obj2.fly(); // Calls Airplane's fly()

        // Accessing specific methods requires downcasting
        if (obj2 instanceof Airplane) {
            Airplane plane = (Airplane) obj2;
            plane.takeOff();
        }

        System.out.println("\nPolymorphism allows a single action to behave differently based on the object performing it.");
    }
}

