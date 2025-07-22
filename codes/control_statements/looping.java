package codes.control_statements;

// Demonstrates looping (iterative) statements:
// for loop, enhanced for loop (for-each), while loop, and do-while loop.
public class looping {

    public static void main(String[] args) {
        System.out.println("--- Looping Statements Demonstration ---");

        // 1. for Loop
        System.out.println("\n--- 1. for Loop (Counting Up) ---");
        for (int i = 0; i < 5; i++) {
            System.out.println("Count: " + i);
        }

        System.out.println("\n--- 1. for Loop (Counting Down) ---");
        for (int i = 5; i > 0; i--) {
            System.out.println("Countdown: " + i);
        }

        // 2. Enhanced for Loop (for-each loop)
        System.out.println("\n--- 2. Enhanced for Loop (for-each) ---");
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.print("Array elements: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        String[] fruits = {"Apple", "Banana", "Cherry"};
        System.out.println("Fruits:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        // 3. while Loop
        System.out.println("\n--- 3. while Loop ---");
        int count = 0;
        while (count < 3) {
            System.out.println("While loop iteration: " + count);
            count++;
        }

        // 4. do-while Loop
        System.out.println("\n--- 4. do-while Loop ---");
        // The do-while loop executes its body at least once, then checks the condition.
        int j = 0;
        do {
            System.out.println("Do-while loop iteration: " + j);
            j++;
        } while (j < 3);

        // Example where do-while runs once even if condition is false initially
        System.out.println("\n--- 4. do-while Loop (Condition initially false) ---");
        int k = 5;
        do {
            System.out.println("This will print once: " + k);
            k++;
        } while (k < 5); // Condition is false, but loop runs once
    }
}

