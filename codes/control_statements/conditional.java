package codes.control_statements;

// Demonstrates conditional (decision-making) statements:
// if, if-else, if-else-if ladder, and switch (both old and new syntax).
public class conditional {

    public static void main(String[] args) {
        System.out.println("--- Conditional Statements Demonstration ---");

        int temperature = 25;
        boolean isSunny = true;
        String dayOfWeek = "Wednesday";
        int month = 7; // July

        // 1. if Statement
        System.out.println("\n--- 1. if Statement ---");
        if (temperature > 20) {
            System.out.println("It's warm outside.");
        }

        // 2. if-else Statement
        System.out.println("\n--- 2. if-else Statement ---");
        if (isSunny) {
            System.out.println("It's a sunny day!");
        } else {
            System.out.println("It's not sunny.");
        }

        // 3. if-else-if Ladder
        System.out.println("\n--- 3. if-else-if Ladder ---");
        if (temperature < 0) {
            System.out.println("It's freezing!");
        } else if (temperature >= 0 && temperature <= 15) {
            System.out.println("It's cool.");
        } else if (temperature > 15 && temperature <= 25) {
            System.out.println("It's pleasant.");
        } else {
            System.out.println("It's hot!");
        }

        // 4. switch Statement (Pre-Java 12 Syntax)
        System.out.println("\n--- 4. switch Statement (Pre-Java 12 Syntax) ---");
        System.out.println("Day of week: " + dayOfWeek);
        switch (dayOfWeek) {
            case "Monday":
                System.out.println("Start of the week.");
                break; // Prevents fall-through
            case "Wednesday":
                System.out.println("Midweek.");
                // No break here to demonstrate fall-through (if not desired, add break)
            case "Friday":
                System.out.println("Almost weekend!");
                break;
            default:
                System.out.println("Regular day.");
                break;
        }

        // 5. switch Statement (Java 12+ Expression with Arrow Syntax and yield)
        // Requires Java 12+ and --enable-preview (or Java 14+ standard)
        System.out.println("\n--- 5. switch Statement (Java 12+ Expression) ---");
        String season = switch (month) {
            case 12, 1, 2 -> "Winter"; // Multiple case labels
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> { // Block for more complex logic
                String message = "It's summer!";
                System.out.println("  (Inside switch expression block: " + message + ")");
                yield "Summer"; // 'yield' returns the value from the block
            }
            case 9, 10, 11 -> "Autumn";
            default -> "Invalid Month";
        };
        System.out.println("Month " + month + " is in " + season + ".");

        // 6. switch Statement (Java 12+ Statement with Arrow Syntax)
        System.out.println("\n--- 6. switch Statement (Java 12+ Statement) ---");
        char grade = 'B';
        switch (grade) {
            case 'A' -> System.out.println("Excellent!");
            case 'B' -> {
                System.out.println("Very Good!");
                System.out.println("Keep up the great work.");
            }
            case 'C' -> System.out.println("Good.");
            default -> System.out.println("Needs Improvement.");
        }
    }
}
