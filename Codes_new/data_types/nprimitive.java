package Codes_new.data_types;


// Demonstrates non-primitive (reference) data types: classes, interfaces, and arrays.

// Define a simple class
class Dog {
    String name;
    String breed;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public void bark() {
        System.out.println(name + " the " + breed + " barks!");
    }
}


interface Swimmable {
    void swim(); // Abstract method
}

// Implement the interface in a class
class Duck implements Swimmable {
    String name;

    public Duck(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming.");
    }

    public void quack() {
        System.out.println(name + " quacks!");
    }
}


public class nprimitive {
    public static void main(String[] args){
        System.out.println("--- Non-Primitive (Reference) Data Types Demonstration ---");

        // 1. Class (Object)
        // Declaring a reference variable 'myDog' of type Dog
        // Creating an object of Dog using 'new' keyword and assigning its reference to myDog
        Dog myDog = new Dog("Buddy", "Golden Retriever");
        System.out.println("\nClass (Object) Example:");
        System.out.println("Dog's name: " + myDog.name);
        myDog.bark(); // Calling a method associated with the object

        // Another reference variable pointing to null initially
        Dog anotherDog = null;
        System.out.println("Another dog reference (initially null): " + anotherDog);
        // anotherDog.bark(); // This would cause a NullPointerException if uncommented

        // 2. Interface
        // Declaring a reference variable 'mySwimmer' of type Swimmable
        // Assigning an object of a class that implements Swimmable
        Swimmable mySwimmer = new Duck("Daffy");
        System.out.println("\nInterface Example:");
        mySwimmer.swim(); // Calling the method defined in the interface

        // You can cast back to Duck if you know it's a Duck instance
        if (mySwimmer instanceof Duck) {
            Duck myDuck = (Duck) mySwimmer;
            myDuck.quack(); // Calling a method specific to Duck
        }


        // 3. Array
        // Array of primitive type (int)
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("\nArray of Primitives Example:");
        System.out.println("First number in array: " + numbers[0]);
        System.out.println("Length of numbers array: " + numbers.length);

        // Array of non-primitive type (String)
        String[] fruits = new String[3]; // Declaring an array of String references
        fruits[0] = "Apple";
        fruits[1] = "Banana";
        fruits[2] = "Cherry";
        System.out.println("\nArray of Non-Primitives (Strings) Example:");
        System.out.println("First fruit: " + fruits[0]);
        System.out.println("Second fruit: " + fruits[1]);
        System.out.println("Third fruit: " + fruits[2]);

        // Array of custom objects (Dog)
        Dog[] kennel = new Dog[2];
        kennel[0] = new Dog("Max", "German Shepherd");
        kennel[1] = new Dog("Lucy", "Beagle");
        System.out.println("\nArray of Custom Objects (Dogs) Example:");
        kennel[0].bark();
        kennel[1].bark();
    }
}
