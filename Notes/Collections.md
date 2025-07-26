> *Topics --> Collection Framework (Interfaces, Classes), Iterator, . . .*\
> *Link to Codes --> [Codes](../Codes/Collections/)*

## Collection Framework
- A set of interfaces and classes that implement commonly reusable collection data structures.
- Prior to Java 2 (JDK 1.2), Java had ad-hoc classes for grouping objects (like `Vector`, `Hashtable`, `Array`). The Collections Framework, introduced in Java 1.2, standardized this approach.

![Collection Framework Hierarchy](../can/collections.jpg "Collection Framework Hierarchy")


---


### `Iterable` Interface (`java.lang.Iterable`) (Java 5+)
- The `Iterable` interface is a single-method interface (making it a functional interface from Java 8, though not typically used with lambdas directly for its primary purpose). 
- It defines only one abstract method:
  ```
  public interface Iterable<T> {
      Iterator<T> iterator();
  }
  ```
  - `iterator()` method --> This method returns an `Iterator` over elements of type `T`.

- Any class that implements the Iterable interface promises that it can provide an Iterator for its elements.(signify that an object is a collection of elements that can be traversed.)
- The most common and significant use of the Iterable interface is its direct support for the for-each-loop.

- **Methods Added to `Iterable` in Java 8**
  - With the introduction of `default` methods in interfaces in Java 8, the `Iterable` interface gained two new methods:
    - `forEach(Consumer<? super T> action)` --> This is a `default` method that performs the given action for each element of the `Iterable` until all elements have been processed or the action throws an exception.
    - `spliterator()` --> a specialized Iterator for traversing and partitioning elements of a source. It's primarily used internally by the Java Streams API to enable parallel processing.

- we can make custom custom data structures iterable by implementing `Iterable` interface.


---


### `Collection<E>` Interface (The Root Interface)
- The most general interface in the collection hierarchy. It represents a group of objects, known as its elements.
- *Characteristics*,
  - does not guarantee order
  - May or may not allow duplicate elements (depends on implementing sub-interfaces/classes).

- *Methods* --> `boolean add(E e)`, `boolean remove(Object o)`, `boolean contains(Object o)`, `int size()`, `boolean isEmpty()`, `Iterator<E> iterator()`, `void clear()`, `boolean addAll(Collection<? extends E> c)`, `boolean removeAll(Collection<?> c)`, `boolean retainAll(Collection<?> c)`, . . .

---

### `List<E>` Interface (Ordered Collection, Allows Duplicates)
-  Represents an ordered collection (sequence) of elements. Elements can be accessed by their integer index.
- *Characteristics*,
  - Ordered
  - Allows duplictes
  - Indexed access

- *Methods* --> (all methods of `Collection`), `void add(int index, E element)`, `E get(int index)`, `E set(int index, E element)`, `E remove(int index)`, `int indexOf(Object o)`, `int lastIndexOf(Object o)`, `ListIterator<E> listIterator()`, . . .

***`List` Implementations***

- **`ArrayList<E>`**
  - *Underlying Data Structure* --> Resizable Array
  - *Initial size & resizing*
    - *default initial capacity* --> 10 elements
    - *Resizing* --> when current capacity is exceeded, the new capacity is typically (`current_capacity * 1.5) + 1 (or current_capacity + (current_capacity / 2) + 1` in older JDKs, effectively 1.5 times). All existing elements are copied to the new, larger array.
  - *Performance*
    - `get(index)` --> O(1) - direct array access
    - `add(element)` (at end) --> Amortized O(1) - usually fast, but can be O(N) if resizing is needed.
    - `add(index, element)` / `remove(index)` --> O(N) - requires shifting elements.
    - `remove(Object)` --> O(N) - requires searching and shifting.
  - Non-Synchronized --> Not Thread Safe


- **`LinkedList<E>`**
  - *Underlying Data Structure* --> Doubly-linked list.
  - *Initial size & resizing* --> starts empty and grows dynamically
  - *Performance*,
    - `get(index)` --> O(N) - requires traversing the list.
    - `addFirst() / addLast() / removeFirst() / removeLast()` --> O(1) - constant time.
    - `add(index, element) / remove(index) (in middle)` --> O(N) - requires traversal to find the position, but once found, insertion/deletion is O(1).

  - Non-Synchronized --> Not Thread Safe


