package codes.control_statements;

// Demonstrates branching (jump) statements:
// break, continue, and return.
public class branching {

    public static void main(String[] args) {
        System.out.println("--- Branching Statements Demonstration ---");

        // 1. break Statement
        System.out.println("\n--- 1. break Statement (in a loop) ---");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println("Breaking loop at i = " + i);
                break; // Terminates the loop immediately
            }
            System.out.println("Current value of i: " + i);
        }

        System.out.println("\n--- 1. break Statement (in a switch) ---");
        int choice = 2;
        switch (choice) {
            case 1:
                System.out.println("Case 1 selected.");
                break;
            case 2:
                System.out.println("Case 2 selected.");
                break; // Prevents fall-through to Case 3
            case 3:
                System.out.println("Case 3 selected.");
                break;
            default:
                System.out.println("Invalid choice.");
        }

        // 2. continue Statement
        System.out.println("\n--- 2. continue Statement ---");
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                System.out.println("Skipping iteration at i = " + i);
                continue; // Skips the rest of the current iteration
            }
            System.out.println("Processing i: " + i);
        }

        // 3. return Statement
        System.out.println("\n--- 3. return Statement ---");
        int result = addNumbers(10, 5);
        System.out.println("Result of addNumbers: " + result);

        System.out.println("Calling method that might return early:");
        checkAndReturn(7);
        checkAndReturn(12);

        System.out.println("End of main method."); // This line will not be reached if checkAndReturn(12) causes early return
    }

    // Method demonstrating 'return' to send a value back
    public static int addNumbers(int a, int b) {
        int sum = a + b;
        return sum; // Returns the sum and exits the method
    }

    // Method demonstrating 'return' to exit early
    public static void checkAndReturn(int num) {
        if (num > 10) {
            System.out.println("Number " + num + " is too large. Exiting method early.");
            return; // Exits the method immediately
        }
        System.out.println("Number " + num + " is acceptable. Continuing method execution.");
    }
}
