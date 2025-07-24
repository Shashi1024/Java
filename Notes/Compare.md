> *Topics --> Comparable, Comparator, . . .*\
> *Link to Codes --> [Codes](../Codes/Collections/)*



### The `Comparable` Interface
- `java.lang.Comparable<T>` interface is used to define the natural ordering of objects of a particular class.
- If a class implements `Comparable`, it means that its instances can be compared to other instances of the same type (or a supertype/subtype) to determine their relative order.

- The `Comparable` interface has a single `abstract` method:
  - ***`public int compareTo(T o);`***
    - *Returns*
      - A negative integer if `this` object is less than `o`.
      - Zero if `this` object is equal to `o`.
      - A positive integer if `this` object is greater than `o`.
    
    - *Contract*
      - It must be consistent with `equals()`. If `a.equals(b)` is true, then `a.compareTo(b)` should return 0. (Though this is a recommendation, not a strict compiler rule).
      - **It should be transitive**: if `(x.compareTo(y) > 0)` and `(y.compareTo(z) > 0)`, then `(x.compareTo(z) > 0)`.
      - **It should be symmetric**: `sgn(x.compareTo(y)) == -sgn(y.compareTo(x))`.


- it can be used with `Collections.sort()` and `Arrays.sort()`.
  - `java.util.Collections.sort(List<T> list)`
  - `java.util.Arrays.sort(T[] a)`


### The `Comparator` Interface
- The `java.util.Comparator<T>` interface is used to define an external or custom ordering for objects.
- Unlike `Comparable`, which defines a class's natural ordering, `Comparator` allows you to define multiple different sorting criteria for the same class, or to sort classes that do not implement `Comparable`

- The `Comparator` interface has a single abstract method (making it a functional interface):
  - `public int compare(T o1, T o2);`
    - *Returns*
      - A negative integer if `o1` is less than `o2`.
      - Zero if `o1` is equal to `o2`.
      - A positive integer if `o1` is greater than `o2`.

    - *Contract* --> Similar to compareTo(), it should ensure consistency, transitivity, and symmetry.
  - *Implementation Approaches*
    - **Separate Class**
    - **Anonymous Inner Class**
    - **Lambda Expressions (Java 8+)**


***`Comparator` Utility Methods (Java 8+)***
- Java 8 introduced static and default methods to the Comparator interface,
    - `Comparator.comparing(Function<T, U> keyExtractor)`
    - `thenComparing(Comparator<? super T> other)`
    - `reversed()`
    - `nullsFirst()`, `nullsLast()`


- it can be used with `Collections.sort()` and `Arrays.sort()`.
  - `java.util.Collections.sort(List<T> list, Comparator<? super T> c)`
  - `java.util.Arrays.sort(T[] a, Comparator<? super T> c)`