- **`Vector<E>` (Legacy)**
  - *Underlying Data Structure* --> Resizable array.
  - *Initial size & resizing*
    - *default initial capacity* --> 10 elements
    - *Resizing* --> When capacity is exceeded, Vector doubles its size `(current_capacity * 2)`. we can also specify a `capacityIncrement` at construction.
  - *Performance* --> similar to `ArrayList`, but generally slower due to synchronization overhead
  - Synchronized --> all methods are Synchronized (thread safe)

  - `Stack`(legacy) extends `Vector` and provides common stack methods (all methods are synchronized)
  - Stack can be created using `Deque` (preffered)



---

### `Set<E>` Interface (Unordered Collection, No Duplicates)
- Represents a collection that contains no duplicate elements. It models the mathematical set abstraction.
- *Characteristics*
  - No duplicates
  - Unordered (generally) --> Most `Set` implementations do not guarantee any order of elements.

- *Methods* --> (all Inherited), `boolean add(E e)`, `boolean remove(Object o)`, . . .

***`Set` Implementations***

- **`HashSet<E>`**
  - *Underlying Data Structure* --> Hash table (backed by a `HashMap`).
  - *Initial size & resizing*
    - *default initial capacity* --> 16 (for the underlying `HashMap`)
    - *Default Load Factor* --> 0.75.
    - *Resizing* --> When the number of elements (`size`) exceeds `capacity * load_factor`, the `HashSet` (or its backing `HashMap`) creates a new, larger internal array (typically double the size) and rehashes all existing elements into the new array.
  - *Performance*,
    - `add()` / `remove()` / `contains()` --> O(1) on average (constant time), assuming a good hash function and minimal collisions. Can degrade to O(N) in worst-case collision scenarios.

  - *Characteristics*
    - does not guarantee insertion order
    - elements are stored based on their `hashCode()` and `equals()` methods.

  - Non-Synchronized --> Not Thread Safe


- **`LinkedHashSet<E>`**
  - *Underlying Data Structure* --> Hash table with a doubly-linked list running through its entries.
  - *Initial size & resizing*
    - similar to `HashSet`
  - *Performance* --> Similar to HashSet (O(1) average for basic operations).
  - *Characteristics* --> Maintains insertion order (the order in which elements were added).
  - Non-Synchronized --> Not Thread Safe


- **`TreeSet<E>`**
  - *Underlying Data Structure* --> Red-Black Tree (a self-balancing binary search tree).
  - *Initial size & resizing* --> no initial capacity, dynamically grows.
  - *Performance* --> 
    - `add()` / `remove()` / `contains()` --> O(log N) (logarithmic time).

  - *Characteristics*,
    - Stores elements in sorted (natural or custom comparator) order. 
    - Does not allow null elements (throws NullPointerException if you try to add one).

  - Non-Synchronized --> Not Thread Safe

---

### `Queue<E>` Interface (FIFO Collection)
- Represents a collection designed for holding elements prior to processing. Typically, elements are added at one end and removed from the other (FIFO - First-In, First-Out).
- *Characteristics*,
  - FIFO
  - does not support indexed access

- *Methods* --> `boolean add(E e)` / `boolean offer(E e)`, `E remove()` / `E poll()`, `E element()` / `E peek()`, . . .


### `Deque<E>` Interface (Double-Ended Queue)
- Represents a double-ended queue, which supports element insertion and removal at both ends.
- it extends `Queue`
- *Characteristics* --> Can be used as a FIFO queue or a LIFO (Last-In, First-Out) stack.

- *Methods* --> `void addFirst(E e)`, `void addLast(E e)`, `E removeFirst()`, `E removeLast()`, `E peekFirst()`, `E peekLast()`, `push()`, `pop()`, . . .


***`Queue` and `Deque` Implementations***

- **`PriorityQueue<E>`**
  - *Underlying Data Structure* --> Min-heap (a binary heap).
  - *Initial size & resizing*
    - *default initial capacity* --> 11 elements
    - *Resizing* --> When the capacity is exceeded, the underlying array is grown. The growth strategy is typically `(current_capacity < 64) ? (current_capacity + 2) : (current_capacity / 2) + current_capacity`.


  - *Characteristics*
    - Elements are ordered according to their natural ordering or by a Comparator provided at queue construction time. 
    - The head of the queue is the least element. 
    - Not strictly FIFO.

  - *Performance*
    - `offer()`, `poll()`, `peek()` : O(log N).
  
  - Non-Synchronized --> Not Thread Safe


