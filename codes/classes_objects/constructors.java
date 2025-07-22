package codes.classes_objects;

// Demonstrates different types of constructors (default, no-arg, parameterized, overloaded)
// and constructor chaining using this() and super().

class ParentVehicle {
    String parentType;

    // Parent's Static Initializer (executed when ParentVehicle class is loaded)
    static {
        System.out.println("ParentVehicle: Static Initializer Block executed.");
    }

    // Parent's Instance Initializer (executed before parent's constructor)
    {
        System.out.println("ParentVehicle: Instance Initializer Block executed.");
    }

    // Parent's No-Argument Constructor
    public ParentVehicle() {
        System.out.println("ParentVehicle: No-argument constructor called.");
        this.parentType = "Generic Vehicle";
    }

    // Parent's Parameterized Constructor
    public ParentVehicle(String type) {
        System.out.println("ParentVehicle: Parameterized constructor called with type: " + type);
        this.parentType = type;
    }
}

class ChildCar extends ParentVehicle {
    String model;
    int year;

    // Child's Static Initializer (executed when ChildCar class is loaded, after ParentVehicle's static)
    static {
        System.out.println("ChildCar: Static Initializer Block executed.");
    }

    // Child's Instance Initializer (executed before child's constructor)
    {
        System.out.println("ChildCar: Instance Initializer Block executed.");
    }

    // 1. No-Argument User-Defined Constructor
    public ChildCar() {
        // super() is implicitly called here if no super() or this() is present.
        // It would call ParentVehicle's no-argument constructor.
        System.out.println("ChildCar: No-argument constructor called.");
        this.model = "Unknown";
        this.year = 0;
    }

    // 2. Parameterized Constructor
    public ChildCar(String model, int year) {
        // Explicitly calling ParentVehicle's no-argument constructor
        super();
        System.out.println("ChildCar: Parameterized constructor (model, year) called.");
        this.model = model;
        this.year = year;
    }

    // 3. Overloaded Constructor with Constructor Chaining (using this())
    public ChildCar(String model, int year, String parentType) {
        // Call to another constructor in the same class (must be the first statement)
        this(model, year); // This will call ChildCar(String, int)
        System.out.println("ChildCar: Overloaded constructor (model, year, parentType) called.");
        // After this(model, year) executes, the super() call in that constructor will have already run.
        // We can then set the parentType (though it's usually set in the superclass constructor).
        // For demonstration, let's just print it.
        System.out.println("Parent type specified: " + parentType);
    }

    // 4. Constructor Chaining (using super() with arguments)
    public ChildCar(String model, int year, boolean isElectric) {
        // Explicitly calling ParentVehicle's parameterized constructor
        super("Electric Vehicle"); // Must be the first statement
        System.out.println("ChildCar: Parameterized constructor (model, year, isElectric) called.");
        this.model = model;
        this.year = year;
        System.out.println("Is Electric: " + isElectric);
    }

    public void displayCarInfo() {
        System.out.println("  Car Model: " + model + ", Year: " + year + ", Parent Type: " + parentType);
    }
}

public class constructors {
    public static void main(String[] args){
        System.out.println("--- Constructors and Chaining Demonstration ---");

        System.out.println("\nCreating ChildCar with no-arg constructor:");
        ChildCar car1 = new ChildCar(); // Calls ChildCar() -> ParentVehicle()
        car1.displayCarInfo();

        System.out.println("\nCreating ChildCar with (model, year) constructor:");
        ChildCar car2 = new ChildCar("Toyota Camry", 2023); // Calls ChildCar(String, int) -> super() -> ParentVehicle()
        car2.displayCarInfo();

        System.out.println("\nCreating ChildCar with (model, year, parentType) constructor (this() chaining):");
        ChildCar car3 = new ChildCar("Honda Civic", 2022, "Sedan Vehicle"); // Calls ChildCar(String, int, String) -> this(model, year) -> ParentVehicle()
        car3.displayCarInfo();

        System.out.println("\nCreating ChildCar with (model, year, isElectric) constructor (super() chaining):");
        ChildCar car4 = new ChildCar("Tesla Model 3", 2024, true); // Calls ChildCar(String, int, boolean) -> super("Electric Vehicle")
        car4.displayCarInfo();

        // Default Constructor (if no constructors are explicitly defined in a class, Java provides a public no-argument default constructor)
        // Example: If ChildCar had no constructors, `new ChildCar()` would use the default.
        // Since we have defined constructors, the default one is not provided.
    }
}
