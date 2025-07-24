package Codes.Enum;

enum Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}

public class EnumSwitch {
    public static void main(String[] args) {
        Season currentSeason = Season.SUMMER;

        switch (currentSeason) {
            case SPRING:
                System.out.println("It's blooming!");
                break;
            case SUMMER:
                System.out.println("It's hot and sunny!");
                break;
            case AUTUMN:
                System.out.println("Leaves are falling.");
                break;
            case WINTER:
                System.out.println("It's cold and snowy.");
                break;
            default:
                System.out.println("Unknown season.");
        }


        System.out.println("\n--- Using Enhanced Switch Expression ---");

        // Enhanced switch expression (Java 14+)
        String seasonDescription = switch (currentSeason) {
            case SPRING -> "It's blooming and fresh!";
            case SUMMER -> "It's hot, sunny, and perfect for vacation!";
            case AUTUMN -> "Leaves are falling and the air is crisp.";
            case WINTER -> "It's cold, snowy, and time for cozy nights.";
            // No default needed if all enum constants are covered
        };
        System.out.println("Enhanced switch description for " + currentSeason + ": " + seasonDescription);

    }
}