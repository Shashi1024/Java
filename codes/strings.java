package codes;

public class strings {

    public static void main(String[] args) {
        System.out.println("--- Java Strings, StringBuilder, and StringBuffer Demonstration ---");

        // --- 1. String Creation: Literals vs. new keyword ---
        System.out.println("\n--- 1. String Creation ---");

        // Using String Literals: Checked in String Pool
        String s1 = "Hello";
        String s2 = "Hello"; // s2 refers to the same object as s1 from the String Pool

        // Using new keyword: Always creates a new object in Heap
        String s3 = new String("Hello");
        String s4 = new String("Hello"); // s4 is a new object, distinct from s1, s2, s3

        System.out.println("s1: \"" + s1 + "\", s2: \"" + s2 + "\", s3: \"" + s3 + "\", s4: \"" + s4 + "\"");
        System.out.println("s1 == s2 (literal vs literal from pool)? " + (s1 == s2)); // true (same object in pool)
        System.out.println("s1 == s3 (literal vs new)? " + (s1 == s3));     // false (different objects)
        System.out.println("s3 == s4 (new vs new)? " + (s3 == s4));         // false (different objects)

        // --- 2. String Immutability ---
        System.out.println("\n--- 2. String Immutability ---");
        String originalString = "Java";
        System.out.println("Original String: " + originalString + " (HashCode: " + originalString.hashCode() + ")");

        String concatenatedString = originalString.concat(" Programming");
        System.out.println("Concatenated String: " + concatenatedString + " (HashCode: " + concatenatedString.hashCode() + ")");
        System.out.println("Original String after concat (unchanged): " + originalString + " (HashCode: " + originalString.hashCode() + ")");
        System.out.println("Is originalString == concatenatedString? " + (originalString == concatenatedString)); // false

        String upperCaseString = originalString.toUpperCase();
        System.out.println("Uppercase String: " + upperCaseString + " (HashCode: " + upperCaseString.hashCode() + ")");
        System.out.println("Original String after toUpperCase (unchanged): " + originalString + " (HashCode: " + originalString.hashCode() + ")");
        System.out.println("Is originalString == upperCaseString? " + (originalString == upperCaseString)); // false

        // --- 3. String.hashCode() and equals() ---
        System.out.println("\n--- 3. String.hashCode() and equals() ---");
        String strA = "example";
        String strB = "example";
        String strC = new String("example");
        String strD = "another";

        System.out.println("strA: \"" + strA + "\", HashCode: " + strA.hashCode());
        System.out.println("strB: \"" + strB + "\", HashCode: " + strB.hashCode());
        System.out.println("strC: \"" + strC + "\", HashCode: " + strC.hashCode());
        System.out.println("strD: \"" + strD + "\", HashCode: " + strD.hashCode());

        System.out.println("strA.equals(strB)? " + strA.equals(strB)); // true (content is same)
        System.out.println("strA.equals(strC)? " + strA.equals(strC)); // true (content is same)
        System.out.println("strA.equals(strD)? " + strA.equals(strD)); // false (content is different)

        // --- 4. intern() Method ---
        System.out.println("\n--- 4. intern() Method ---");
        String newStringObj = new String("intern me"); // Object in heap, not in pool
        String internedString = newStringObj.intern(); // Adds to pool if not present, returns pool reference
        String literalString = "intern me"; // From pool

        System.out.println("newStringObj == literalString? " + (newStringObj == literalString)); // false
        System.out.println("internedString == literalString? " + (internedString == literalString)); // true (both now refer to the pool object)
        System.out.println("newStringObj == internedString? " + (newStringObj == internedString)); // false (newStringObj is still the original heap object)

        // --- 5. StringBuffer (Mutable, Synchronized, Slower) ---
        System.out.println("\n--- 5. StringBuffer (Mutable, Synchronized) ---");
        StringBuffer stringBuffer = new StringBuffer("Start");
        System.out.println("Initial StringBuffer: " + stringBuffer + " (Capacity: " + stringBuffer.capacity() + ")");

        stringBuffer.append("ing"); // Appends characters
        System.out.println("After append 'ing': " + stringBuffer + " (Capacity: " + stringBuffer.capacity() + ")");

        stringBuffer.insert(0, "Re"); // Inserts at index 0
        System.out.println("After insert 'Re' at 0: " + stringBuffer + " (Capacity: " + stringBuffer.capacity() + ")");

        stringBuffer.delete(2, 5); // Deletes characters from index 2 (inclusive) to 5 (exclusive)
        System.out.println("After delete(2,5): " + stringBuffer + " (Capacity: " + stringBuffer.capacity() + ")");

        stringBuffer.reverse(); // Reverses the sequence
        System.out.println("After reverse: " + stringBuffer + " (Capacity: " + stringBuffer.capacity() + ")");

        // Note: StringBuffer is thread-safe due to synchronized methods, but this adds overhead.

        // --- 6. StringBuilder (Mutable, Non-Synchronized, Faster) ---
        System.out.println("\n--- 6. StringBuilder (Mutable, Non-Synchronized) ---");
        StringBuilder stringBuilder = new StringBuilder("Hello");
        System.out.println("Initial StringBuilder: " + stringBuilder + " (Capacity: " + stringBuilder.capacity() + ")");

        stringBuilder.append(" World"); // Appends characters
        System.out.println("After append ' World': " + stringBuilder + " (Capacity: " + stringBuilder.capacity() + ")");

        stringBuilder.replace(6, 11, "Java"); // Replaces a substring
        System.out.println("After replace(6,11,'Java'): " + stringBuilder + " (Capacity: " + stringBuilder.capacity() + ")");

        stringBuilder.deleteCharAt(5); // Deletes character at index 5 (space)
        System.out.println("After deleteCharAt(5): " + stringBuilder + " (Capacity: " + stringBuilder.capacity() + ")");

        // Note: StringBuilder is faster than StringBuffer because its methods are not synchronized.
        // Use StringBuilder when thread-safety is not a concern (e.g., in single-threaded environments).

        System.out.println("\n--- Key Differences Summary ---");
        System.out.println("String: Immutable, uses String Pool for literals, thread-safe.");
        System.out.println("StringBuffer: Mutable, thread-safe (synchronized), generally slower.");
        System.out.println("StringBuilder: Mutable, NOT thread-safe (non-synchronized), generally faster.");
    }
}

