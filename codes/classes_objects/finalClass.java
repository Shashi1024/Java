package Codes.classes_objects;

// Demonstrates a final class, which cannot be subclassed (inherited from).

// Final Class: Declared with the 'final' keyword.
// No other class can extend this class.
final class ImmutablePoint {
    private final int x; // Fields are often final in final classes
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // All methods in a final class are implicitly final (cannot be overridden)
    // even if not explicitly marked 'final'.
    public String getLocation() {
        return "(" + x + ", " + y + ")";
    }
}

// Uncommenting the following class would cause a compile-time error:
/*
class ColoredPoint extends ImmutablePoint { // Compile-time error: cannot inherit from final class
    String color;
    public ColoredPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }
}
*/

public class finalClass {
    public static void main(String[] args) {
        System.out.println("--- Final Class Demonstration ---");

        ImmutablePoint point = new ImmutablePoint(10, 20);
        System.out.println("Point location: " + point.getLocation());
        System.out.println("X coordinate: " + point.getX());

        // You cannot change the state of an ImmutablePoint object after creation
        // point.x = 30; // Compile-time error if x was not final, but it is private anyway.
        // Even if x was public, it would be final, so no reassignment.

        // The primary purpose of a final class is to prevent extension,
        // often used for security or to ensure immutability.
        System.out.println("\n'ImmutablePoint' is a final class, meaning it cannot be extended.");
        System.out.println("This ensures its behavior and structure remain consistent.");
    }
}
