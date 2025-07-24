> *Topics --> Strings, String Pool, String Builder, String Buffer, . . .*\
> *Link to Codes --> [Codes](../codes/Strings/)*


## Strings
- not a primitive Data Type, a reference type, represents sequence of characters
- it is a class (`java.lang.String`)
- it is immutable, any operation that appears to modify a string actually results in the creation of a new `String` object with modified content. (original `String` remains unchanged)
- *Benefits of Immutable* --> Security, Thread-Safety, Performance and Caching (String Pool), can be used as Map keys.

- **String Creation**
  - can be created in two primary ways,
    - ***Using String Literals***
      - when a string is created using double quotes, java first checks the String Pool, to see if a String object with same contents already exists.
    
    - ***Using `new` keyword***
      - when the `new` operator is used, a new `String` object is always created in the Heap Memory, even if an identical `String` literal already exists in the String Pool. (if a string literal is used in the declaration/definition it will also be added to the String Pool)


- ***String Memory Management (String Pool)***
  - also known as String Constant Pool, is a special area within the JVMs Heap
  - its purpose is to store unique `String` literals to save memory.
  - it is implemented used `Hash Table` or `Hash Set`

    - **Location of String Pool**
      - *Java 6 & earlier* --> String Pool was part of the **PermGen** (Permanent Generation) space of the Method Area. PermGen had a fixed size, which could lead to `OutOfMemoryError: PermGen space` if too many unique strings were interned.
      > PermGen was a fixed-size memory area that stored metadata about classes and methods, as well as interned strings.

      - *Java 7* -->  The String Pool was moved to the main **Heap space**. This made it eligible for garbage collection and allowed it to grow dynamically, reducing PermGen-related `OutOfMemoryErrors`.

      - *Java 8 & later* --> PermGen was completely removed and replaced by Metaspace (which uses native memory). The String Pool remains in the Heap.


- **`String.hashCode()`**
  - the `hashCode()` method is defined in the `java.lang.Object` class and is inherited by all the classes. its purpose is to return an integer hashCode value for the object.
  - `String` overrides this method inherited from `Object` class, because `Object`'s `hashCode()` typically returns a hash based on the object's memory address.
  - `String.hashCode()` computes the hash code based on the **content** (character sequence) of the string. (It also has formula but i dont think you'll be able to remember that!)

  - *Where is it used?*
    - when we use `equals()` method it compares this `hash`.
    - this `hash` is used to determine and return the reference of a `String` if it already exists in String Pool.


- **`intern()` Method**
  - it can be used to explicitly put a `String` into the String Pool or retrieve a reference to an existing `String` from the Pool.
    - if string pool already contains a `String` equal to the new `String` Object (as determined by `equals()`method), then the reference from the pool is returned.
    - Otherwise, this new `String` object is added to the pool, and a reference to this `String` object is returned.


- **String Comparision (`==` Vs. `equals()`)**
  - *`==` Operator* --> Compares **References**(memory addresses). returns `true` only if both variables point to the exact same object in the memory.
  - *`equals()` Method* --> Compares the actual content (Character Sequence) of the `String` objects. returns `true` if the character sequence is exactly the same.



- **`StringBuffer` and `StringBuilder`**
  - both are mutable
  - both consists of,
    - **Internal `char[]` Array (`value` field)** --> this Character array stores the actual characters of the String sequence.
    - **Current Length (`count` field)** --> keeps track of no. of elements stored in the value array (logical length of the String)
    - **Capacity (`value.length`)** --> total allocated size of internal `char[]` array. (max. buffer size)

  - both of them have almost the same underlying mechanisms.

  - general steps while performing operations,
    - **Check for Capacity** --> checks if current `capacity` is sufficient to accomodate the requested modification.

    - **Resizing (Expansion)** --> if current `capacity` is insufficient, a new larger `char[]` array is allocated in the memory.
      - The typical growth strategy is to double the old capacity and add a small increment 
      - The existing characters from the old `char[]` array are then copied to the newly allocated larger array using `System.arraycopy()`.
      - old, smaller array becomes eligible for garbage collection.

    - **Modification** --> requested modification is performed.
      - example: `insert()`, `delete()`, `append()`

    - **Update Length (`count`)** --> The `count` variable is updated to reflect the new logical length of the string sequence.


  - **Difference `StringBuilder` & `StringBuffer`**
    - ***`StringBuffer` (Older, Synchronized, Slower)***
      - *Thread-safe* --> All of its public methods (e.g., `append()`, `insert()`, `delete()`, `setCharAt()`) are explicitly declared as `synchronized`. This means that at any given time, only one thread can execute a `StringBuffer` method on a particular `StringBuffer` instance.

      - *Performance*: Slower than `StringBuilder` due to the overhead associated with acquiring and releasing locks for each `synchronized` method call. 

    - ***`StringBuilder` (Newer, Non-Synchronized, Faster)***
      - *Not thread-safe* --> Its methods are not `synchronized`. If multiple threads try to modify the same `StringBuilder` instance concurrently without external `synchronization`, it can lead to data corruption, unexpected behavior, or a `ConcurrentModificationException` (a race condition).
      - *Performance*: Generally faster than `StringBuffer` because it avoids the `synchronization` overhead.





- Buffer and Builder (Behaviour on my device)
  - initial capacity (when length is 0): 16
  - the capacity is getting incremented everytime the length is increased (if length=1 then capacity=17 ...)