package codes.classes_objects;

// Demonstrates Java Sealed Classes (introduced in Java 17).
// Sealed classes allow you to explicitly declare which other classes
// or interfaces are permitted to extend or implement them.

// To compile and run this, you need Java 17 or later.
// Compile with: javac --enable-preview --release 17 SealedClassDemo.java
// Run with: java --enable-preview SealedClassDemo

// 1. Sealed Class: Declared with 'sealed' and 'permits' keywords.
// This interface can only be implemented by Car, Truck, and Motorcycle.
sealed interface Vehicle permits Car, Truck, Motorcycle {
    String getVehicleType();
}

// 2. Permitted Subclass: Must be declared 'final', 'sealed', or 'non-sealed'.
// 'final' means no further subclasses are allowed.
final class Car implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Car";
    }
}

// 3. Permitted Subclass: 'non-sealed' means it can be extended by any class.
non-sealed class Truck implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Truck";
    }
}

// 4. Permitted Subclass: 'sealed' means it can only be extended by its own permitted subclasses.
sealed class Motorcycle implements Vehicle permits SportBike, Cruiser {
    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }
}

// Permitted subclass of Motorcycle (must be final, sealed, or non-sealed)
final class SportBike extends Motorcycle {
    // No additional methods needed for this example
}

// Another permitted subclass of Motorcycle
non-sealed class Cruiser extends Motorcycle {
    // No additional methods needed for this example
}

// Uncommenting the following would cause a compile-time error:
/*
class Bicycle implements Vehicle { // Error: Bicycle is not permitted to implement Vehicle
    @Override
    public String getVehicleType() {
        return "Bicycle";
    }
}
*/

public class sealedClass {
    public static void main(String[] args) {
        System.out.println("--- Sealed Class Demonstration ---");

        Vehicle car = new Car();
        Vehicle truck = new Truck();
        Vehicle motorcycle = new Motorcycle();
        Vehicle sportBike = new SportBike();
        Vehicle cruiser = new Cruiser();

        System.out.println("Vehicle type: " + car.getVehicleType());
        System.out.println("Vehicle type: " + truck.getVehicleType());
        System.out.println("Vehicle type: " + motorcycle.getVehicleType());
        System.out.println("Vehicle type: " + sportBike.getVehicleType());
        System.out.println("Vehicle type: " + cruiser.getVehicleType());

        System.out.println("\nSealed types provide more control over inheritance hierarchies.");
        System.out.println("Only classes explicitly listed in 'permits' can extend/implement a sealed type.");
        System.out.println("Permitted subclasses must be 'final', 'sealed', or 'non-sealed'.");
    }
}
