package codes.classes_objects;

// Demonstrates an abstract class, its abstract and concrete methods,
// and how a concrete subclass must implement abstract methods.

// Abstract class: Cannot be instantiated directly.
// It serves as a blueprint for other classes.
abstract class Shape {
    String color;

    // Constructor in an abstract class
    public Shape(String color) {
        this.color = color;
        System.out.println("Shape constructor called. Color: " + color);
    }

    // Abstract method: Has no body, must be implemented by concrete subclasses.
    // Declared with 'abstract' keyword and ends with a semicolon.
    public abstract double calculateArea();

    // Concrete method: Has an implementation.
    public void displayColor() {
        System.out.println("This shape is " + color);
    }

    // Final method: Cannot be overridden by subclasses.
    public final void printShapeType() {
        System.out.println("This is a generic Shape.");
    }

    // Static method in an abstract class
    public static void describeShapes() {
        System.out.println("Shapes are fundamental geometric figures.");
    }
}

// Concrete subclass: Extends the abstract class and must implement all abstract methods.
class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        super(color); // Call to abstract superclass constructor
        this.radius = radius;
        System.out.println("Circle object created with radius: " + radius);
    }

    // Implementing the abstract method from Shape
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Specific method for Circle
    public void displayRadius() {
        System.out.println("Circle radius: " + radius);
    }
}

// Another concrete subclass
class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
        System.out.println("Rectangle object created with length " + length + " and width " + width);
    }

    // Implementing the abstract method from Shape
    @Override
    public double calculateArea() {
        return length * width;
    }
}

// An abstract subclass: Does not have to implement all abstract methods of its parent,
// but must itself be declared abstract.
abstract class ThreeDShape extends Shape {
    public ThreeDShape(String color) {
        super(color);
    }
    // ThreeDShape does not implement calculateArea(), so it must be abstract.
    // It might introduce its own abstract methods, e.g., calculateVolume().
    public abstract double calculateVolume();
}

public class abstractClass {
    public static void main(String[] args) {
        System.out.println("--- Abstract Class Demonstration ---");

        // Shape s = new Shape("Red"); // Compile-time error: Cannot instantiate abstract class Shape

        // Create objects of concrete subclasses
        Circle circle = new Circle("Blue", 5.0);
        System.out.println("Circle Area: " + circle.calculateArea());
        circle.displayColor();
        circle.displayRadius();
        circle.printShapeType(); // Calling final method from abstract class

        System.out.println();

        Rectangle rectangle = new Rectangle("Green", 4.0, 6.0);
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        rectangle.displayColor();
        rectangle.printShapeType();

        // Calling static method of abstract class
        Shape.describeShapes();
    }
}
