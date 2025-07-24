package Codes_new.data_types;

public class primitive {
    public static void main(String[] args){
        byte myByte = 127; // 8-bit signed integer, range -128 to 127
        short myShort = 32767; // 16-bit signed integer, range -32,768 to 32,767
        int myInt = 2147483647; // 32-bit signed integer, default for whole numbers
        long myLong = 9223372036854775807L; // 64-bit signed integer, requires 'L' suffix for literals

        // Floating-point types
        float myFloat = 3.14159f; // 32-bit floating-point, requires 'f' suffix for literals
        double myDouble = 2.718281828459045; // 64-bit floating-point, default for decimal numbers

        // Character type
        char myChar = 'Z'; // 16-bit Unicode character
        char unicodeChar = '\u0041'; // 'A' using Unicode escape sequence

        // Boolean type
        boolean myBoolean = true; // Represents true or false

        System.out.println("--- Primitive Data Types Demonstration ---");
        System.out.println("byte: " + myByte);
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long: " + myLong);
        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble);
        System.out.println("char: " + myChar + " (Unicode: " + unicodeChar + ")");
        System.out.println("boolean: " + myBoolean);
    }
}
