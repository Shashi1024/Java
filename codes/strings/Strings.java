package codes.strings;

// Link to Notes --> .../Notes/Strings.md

public class Strings {
    public static void main(String[] args) {
        System.out.println("--- 1. String Creation & String Pool ---");

        String sLiteral1 = "hello";
        String sLiteral2 = "hello";
        String sLiteral3 = "world";

        System.out.println("sLiteral1: " + sLiteral1 + ", HashCode: " + sLiteral1.hashCode());
        System.out.println("sLiteral2: " + sLiteral2 + ", HashCode: " + sLiteral2.hashCode());
        System.out.println("sLiteral3: " + sLiteral3 + ", HashCode: " + sLiteral3.hashCode());

        System.out.println("sLiteral1 == sLiteral2 (same object in pool): " + (sLiteral1 == sLiteral2)); // true
        System.out.println("sLiteral1 == sLiteral3 (different objects): " + (sLiteral1 == sLiteral3));   // false
        System.out.println();

        String sNew1 = new String("java"); // creates new object on heap. "java" literal also in pool.
        String sNew2 = new String("java"); // other new object
        String sNew3 = "java";             // refers to the java literal in the pool (created above)

        System.out.println("sNew1: " + sNew1 + ", HashCode: " + sNew1.hashCode());
        System.out.println("sNew2: " + sNew2 + ", HashCode: " + sNew2.hashCode());
        System.out.println("sNew3: " + sNew3 + ", HashCode: " + sNew3.hashCode());

        System.out.println("sNew1 == sNew2 (different objects on heap): " + (sNew1 == sNew2)); // false
        System.out.println("sNew1 == sNew3 (heap vs. pool): " + (sNew1 == sNew3));             // false
        System.out.println("sNew1.equals(sNew3) (content comparison): " + (sNew1.equals(sNew3))); // true
        System.out.println();


        String sInterned = sNew1.intern(); // sInterned refers to the java object in the pool
        System.out.println("sNew1.intern() == sNew3 (sInterned now points to pool object): " + (sInterned == sNew3)); // true
        System.out.println("sNew1 == sInterned (original heap object vs. interned): " + (sNew1 == sInterned)); // false
        System.out.println();

        System.out.println("--- 2. String Immutability ---");
        String originalString = "immutable";
        System.out.println("Original String: " + originalString + ", HashCode: " + originalString.hashCode());

        String modifiedString = originalString.concat(" string"); // This creates a new String object
        System.out.println("Modified String (new object): " + modifiedString + ", HashCode: " + modifiedString.hashCode());
        System.out.println("Original String after concat: " + originalString); // Original remains unchanged
        System.out.println("originalString == modifiedString: " + (originalString == modifiedString)); // false
        System.out.println();

        System.out.println("--- 3. String.hashCode() Usage ---");
        // Hash code is based on content, not memory address for String
        String h1 = "example";
        String h2 = "example";
        String h3 = new String("example");

        System.out.println("h1: " + h1 + ", HashCode: " + h1.hashCode());
        System.out.println("h2: " + h2 + ", HashCode: " + h2.hashCode());
        System.out.println("h3: " + h3 + ", HashCode: " + h3.hashCode());
        System.out.println("Note: Hash codes are identical for strings with the same content.");
        System.out.println();

        System.out.println("--- 4. String Comparison (== vs. equals()) ---");
        String compare1 = "test";
        String compare2 = "test";
        String compare3 = new String("test");
        String compare4 = "TEST";

        System.out.println("compare1: " + compare1);
        System.out.println("compare2: " + compare2);
        System.out.println("compare3: " + compare3);
        System.out.println("compare4: " + compare4);

        System.out.println("compare1 == compare2 (same literal, same object): " + (compare1 == compare2)); // true
        System.out.println("compare1 == compare3 (literal vs. new object): " + (compare1 == compare3));     // false
        System.out.println("compare1.equals(compare2) (content): " + compare1.equals(compare2));           // true
        System.out.println("compare1.equals(compare3) (content): " + compare1.equals(compare3));           // true
        System.out.println("compare1.equals(compare4) (content, case-sensitive): " + compare1.equals(compare4)); // false
        System.out.println("compare1.equalsIgnoreCase(compare4) (content, case-insensitive): " + compare1.equalsIgnoreCase(compare4)); // true
        System.out.println();

        System.out.println("--- 5. StringBuffer and StringBuilder (Mutable Strings) ---");

        System.out.println("--- StringBuilder ---");
        StringBuilder sb = new StringBuilder("Initial");
        System.out.println("Initial StringBuilder: " + sb + ", Length: " + sb.length() + ", Capacity (approx): " + sb.capacity());

        sb.append(" text"); // Modifies the existing object
        System.out.println("After append: " + sb + ", Length: " + sb.length() + ", Capacity (approx): " + sb.capacity());

        sb.insert(7, " new"); // Inserts characters at index 7
        System.out.println("After insert: " + sb + ", Length: " + sb.length() + ", Capacity (approx): " + sb.capacity());

        sb.delete(0, 8); // Deletes characters from index 0 to 7 (exclusive)
        System.out.println("After delete: " + sb + ", Length: " + sb.length() + ", Capacity (approx): " + sb.capacity());

        // Demonstrate capacity growth (might vary based on JVM implementation)
        sb.append("This is a very long string that will likely cause the StringBuilder to resize its internal character array.");
        System.out.println("After long append: " + sb + ", Length: " + sb.length() + ", Capacity (approx): " + sb.capacity());
        System.out.println("Note: Capacity increases as needed, typically doubling and adding a small increment.");
        System.out.println();

        // 5.2 StringBuffer (Thread-Safe, Slower due to synchronization)
        System.out.println("--- StringBuffer ---");
        StringBuffer sbuf = new StringBuffer("Start");
        System.out.println("Initial StringBuffer: " + sbuf + ", Length: " + sbuf.length() + ", Capacity (approx): " + sbuf.capacity());

        sbuf.append("ing now."); // Modifies the existing object
        System.out.println("After append: " + sbuf + ", Length: " + sbuf.length() + ", Capacity (approx): " + sbuf.capacity());

        sbuf.reverse(); // Reverses the string
        System.out.println("After reverse: " + sbuf + ", Length: " + sbuf.length() + ", Capacity (approx): " + sbuf.capacity());

        System.out.println("Note: StringBuffer methods are synchronized, making it thread-safe but generally slower than StringBuilder.");
        System.out.println();

        // Converting mutable to immutable String
        String finalStringFromSB = sb.toString();
        System.out.println("Converted StringBuilder to String: " + finalStringFromSB);
        System.out.println("Is the converted string the same object as the StringBuilder? " + (finalStringFromSB == sb.toString())); // false, new String object created
    }
}
