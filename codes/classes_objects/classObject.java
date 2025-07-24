package Codes.classes_objects;


// Class: A blueprint for creating objects.
class Dog {
    // Fields (State/Data): Instance variables that define the object's attributes.
    String name;
    String breed;
    int age;

    // Constructor: Special method to initialize a new object.
    // It has the same name as the class and no return type.
    public Dog(String name, String breed, int age) {
        this.name = name;   // 'this' refers to the current object's instance variable
        this.breed = breed;
        this.age = age;
        System.out.println("A new Dog object has been created: " + name);
    }

    // Method (Behavior): Defines what the object can do.
    public void bark() {
        System.out.println(name + " says Woof! Woof!");
    }

    // Method to display dog's details
    public void displayInfo() {
        System.out.println("Name: " + name + ", Breed: " + breed + ", Age: " + age);
    }
}

public class classObject {
    public static void main(String[] args){
        System.out.println("--- Classes and Objects Demonstration ---");

        // Object Creation Process:
        // 1. Declaration: Declares a reference variable 'myDog' of type Dog.
        Dog myDog;

        // 2. Instantiation: 'new Dog(...)' allocates memory on the Heap for a new Dog object.
        //    Instance variables are initialized to default values (null for String, 0 for int).
        // 3. Constructor Invocation: The Dog(String, String, int) constructor is called.
        //    It initializes the instance variables 'name', 'breed', 'age'.
        // 4. Reference Assignment: The memory address of the new object is assigned to 'myDog'.
        myDog = new Dog("Buddy", "Golden Retriever", 3);

        // Accessing object's state (fields)
        System.out.println("\nAccessing Object State:");
        System.out.println("My dog's name: " + myDog.name);
        System.out.println("My dog's breed: " + myDog.breed);
        myDog.age = 4; // Modifying object's state
        System.out.println("My dog's new age: " + myDog.age);

        // Invoking object's behavior (methods)
        System.out.println("\nInvoking Object Behavior:");
        myDog.bark();
        myDog.displayInfo();

        // Creating another object of the same class
        Dog anotherDog = new Dog("Lucy", "Labrador", 2);
        anotherDog.bark();
        anotherDog.displayInfo();

        // Demonstrating object identity (memory address)
        System.out.println("\nObject Identity:");
        System.out.println("myDog object hash code: " + myDog.hashCode());
        System.out.println("anotherDog object hash code: " + anotherDog.hashCode());
        System.out.println("Are myDog and anotherDog the same object? " + (myDog == anotherDog)); // False, they are distinct objects
    }
}
