package codes.data_types;


// Demonstrates implicit (widening) and explicit (narrowing) type casting for primitives
// and upcasting/downcasting for reference types.

// Parent class for reference type casting
class Animal {
    String species;

    public Animal(String species) {
        this.species = species;
    }

    public void eat() {
        System.out.println(species + " is eating.");
    }
}

// Child class for reference type casting
class Cat extends Animal {
    String name;

    public Cat(String name) {
        super("Cat"); // Call to parent constructor
        this.name = name;
    }

    public void meow() {
        System.out.println(name + " the " + species + " says meow!");
    }
}


public class typeCasting {
    public static void main(String[] args){
        System.out.println("--- Type Casting Demonstration ---");

        // 1. Implicit Type Casting (Widening Conversion)
        // Smaller type to larger type - safe, no data loss, automatic
        System.out.println("\n--- Implicit Type Casting (Widening) ---");
        int myInt = 100;
        long myLong = myInt;       // int to long
        float myFloat = myLong;    // long to float (can lose precision for very large numbers)
        double myDouble = myFloat; // float to double

        System.out.println("int to long: " + myInt + " -> " + myLong);
        System.out.println("long to float: " + myLong + " -> " + myFloat);
        System.out.println("float to double: " + myFloat + " -> " + myDouble);

        // 2. Explicit Type Casting (Narrowing Conversion)
        // Larger type to smaller type - potential data loss, requires cast operator ()
        System.out.println("\n--- Explicit Type Casting (Narrowing) ---");
        double largeDouble = 123.456;
        int castedInt = (int) largeDouble; // double to int, fractional part is truncated
        System.out.println("double to int (truncation): " + largeDouble + " -> " + castedInt);

        long veryLargeLong = 250L;
        byte castedByte = (byte) veryLargeLong; // long to byte, potential overflow/wrap-around
        System.out.println("long to byte (potential overflow): " + veryLargeLong + " -> " + castedByte);
        // 250 in byte range (-128 to 127) wraps around to -6

        char charValue = 'A';
        int charToInt = (int) charValue; // char to int (gets ASCII/Unicode value)
        System.out.println("char to int: " + charValue + " -> " + charToInt);

        // 3. Type Casting with Reference Types
        System.out.println("\n--- Reference Type Casting ---");

        // Upcasting: Subclass object to Superclass reference
        // Always safe, implicit
        Animal myAnimal = new Cat("Whiskers"); // Upcasting: Cat object referred to by Animal reference
        System.out.println("Upcasted object species: " + myAnimal.species);
        myAnimal.eat(); // Can call methods defined in Animal (or overridden by Cat)
        // myAnimal.meow(); // Compile-time error: meow() is not defined in Animal

        // Downcasting: Superclass reference to Subclass type
        // Potentially unsafe, requires explicit cast, can throw ClassCastException
        System.out.println("\nDowncasting:");
        Animal anotherAnimal = new Cat("Mittens"); // This Animal reference points to a Cat object

        // Safe Downcasting with instanceof
        if (anotherAnimal instanceof Cat) {
            Cat myCat = (Cat) anotherAnimal; // Downcasting: Animal reference to Cat type
            System.out.println("Successfully downcasted. Cat's name: " + myCat.name);
            myCat.meow(); // Now can call Cat-specific methods
        } else {
            System.out.println("Cannot downcast: anotherAnimal is not a Cat instance.");
        }

        // Example of unsafe downcasting (will cause ClassCastException at runtime)
        Animal genericAnimal = new Animal("Bird"); // This Animal reference points to an Animal object, not a Cat
        System.out.println("\nAttempting unsafe downcasting (will cause runtime error if uncommented):");
        try {
            // Cat brokenCat = (Cat) genericAnimal; // This line would throw ClassCastException
            // brokenCat.meow();
            System.out.println("Unsafe downcast avoided by try-catch for demonstration.");
        } catch (ClassCastException e) {
            System.out.println("Caught ClassCastException: " + e.getMessage());
        }
    }
}
