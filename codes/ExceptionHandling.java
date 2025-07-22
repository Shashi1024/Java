package codes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException; // Used for demonstrating checked exceptions

// 1. Custom Checked Exception
// Extends Exception, so it must be handled or declared with 'throws'.
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// 2. Custom Unchecked Exception
// Extends RuntimeException, so it does not need to be handled or declared.
class InvalidInputRuntimeException extends RuntimeException {
    public InvalidInputRuntimeException(String message) {
        super(message);
    }
}

public class ExceptionHandling {

    public static void main(String[] args) {
        System.out.println("--- Exception Handling Demonstration ---");

        // --- 1. Basic try-catch block ---
        System.out.println("\n--- 1. Basic try-catch block (ArithmeticException) ---");
        try {
            int result = 10 / 0; // This will throw an ArithmeticException
            System.out.println("Result: " + result); // This line will not be executed
        } catch (ArithmeticException e) {
            System.out.println("Caught an ArithmeticException: " + e.getMessage());
        }
        System.out.println("Program continues after handling ArithmeticException.");

        // --- 2. Multiple catch blocks ---
        System.out.println("\n--- 2. Multiple catch blocks ---");
        String[] names = {"Alice", "Bob"};
        try {
            System.out.println("Name at index 2: " + names[2]); // ArrayIndexOutOfBoundsException
            String s = null;
            System.out.println("Length of null string: " + s.length()); // NullPointerException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException: " + e.getMessage());
        } catch (Exception e) { // Catching a more general exception (must be after specific ones)
            System.out.println("Caught a general Exception: " + e.getMessage());
        }
        System.out.println("Program continues after multiple catch blocks.");

        // --- 3. Multi-Catch (Java 7+) ---
        System.out.println("\n--- 3. Multi-Catch (Java 7+) ---");
        try {
            int[] numbers = {1, 2};
            System.out.println(numbers[5]); // ArrayIndexOutOfBoundsException
            // Integer.parseInt("abc"); // NumberFormatException
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Caught either ArrayIndexOutOfBoundsException or NumberFormatException: " + e.getMessage());
        }
        System.out.println("Program continues after multi-catch.");

        // --- 4. finally block ---
        System.out.println("\n--- 4. finally block ---");
        try {
            System.out.println("Inside try block.");
            int value = 10 / 2; // No exception
            System.out.println("Value: " + value);
        } catch (ArithmeticException e) {
            System.out.println("Inside catch block (will not execute).");
        } finally {
            System.out.println("Inside finally block (always executes).");
        }

        System.out.println("\n--- 4. finally block with exception ---");
        try {
            System.out.println("Inside try block (with exception).");
            int value = 10 / 0; // Exception occurs
            System.out.println("Value: " + value); // Not reached
        } catch (ArithmeticException e) {
            System.out.println("Inside catch block: " + e.getMessage());
        } finally {
            System.out.println("Inside finally block (executes after catch).");
        }

        System.out.println("\n--- 4. finally block and return ---");
        System.out.println("Method returns: " + demonstrateFinallyWithReturn());

        // --- 5. throw keyword ---
        System.out.println("\n--- 5. throw keyword (throwing an Unchecked Exception) ---");
        try {
            validateAge(5); // This will throw InvalidInputRuntimeException
            System.out.println("Age is valid.");
        } catch (InvalidInputRuntimeException e) {
            System.out.println("Caught custom unchecked exception: " + e.getMessage());
        }

        System.out.println("\n--- 5. throw keyword (throwing a Checked Exception) ---");
        try {
            performTransaction(50); // This will throw InsufficientFundsException
            System.out.println("Transaction successful.");
        } catch (InsufficientFundsException e) {
            System.out.println("Caught custom checked exception: " + e.getMessage());
        }

        // --- 6. throws keyword ---
        System.out.println("\n--- 6. throws keyword ---");
        try {
            // Calling a method that declares 'throws IOException'
            readFileContent("nonexistent.txt");
        } catch (IOException e) {
            System.out.println("Caught IOException when reading file: " + e.getMessage());
        }

        try {
            // Calling a method that declares 'throws SQLException'
            connectToDatabase();
        } catch (SQLException e) {
            System.out.println("Caught SQLException when connecting to database: " + e.getMessage());
        }

        // --- 7. try-with-resources (Java 7+) ---
        System.out.println("\n--- 7. try-with-resources ---");
        String dummyFilePath = "temp_data.txt";
        try {
            // Create a dummy file for demonstration
            java.nio.file.Files.write(java.nio.file.Paths.get(dummyFilePath), "Line 1\nLine 2".getBytes());
        } catch (IOException e) {
            System.err.println("Error creating dummy file: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dummyFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
            System.out.println("BufferedReader automatically closed.");
        } catch (IOException e) {
            System.out.println("Caught IOException during file read: " + e.getMessage());
        } finally {
            // Clean up the dummy file
            try {
                java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(dummyFilePath));
            } catch (IOException e) {
                System.err.println("Error deleting dummy file: " + e.getMessage());
            }
        }

        System.out.println("\n--- JVM's Role in Exception Handling (Conceptual) ---");
        System.out.println("When an exception occurs, the JVM:");
        System.out.println("1. Creates an exception object.");
        System.out.println("2. Unwinds the call stack, looking for a matching catch block.");
        System.out.println("3. Executes finally blocks during unwinding.");
        System.out.println("4. If no handler is found, terminates the program with a stack trace.");

        System.out.println("\nDemonstration complete.");
    }

    // --- Helper Methods for Demonstration ---

    // Method to demonstrate finally block behavior with return
    public static int demonstrateFinallyWithReturn() {
        try {
            System.out.println("  Inside demonstrateFinallyWithReturn try block.");
            return 10; // This return is "interrupted" by finally
        } finally {
            System.out.println("  Inside demonstrateFinallyWithReturn finally block.");
            // If uncommented, this return would override the 'return 10' from try.
            // return 20;
        }
    }

    // Method using 'throw' for a custom unchecked exception
    public static void validateAge(int age) {
        if (age < 0 || age > 120) {
            throw new InvalidInputRuntimeException("Age must be between 0 and 120.");
        }
        System.out.println("Age " + age + " is valid.");
    }

    // Method using 'throw' for a custom checked exception
    public static void performTransaction(double amount) throws InsufficientFundsException {
        double currentBalance = 100.0;
        if (amount > currentBalance) {
            throw new InsufficientFundsException("Insufficient funds. Required: " + amount + ", Available: " + currentBalance);
        }
        System.out.println("Transaction of $" + amount + " processed.");
    }

    // Method that declares 'throws IOException'
    public static void readFileContent(String fileName) throws IOException {
        System.out.println("Attempting to read file: " + fileName);
        // This line will throw FileNotFoundException (a subclass of IOException)
        // if the file does not exist.
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        reader.readLine(); // Just read one line for simplicity
        reader.close();
        System.out.println("File read successfully.");
    }

    // Method that declares 'throws SQLException'
    public static void connectToDatabase() throws SQLException {
        System.out.println("Attempting to connect to database...");
        // Simulate a database connection error
        boolean connectionSuccessful = false;
        if (!connectionSuccessful) {
            throw new SQLException("Database connection failed: Could not establish connection.");
        }
        System.out.println("Database connected successfully.");
    }
}
