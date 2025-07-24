package Codes_new;

// Base class (Superclass)
class Animal {
    String name;
    int age;

    // Instance initializer for Animal
    {
        System.out.println("Animal: Instance initializer executed.");
    }

    // Static initializer for Animal
    static {
        System.out.println("Animal: Static initializer executed.");
    }

    // Constructor for Animal
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Animal: Constructor called for " + name);
    }

    // Method in superclass
    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    // Method to demonstrate Object class methods
    @Override
    public String toString() {
        return "Animal[name=" + name + ", age=" + age + "]";
    }
}

// 1. Single Inheritance: Dog inherits from Animal
class Dog extends Animal {
    String breed;

    // Instance initializer for Dog
    {
        System.out.println("Dog: Instance initializer executed.");
    }

    // Static initializer for Dog
    static {
        System.out.println("Dog: Static initializer executed.");
    }

    // Constructor for Dog
    public Dog(String name, int age, String breed) {
        // super(name, age) must be the first statement to call the superclass constructor
        super(name, age);
        this.breed = breed;
        System.out.println("Dog: Constructor called for " + name + " (Breed: " + breed + ")");
    }

    // New method specific to Dog
    public void bark() {
        System.out.println(name + " barks loudly!");
    }

    // Method Overriding: Dog provides its own implementation of eat()
    @Override
    public void eat() {
        System.out.println(name + " (a " + breed + ") is eating dog food.");
    }

    // Demonstrating super.methodName()
    public void performActivities() {
        System.out.println("\n--- " + name + " Activities ---");
        eat(); // Calls overridden eat() in Dog class
        super.eat(); // Calls eat() method from the Animal superclass
        sleep(); // Inherited from Animal
        bark(); // Specific to Dog
    }
}

// 2. Multilevel Inheritance: Puppy inherits from Dog, which inherits from Animal
class Puppy extends Dog {
    String favoriteToy;

    // Instance initializer for Puppy
    {
        System.out.println("Puppy: Instance initializer executed.");
    }

    // Static initializer for Puppy
    static {
        System.out.println("Puppy: Static initializer executed.");
    }

    // Constructor for Puppy
    public Puppy(String name, int age, String breed, String favoriteToy) {
        // Calls Dog's constructor, which in turn calls Animal's constructor
        super(name, age, breed);
        this.favoriteToy = favoriteToy;
        System.out.println("Puppy: Constructor called for " + name + " (Favorite Toy: " + favoriteToy + ")");
    }

    // New method specific to Puppy
    public void play() {
        System.out.println(name + " is playing with its " + favoriteToy + ".");
    }

    @Override
    public void bark() {
        System.out.println(name + " barks playfully!");
    }
}

// 3. Hierarchical Inheritance: Cat also inherits from Animal
class Cat extends Animal {
    String color;

    // Constructor for Cat
    public Cat(String name, int age, String color) {
        super(name, age);
        this.color = color;
        System.out.println("Cat: Constructor called for " + name + " (Color: " + color + ")");
    }

    // New method specific to Cat
    public void meow() {
        System.out.println(name + " the " + color + " cat says Meow!");
    }

    @Override
    public void eat() {
        System.out.println(name + " (a " + color + " cat) is eating cat food.");
    }
}


public class inheritance {

    public static void main(String[] args) {
        System.out.println("--- Java Inheritance Demonstration ---");

        System.out.println("\n--- 1. Single Inheritance (Dog extends Animal) ---");
        Dog myDog = new Dog("Buddy", 3, "Golden Retriever");
        myDog.eat(); // Calls Dog's overridden eat()
        myDog.sleep(); // Inherited from Animal
        myDog.bark(); // Specific to Dog
        myDog.performActivities(); // Demonstrates super.method()

        System.out.println("\n--- 2. Multilevel Inheritance (Puppy extends Dog) ---");
        Puppy myPuppy = new Puppy("Max", 1, "Beagle", "Squeaky Ball");
        myPuppy.eat(); // Inherited from Dog (which overrides Animal's eat)
        myPuppy.sleep(); // Inherited from Animal
        myPuppy.bark(); // Overridden in Puppy
        myPuppy.play(); // Specific to Puppy

        System.out.println("\n--- 3. Hierarchical Inheritance (Cat extends Animal) ---");
        Cat myCat = new Cat("Whiskers", 5, "Tabby");
        myCat.eat(); // Overridden in Cat
        myCat.sleep(); // Inherited from Animal
        myCat.meow(); // Specific to Cat

        // --- 4. The Object Class: The Root of All Classes ---
        System.out.println("\n--- 4. The Object Class ---");
        // Every class implicitly extends java.lang.Object
        Object obj1 = new Animal("Generic", 0);
        Object obj2 = new Dog("RootDog", 2, "Poodle");
        Object obj3 = new Cat("RootCat", 4, "Black");

        System.out.println("Animal object's toString(): " + obj1.toString());
        System.out.println("Dog object's toString(): " + obj2.toString()); // Dog inherits toString from Animal, which inherits from Object
        System.out.println("Cat object's toString(): " + obj3.toString());

        System.out.println("Class of obj1: " + obj1.getClass().getName());
        System.out.println("obj1 equals obj2? " + obj1.equals(obj2)); // Inherited from Object

        System.out.println("\n--- Order of Initialization (Class Loading & Object Creation) ---");
        System.out.println("Notice the order of static and instance initializers/constructors in the output above:");
        System.out.println("1. Parent Static Initializers (Animal)");
        System.out.println("2. Child Static Initializers (Dog, then Puppy)");
        System.out.println("3. Parent Instance Initializers (Animal)");
        System.out.println("4. Parent Constructor (Animal)");
        System.out.println("5. Child Instance Initializers (Dog)");
        System.out.println("6. Child Constructor (Dog)");
        System.out.println("7. Grandchild Instance Initializers (Puppy)");
        System.out.println("8. Grandchild Constructor (Puppy)");

        System.out.println("\n--- Java Does Not Support Multiple Inheritance for Classes ---");
        System.out.println("Java classes cannot extend more than one class directly (e.g., 'class C extends A, B' is forbidden).");
        System.out.println("This prevents the 'Diamond Problem' and simplifies the class hierarchy.");
        System.out.println("Multiple inheritance of *behavior* is achieved through Interfaces.");
    }
}

