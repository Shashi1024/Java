package Codes_new.control_statements;

// Demonstrates labeled break and continue statements in Java,
// used to control outer loops in nested loop structures.
public class labeledStatements {

    public static void main(String[] args) {
        System.out.println("--- Labeled break and continue Demonstration ---");

        // 1. Labeled break
        System.out.println("\n--- 1. Labeled break ---");
        outerLoopBreak: // Label for the outer loop
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("Breaking outer loop when i=" + i + ", j=" + j);
                    break outerLoopBreak; // Breaks out of the 'outerLoopBreak'
                }
                System.out.println("i: " + i + ", j: " + j);
            }
        }
        System.out.println("After labeled break. Loop(s) terminated.");

        // 2. Labeled continue
        System.out.println("\n--- 2. Labeled continue ---");
        outerLoopContinue: // Label for the outer loop
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 1) {
                    System.out.println("Continuing outer loop when i=" + i + ", j=" + j);
                    continue outerLoopContinue; // Skips to the next iteration of 'outerLoopContinue'
                }
                System.out.println("i: " + i + ", j: " + j);
            }
        }
        System.out.println("After labeled continue. Loop(s) completed.");

        // Example: Using a label with a block (less common, but valid)
        System.out.println("\n--- 3. Labeled block with break ---");
        int x = 10;
        myBlock: {
            System.out.println("Inside myBlock - before if");
            if (x > 5) {
                System.out.println("Breaking out of myBlock because x > 5");
                break myBlock; // Exits the labeled block
            }
            System.out.println("Inside myBlock - after if (this won't print)");
        }
        System.out.println("Outside myBlock.");
    }
}

