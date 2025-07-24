package Codes;

import java.util.EnumSet;
import java.util.EnumMap;
import java.util.Map;

// 1. Basic Enum Declaration
// An enum representing days of the week.
// Semicolon is optional here as there are no other members.
enum DayOfWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

// 2. Enum with Fields, Constructors, and Methods
// Represents different levels of a game, each with associated difficulty and score multiplier.
enum GameLevel {
    EASY(1, "Beginner", 1.0),
    MEDIUM(2, "Intermediate", 1.5),
    HARD(3, "Challenging", 2.0),
    EXPERT(4, "Master", 3.0); // Semicolon is required if other members follow

    private final int levelNumber; // Instance field for each enum constant
    private final String description;
    private final double scoreMultiplier;

    // Constructor for the enum. Implicitly private.
    // Called when the enum constants (EASY, MEDIUM, etc.) are declared above.
    private GameLevel(int levelNumber, String description, double scoreMultiplier) {
        this.levelNumber = levelNumber;
        this.description = description;
        this.scoreMultiplier = scoreMultiplier;
        System.out.println("  GameLevel constant initialized: " + name());
    }

    // Instance methods to access the fields
    public int getLevelNumber() {
        return levelNumber;
    }

    public String getDescription() {
        return description;
    }

    public double getScoreMultiplier() {
        return scoreMultiplier;
    }

    // Overriding toString() for better representation
    @Override
    public String toString() {
        return name() + " (" + description + ", Multiplier: " + scoreMultiplier + "x)";
    }

    // Static method (can be added to enums)
    public static void printAllLevels() {
        System.out.println("\n--- All Game Levels ---");
        for (GameLevel level : GameLevel.values()) {
            System.out.println(level); // Uses the overridden toString()
        }
    }
}

public class EnumDemo {

    public static void main(String[] args) {
        System.out.println("--- Enum Demonstration ---");

        // Using basic enum constants
        DayOfWeek today = DayOfWeek.WEDNESDAY;
        System.out.println("\nToday is: " + today);

        // 3. Enum-specific methods: name() and ordinal()
        System.out.println("\n--- Enum Methods (name() and ordinal()) ---");
        System.out.println("DayOfWeek.MONDAY name(): " + DayOfWeek.MONDAY.name());
        System.out.println("DayOfWeek.MONDAY ordinal(): " + DayOfWeek.MONDAY.ordinal()); // Position (0-indexed)

        System.out.println("GameLevel.HARD name(): " + GameLevel.HARD.name());
        System.out.println("GameLevel.HARD ordinal(): " + GameLevel.HARD.ordinal());

        // 4. Using enums with fields and methods
        System.out.println("\n--- Enums with Fields and Methods ---");
        GameLevel currentLevel = GameLevel.MEDIUM;
        System.out.println("Current Game Level: " + currentLevel.getDescription());
        System.out.println("Level Number: " + currentLevel.getLevelNumber());
        System.out.println("Score Multiplier: " + currentLevel.getScoreMultiplier());

        // Using the overridden toString()
        System.out.println("Current Level (toString): " + currentLevel);

        // 5. values() method: Returns an array of all enum constants
        System.out.println("\n--- Iterating through Enum Constants (values()) ---");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println("Day: " + day + ", Ordinal: " + day.ordinal());
        }

        GameLevel.printAllLevels(); // Calling static method from enum

        // 6. valueOf() method: Converts a String to an enum constant
        System.out.println("\n--- Converting String to Enum (valueOf()) ---");
        try {
            DayOfWeek parsedDay = DayOfWeek.valueOf("FRIDAY");
            System.out.println("Parsed Day: " + parsedDay);

            GameLevel parsedLevel = GameLevel.valueOf("EXPERT");
            System.out.println("Parsed Level: " + parsedLevel.getDescription());

            // This will throw an IllegalArgumentException
            // DayOfWeek invalidDay = DayOfWeek.valueOf("FUNDAY");
            // System.out.println("Invalid Day: " + invalidDay);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid enum constant name provided to valueOf().");
        }

        // 7. Enum in switch Statements (both old and new syntax)
        System.out.println("\n--- Enum in switch Statements ---");

        // Pre-Java 12 switch statement
        DayOfWeek favoriteDay = DayOfWeek.SATURDAY;
        System.out.println("Pre-Java 12 switch for " + favoriteDay + ":");
        switch (favoriteDay) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println("It's a weekday.");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("It's the weekend!");
                break;
        }

        // Java 12+ switch expression (requires --enable-preview or Java 14+)
        GameLevel selectedLevel = GameLevel.HARD;
        String levelMessage = switch (selectedLevel) {
            case EASY -> "Relaxed gameplay.";
            case MEDIUM -> "Moderate challenge.";
            case HARD -> "Tough challenge, good luck!";
            case EXPERT -> {
                System.out.println("  (Inside switch expression block for EXPERT)");
                yield "Extreme difficulty, for pros only!"; // Use yield to return value from block
            }
        };
        System.out.println("Java 12+ switch for " + selectedLevel + ": " + levelMessage);


        // 8. Specialized Collections for Enums (EnumSet and EnumMap)
        System.out.println("\n--- EnumSet and EnumMap ---");

        // EnumSet: A high-performance Set implementation for enum types.
        EnumSet<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        System.out.println("Weekend days: " + weekend);
        System.out.println("Is MONDAY in weekend? " + weekend.contains(DayOfWeek.MONDAY));

        // EnumMap: A specialized Map implementation for enum keys.
        EnumMap<GameLevel, String> levelTips = new EnumMap<>(GameLevel.class);
        levelTips.put(GameLevel.EASY, "Focus on learning controls.");
        levelTips.put(GameLevel.HARD, "Be prepared for intense combat.");
        System.out.println("Level Tips: " + levelTips);
        System.out.println("Tip for MEDIUM level: " + levelTips.get(GameLevel.MEDIUM)); // null if not present
    }
}
