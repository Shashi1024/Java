package Codes_new.data_types;

public class wrapper {
    public static void main(String[] args){
        System.out.println("--- Wrapper Classes, Autoboxing & Unboxing Demonstration ---");

        // 1. Primitive to Wrapper Object (Autoboxing)
        int primitiveInt = 100;
        Integer integerObject = primitiveInt; // Autoboxing: int to Integer
        System.out.println("\nAutoboxing:");
        System.out.println("Primitive int: " + primitiveInt);
        System.out.println("Wrapper Integer object: " + integerObject);

        double primitiveDouble = 99.99;
        Double doubleObject = primitiveDouble; // Autoboxing: double to Double
        System.out.println("Primitive double: " + primitiveDouble);
        System.out.println("Wrapper Double object: " + doubleObject);

        boolean primitiveBoolean = true;
        Boolean booleanObject = primitiveBoolean; // Autoboxing: boolean to Boolean
        System.out.println("Primitive boolean: " + primitiveBoolean);
        System.out.println("Wrapper Boolean object: " + booleanObject);

        char primitiveChar = 'X';
        Character characterObject = primitiveChar; // Autoboxing: char to Character
        System.out.println("Primitive char: " + primitiveChar);
        System.out.println("Wrapper Character object: " + characterObject);

        // 2. Wrapper Object to Primitive (Unboxing)
        Integer anotherIntegerObject = 250;
        int unboxedInt = anotherIntegerObject; // Unboxing: Integer to int
        System.out.println("\nUnboxing:");
        System.out.println("Wrapper Integer object: " + anotherIntegerObject);
        System.out.println("Unboxed int: " + unboxedInt);

        Double anotherDoubleObject = 123.45;
        double unboxedDouble = anotherDoubleObject; // Unboxing: Double to double
        System.out.println("Wrapper Double object: " + anotherDoubleObject);
        System.out.println("Unboxed double: " + unboxedDouble);

        // 3. Using Wrapper Classes with Collections (which only store objects)
        // (Conceptual example, full collection usage is a separate topic)
        // List<Integer> numbers = new ArrayList<>();
        // numbers.add(10); // Autoboxes 10 (int) to Integer
        // numbers.add(20);
        // int firstNum = numbers.get(0); // Unboxes Integer to int

        System.out.println("\nWrapper Classes provide utility methods:");
        // Example of a utility method from Integer class
        String intAsString = Integer.toString(500);
        System.out.println("Integer 500 as String: " + intAsString);

        int parsedInt = Integer.parseInt("789");
        System.out.println("Parsed int from String '789': " + parsedInt);

        // Wrapper classes can hold null, unlike primitives
        Integer nullableInt = null;
        System.out.println("Nullable Integer: " + nullableInt);
        // int attemptUnboxNull = nullableInt; // This would throw NullPointerException at runtime
    }
}
