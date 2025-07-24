package Codes.Enum;

// we cannot have both enum and class as public because both are top level types
// we cannot have more than one top-level public type (class, enum, or interface) in a single Java file.

enum Day{
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static void main(String[] args){
        Day today = Day.SUNDAY;
        System.out.println("Today is " + today);

        for (Day day : Day.values()) {
            System.out.println(day + " is day number " + day.ordinal());
        }
    }
}

// the main method can be placed direclty inside the enum (main method can be placed inside a class, enum, interface)
// (can be placed in an abstract class also)


// public class SimpleEnum {
//     public static void main(String[] args){
//         Day today = Day.SUNDAY;
//         System.out.println("Today is " + today);

//         for (Day day : Day.values()) {
//             System.out.println(day + " is day number " + day.ordinal());
//         }
//     }
// }
