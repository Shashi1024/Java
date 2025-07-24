package Codes_new.data_types;

// Demonstrates different types of variables in Java: local, instance, and static (class) variables,
// along with the 'final' keyword.

class Product {
    // Instance Variables (Non-static fields)
    // These belong to an object and get default values if not initialized
    String name;
    double price;
    boolean inStock; // Default: false

    // Class Variable (Static field)
    // This belongs to the class, shared by all objects, and loaded when the class is loaded
    static String companyName = "Global Corp";
    static int totalProductsSold = 0;

    // Constructor to initialize instance variables
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.inStock = true; // Explicitly initialized
        totalProductsSold++; // Increment static variable each time a product is created
    }

    // Instance method to display product details
    public void displayProductInfo() {
        // Local variable 'status' declared inside the method
        String status;
        if (inStock) {
            status = "In Stock";
        } else {
            status = "Out of Stock";
        }
        // Local variables must be initialized before use, otherwise compile error.
        // System.out.println(uninitializedLocal); // Compile-time error if uncommented

        System.out.println("Product: " + name + ", Price: $" + price + ", Status: " + status);
        System.out.println("Company: " + companyName); // Can access static variable from instance method
    }

    // Static method to display total products sold
    public static void displayCompanyStats() {
        // System.out.println("Product name: " + name); // Compile-time error: cannot access instance variable from static method
        System.out.println("Total products sold by " + companyName + ": " + totalProductsSold);
    }
}

public class variables {
    final static double PI = 3.14159;
    public static void main(String[] args){
        System.out.println("--- Variables Demonstration ---");

        // 1. Local Variables
        // Declared inside the main method (a block), must be initialized before use
        int quantity = 5;
        String message = "Welcome!";
        System.out.println("\nLocal Variables:");
        System.out.println("Quantity: " + quantity);
        System.out.println("Message: " + message);

        // The 'final' keyword for local variables
        final int MAX_ATTEMPTS = 3;
        System.out.println("Max attempts (final local): " + MAX_ATTEMPTS);
        // MAX_ATTEMPTS = 5; // Compile-time error: cannot reassign a final variable

        // 2. Instance Variables
        // Create objects to access instance variables
        Product laptop = new Product("Laptop Pro", 1200.00);
        Product mouse = new Product("Wireless Mouse", 25.50);

        System.out.println("\nInstance Variables:");
        System.out.println("Laptop Name: " + laptop.name);
        System.out.println("Laptop Price: $" + laptop.price);
        System.out.println("Laptop In Stock: " + laptop.inStock); // Accessing boolean instance variable

        System.out.println("Mouse Name: " + mouse.name);
        System.out.println("Mouse Price: $" + mouse.price);
        mouse.inStock = false; // Modifying an instance variable
        System.out.println("Mouse In Stock (after change): " + mouse.inStock);

        laptop.displayProductInfo();
        mouse.displayProductInfo();

        // 3. Class Variables (Static Variables)
        // Accessed using the class name
        System.out.println("\nClass (Static) Variables:");
        System.out.println("Company Name (via class): " + Product.companyName);
        System.out.println("Total Products Sold (via class): " + Product.totalProductsSold);

        // Can also be accessed via object reference, but not recommended for clarity
        System.out.println("Company Name (via object - not recommended): " + laptop.companyName);

        // Modifying a static variable affects all instances
        Product.companyName = "New Global Tech";
        System.out.println("Company Name (after change): " + Product.companyName);
        laptop.displayProductInfo(); // Shows updated company name

        Product.displayCompanyStats(); // Calling static method

        // Final static variable
        System.out.println("\nFinal Static Variable:");
        System.out.println("Value of PI: " + PI);
        // PI = 3.14; // Compile-time error: cannot reassign a final variable

        // Final reference variable
        final Product limitedEdition = new Product("Limited Edition Gadget", 500.00);
        System.out.println("\nFinal Reference Variable:");
        System.out.println("Limited Edition Product: " + limitedEdition.name);
        // limitedEdition = new Product("Another Gadget", 600.00); // Compile-time error: cannot reassign final reference

        // However, the object's state can be changed
        limitedEdition.price = 450.00;
        System.out.println("Limited Edition Product (price changed): $" + limitedEdition.price);
    }
}
