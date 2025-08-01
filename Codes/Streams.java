package Codes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "grape", "avocado", "kiwi");

        // Stream pipeline:
        // 1. Create a stream from the list of words.
        // 2. Filter words that start with 'a'.
        // 3. Map (transform) them to uppercase.
        // 4. Sort them alphabetically.
        // 5. Collect the results into a new List.
        List<String> processedWords = words.stream() // Source
                                         .filter(s -> s.startsWith("a")) // Intermediate: lazy, no new collection yet
                                         .map(String::toUpperCase)     // Intermediate: lazy, no new collection yet
                                         .sorted()                     // Intermediate: might buffer for sorting
                                         .collect(Collectors.toList()); // Terminal: triggers execution, collects into new List

        System.out.println(processedWords);

        // short-circuiting with limit()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.stream()
               .filter(n -> {
                   System.out.println("Filtering: " + n);
                   return n % 2 == 0;
               })
               .limit(2) // Short-circuiting operation
               .forEach(n -> System.out.println("Processing: " + n));
        // 'Filtering' will stop after finding two even numbers

        // distinct() and peek()
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 1, 4, 5, 5);
        duplicates.stream()
                  .distinct() // Intermediate: uses internal Set to track seen elements
                  .peek(n -> System.out.println("After distinct: " + n)) // Intermediate: for debugging
                  .map(n -> n * 10) // Intermediate
                  .forEach(n -> System.out.println("Final: " + n)); // Terminal
    }
}



