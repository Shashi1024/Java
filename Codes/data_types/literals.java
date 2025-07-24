package Codes_new.data_types;

public class literals {
    public static void main(String[] args){
        System.out.println("--- Literals Demonstration ---");

        // Integer Literals
        int decimalLiteral = 100;         // Decimal (base 10)
        int octalLiteral = 0144;          // Octal (base 8) - starts with 0
        int hexLiteral = 0x64;            // Hexadecimal (base 16) - starts with 0x
        int binaryLiteral = 0b01100100;   // Binary (base 2) - starts with 0b
        long longLiteral = 123_456_789_012L; // Long literal with underscores for readability

        System.out.println("\nInteger Literals:");
        System.out.println("Decimal: " + decimalLiteral);
        System.out.println("Octal: " + octalLiteral);
        System.out.println("Hexadecimal: " + hexLiteral);
        System.out.println("Binary: " + binaryLiteral);
        System.out.println("Long with underscores: " + longLiteral);

        // Floating-Point Literals
        double doubleLiteral = 3.14159;    // Default is double
        float floatLiteral = 2.718f;       // Explicit float with 'f' or 'F' suffix
        double scientificNotation = 1.23e-4; // Scientific notation (1.23 * 10^-4)

        System.out.println("\nFloating-Point Literals:");
        System.out.println("Double: " + doubleLiteral);
        System.out.println("Float: " + floatLiteral);
        System.out.println("Scientific Notation: " + scientificNotation);

        // Character Literals
        char singleChar = 'A';             // Single character in single quotes
        char escapeSequenceNewline = '\n'; // Newline escape sequence
        char escapeSequenceTab = '\t';     // Tab escape sequence
        char unicodeChar = '\u0042';       // Unicode representation for 'B'

        System.out.println("\nCharacter Literals:");
        System.out.println("Single character: " + singleChar);
        System.out.println("Escape sequence (newline): Before" + escapeSequenceNewline + "After");
        System.out.println("Escape sequence (tab): Before" + escapeSequenceTab + "After");
        System.out.println("Unicode character: " + unicodeChar);

        // String Literals
        String stringLiteral1 = "Hello, Java!"; // Sequence of characters in double quotes
        String stringLiteral2 = "Hello, Java!"; // Refers to the same object in String Pool
        String newStringObject = new String("Hello, Java!"); // Creates a new object, not from pool

        System.out.println("\nString Literals:");
        System.out.println("String 1: " + stringLiteral1);
        System.out.println("String 2: " + stringLiteral2);
        System.out.println("New String object: " + newStringObject);
        System.out.println("stringLiteral1 == stringLiteral2: " + (stringLiteral1 == stringLiteral2)); // true (String Pool optimization)
        System.out.println("stringLiteral1 == newStringObject: " + (stringLiteral1 == newStringObject)); // false (different objects)

        // Boolean Literals
        boolean trueLiteral = true;
        boolean falseLiteral = false;

        System.out.println("\nBoolean Literals:");
        System.out.println("True: " + trueLiteral);
        System.out.println("False: " + falseLiteral);

        // Null Literal
        String nullReference = null; // Can be assigned to any reference type
        // int nullPrimitive = null; // Compile-time error: cannot assign null to primitive type

        System.out.println("\nNull Literal:");
        System.out.println("Null reference: " + nullReference);
    }
}
