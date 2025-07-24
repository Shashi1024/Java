package Codes.classes_objects;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

// Demonstrates how to create an immutable class in Java.
// An immutable object's state cannot be changed after it's created.

final class ImmutablePerson { // 1. Declare the class as final
    private final String name; // 2. Declare all fields as private and final
    private final int age;
    private final Date birthDate; // Mutable object field
    private final List<String> hobbies; // Mutable collection field

    // 3. Initialize all fields via constructor
    public ImmutablePerson(String name, int age, Date birthDate, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // 4. Perform deep copy for mutable object fields in the constructor
        this.birthDate = new Date(birthDate.getTime()); // Deep copy of Date
        this.hobbies = new ArrayList<>(hobbies); // Deep copy of List
        System.out.println("ImmutablePerson object created: " + name);
    }

    // 5. No setter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 6. Perform deep copy for mutable object fields in getter methods
    public Date getBirthDate() {
        return new Date(this.birthDate.getTime()); // Return a defensive copy
    }

    public List<String> getHobbies() {
        return new ArrayList<>(this.hobbies); // Return a defensive copy
    }

    @Override
    public String toString() {
        return "ImmutablePerson [name=" + name + ", age=" + age + ", birthDate=" + birthDate + ", hobbies=" + hobbies + "]";
    }
}

public class immutableClass {
    public static void main(String[] args) {
        System.out.println("--- Immutable Class Demonstration ---");

        // Create mutable objects to pass to the immutable class constructor
        Date dob = new Date();
        List<String> initialHobbies = new ArrayList<>();
        initialHobbies.add("Reading");
        initialHobbies.add("Hiking");

        ImmutablePerson person = new ImmutablePerson("Alice", 30, dob, initialHobbies);
        System.out.println("Initial person: " + person);

        // Attempt to modify the state of the person object (will fail as it's immutable)
        // person.name = "Bob"; // Compile-time error: private final field
        // person.setAge(31); // Compile-time error: no setter method

        // Demonstrate that modifying the *original* mutable objects used for construction
        // does NOT affect the ImmutablePerson's internal state due to deep copies.
        dob.setYear(90); // Modify the original Date object
        initialHobbies.add("Swimming"); // Modify the original List

        System.out.println("\nAfter attempting external modifications:");
        System.out.println("Original DOB object (modified): " + dob);
        System.out.println("Original Hobbies list (modified): " + initialHobbies);
        System.out.println("Person object (should remain unchanged): " + person);

        // Demonstrate that modifying the *returned* mutable objects from getters
        // does NOT affect the ImmutablePerson's internal state due to deep copies.
        Date retrievedDob = person.getBirthDate();
        retrievedDob.setYear(80); // Modify the retrieved Date object

        List<String> retrievedHobbies = person.getHobbies();
        retrievedHobbies.add("Coding"); // Modify the retrieved List

        System.out.println("\nAfter modifying retrieved mutable objects:");
        System.out.println("Retrieved DOB object (modified): " + retrievedDob);
        System.out.println("Retrieved Hobbies list (modified): " + retrievedHobbies);
        System.out.println("Person object (should remain unchanged): " + person);

        System.out.println("\nAn immutable object's state is guaranteed to be constant after creation.");
    }
}