- **`ArrayDeque<E>`**
  - *Underlying Data Structure* --> Resizable array.
  - *Initial size & resizing*
    - *default initial capacity* --> 16 elements
    - *Resizing* --> When the array becomes full, it doubles in size.
  - *Characteristics*
    - Can function as both a Queue (FIFO) and a Deque (double-ended queue/stack). 
    - More efficient than LinkedList when used as a stack or queue.
  
  - *Performance*
    - `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()` : amortized O(1).

  - Non-Synchronized --> Not Thread Safe


---

### `Map<K, V>` (Key-Value Pair Collection)
- Represents a collection that maps unique keys to values. It's not a true `Collection` (doesn't implement `Collection` interface directly) but is part of the framework.
- *Characteristics*,
  - Uniques Keys
  - Allows duplicate values
  - No Order (generally)

- *Methods* --> `V put(K key, V value)`, `V get(Object key)`, `V remove(Object key)`, `boolean containsKey(Object key)`, `boolean containsValue(Object value)`, `Set<K> keySet()`, `Collection<V> values()`, `Set<Map.Entry<K, V>> entrySet()`, . . .


***`Map` Implementations***

- **`HashMap<K, V>`**
  - *Underlying Data Structure* --> Hash table.
  - *Initial size & resizing*
    - *default initial capacity* --> 16
    - *Default Load Factor* --> 0.75.
    - *Resizing* --> When the number of entries (`size`) exceeds `capacity * load_factor`, the HashMap doubles its internal array size and rehashes all existing entries into the new array. This is a costly operation `(O(N))`

  - *Performance*,
    - `put()` / `get()` / `remove()` / `containsKey()` --> O(1) on average, assuming a good hash function and minimal collisions. Can degrade to O(N) in worst-case collision scenarios.

  - *Characteristics*,
    - Does not Guarantee Insertion Order.
    - Allows one `null` key and multiple `null` values.

  - Non-Synchronized --> Not Thread Safe

  - *Internal Storage and Handling*
    - `HashMap` stores key-value pairs in an array of "buckets". Each bucket can contain multiple entries, typically linked together in a linked list or, in Java 8+, a balanced tree (Red-Black Tree) for buckets with many collisions.

    - `HashMap` uses `hashCode()` method to generate hash and uses `equals()` method for comparing the key.


- **`LinkedHashMap<K, V>`**
  - *Underlying Data Structure* --> Hash table with a doubly-linked list running through its entries.
  - *Internal Storage and Handling*
    - Similar to `HashMap`, it has a default initial capacity of 16 and a load factor of 0.75, and resizes by rehashing when the threshold is met.

  - *Performance* --> Similar to `HashMap` (O(1) average for basic operations).

  - *Characteristics*
    - Maintains Insertion order
    - can be configured to maintain access order (useful for LRU caches)

  - Non-Synchronized --> Not Thread Safe

- **`TreeMap<K, V>`**
  - *Underlying Data Structure* --> Red-Black Tree.
  - *Internal Storage and Handling*
    - does not have initial capacity, grows dynamically

  - *Performance*
    - `put()` / `get()` / `remove()` / `containsKey()`: O(log N).

  - *Characteristics*
    - Stores entries in sorted order based on the natural order of keys or a custom Comparator. 
    - Does not allow null keys.

  - Non-Synchronized --> Not Thread Safe

- **`Hashtable<K, V>` (Legacy)**
  - *Underlying Data Structure* --> Hash table.
  - *Internal Storage and Handling*
    - *default initial capacity* --> 11
    - *Resizing* --> When capacity is exceeded, it increases by `(current_capacity * 2) + 1`
  - *Performance* --> Similar to `HashMap`, but generally slower due to synchronization overhead.
  - Synchronized --> all methods are synchronized (thread safe)
  - *Characteristics*
    - does not allow `null` values or `null` keys



---


### Utility Classes: `Collections` and `Arrays`

- The Java Collections Framework provides utility classes with static methods to perform common operations on collections and arrays.

***`java.util.Collections`***
- Provides static methods that operate on or return collections.
- It contains polymorphic algorithms that operate on collections, "wrappers", which return a new collection backed by a specified collection, and a few other odds and ends.

- *Key Methods*
  - **Sorting** --> `sort(List<T> list)` (uses natural ordering), `sort(List<T> list, Comparator<? super T> c)` (uses custom comparator).
  - **Searcing** --> `binarySearch(List<? extends Comparable<T>> list, T key)` (list must be sorted).
  - **Shuffling** --> `shuffle(List<?> list)`
  - **Reversing** --> `reverse(List<?> list)`
  - **Frequency/Disjoint** --> `frequency(Collection<?> c, Object o)`, `disjoint(Collection<?> c1, Collection<?> c2)`.
  - **Thread-Safe Wrappers** --> `synchronizedList()`, `synchronizedSet()`, `synchronizedMap()`, etc. (e.g., `List<String> syncList = Collections.synchronizedList(new ArrayList<>());`). These provide basic synchronization for non-thread-safe collections.


***`java.util.Arrays`***
- Provides static methods to manipulate arrays.

- *Key Methods*
  - `sort(array)` --> Sorts an array.
  - `binarySearch(array, key)` --> searches for an element
  - `copyOf(original, newLength)` --> Copies an array.
  - `asList(T... a)` --> Returns a fixed-size List backed by the specified array. (Modifying this list directly modifies the array).
  - `deepEquals(Object[] a1, Object[] a2)` --> Compares two arrays for deep equality.


---


### Iterators (Traversing Collections)
- An `Iterator` is an object that enables you to traverse a collection and remove elements during iteration. 
- provides a standard way to access elements sequentially without exposing the underlying structure of the collection.

- *Key Methods*
  - `boolean hasNext()`
  - `E next()`
  - `default void remove()` --> by default it throws an Exception (`UnsupportedOperationException("remove")`)
  - `default void forEachRemaining(Consumer<? super E> action)` --> it uses `hasNext()`, `next()` under the hood.

- `ListIterator<E>` --> A sub-interface of Iterator specifically for Lists. It provides additional functionality,
  - Bidirectional traversal (`hasPrevious()`, `previous()`).
  - Modifying elements (`set(E e)`).
  - Adding elements (`add(E e)`).
  - Getting indices (`nextIndex()`, `previousIndex()`).

- An `Iterator` is a one-time use object. Once you iterate through a collection using an `Iterator`, you generally cannot reset it to the beginning. To iterate again, you need to obtain a new `Iterator` from the `Iterable` object.
- `Iterator` provides a way to remove elements from the underlying collection safely during iteration (using `remove()`), which is not possible directly with the enhanced for-loop. Modifying a collection while iterating over it using an enhanced for-loop (or a traditional `for` loop that relies on index) can lead to `ConcurrentModificationException`.


---


### Generics: Type Safety in Collections (Java 5+)
- allow you to specify the type of objects that a collection can hold.
- This provides **compile-time type safety** and eliminates the need for explicit type casting, preventing `ClassCastExceptions` at runtime.

- *For more info refer Generics --> [Generics](generics.md)*


---


### Concurrency in Collections

- The default implementations of collections (`ArrayList`, `HashMap`, `HashSet`, `LinkedList`, `TreeMap`, `TreeSet`, `ArrayDeque`, `PriorityQueue`) are not thread-safe.
- If multiple threads access and modify these collections concurrently without external synchronization, it can lead to data corruption or `ConcurrentModificationException`.

- *approaches for thread-safe collections:*
  - **Synchronized Wrappers (from `java.util.Collections`)**
    - `Collections.synchronizedList(List<T> list)`
    - `Collections.synchronizedSet(Set<T> s)`
    - `Collections.synchronizedMap(Map<K, V> m)`
    
    - These methods return a synchronized (thread-safe) wrapper around an existing non-synchronized collection. Every method call to the wrapper is synchronized.
    - can be a performance bottleneck, due to coarse-grained locking (the entire collection is locked for every operation).

  - **Concurrent Collections (from `java.util.concurrent`)**
    - These classes are designed for high-performance concurrent access.
    - They use more sophisticated concurrency control mechanisms (like fine-grained locking, lock-free algorithms, or optimistic concurrency) to provide better scalability than synchronized wrappers.

    - `ConcurrentHashMap` --> A highly scalable, thread-safe alternative to HashMap and Hashtable.
    - `CopyOnWriteArrayList` --> A thread-safe List implementation where all mutative operations (add, set, remove, etc.) are implemented by making a fresh copy of the underlying array. Reads are very fast as they don't require locking. Suitable for lists that are frequently read but rarely modified.
    - `CopyOnWriteArraySet` --> A thread-safe Set implementation backed by CopyOnWriteArrayList.
    - `ConcurrentLinkedQueue` --> A thread-safe, unbounded, non-blocking FIFO queue.
    - `BlockingQueue` implementations --> (e.g., `ArrayBlockingQueue`, `LinkedBlockingQueue`) Support blocking operations when the queue is full or empty, useful for producer-consumer patterns.

