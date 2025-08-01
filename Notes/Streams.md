> *Topics --> Streams, . . .*\
> *Link to Codes --> [Codes](../Codes/Streams.java)*

## Streams
- introduced in java 8
- Java Stream is a sequence of elements that supports sequential and parallel aggregate operations. 
- *Characteristics*
  - not a data structure --> do not store data
  - Functional in Nature --> produce a result without modifying the original data source.
  - Lazy Evaluation --> Intermediate operations are not executed immediately. They are only performed when a terminal operation is invoked. 
  - Pipelining --> Operations can be chained together to form a pipeline, where the output of one operation becomes the input for the next.
  - Consumable --> A stream can be traversed only once. After a terminal operation is performed, the stream is "consumed" and cannot be reused. 
  - Possibly Unbounded -->  Streams can represent infinite sequences of elements (e.g., `Stream.iterate()`, `Stream.generate()`)
  - Parallel Processing --> Streams can be processed in parallel


### Types of Stream Operations
- Intermediate operations & Terminal Operations

#### Intermediate Operations
- These operations transform a stream into another stream.
- They are lazy, meaning they are not executed until a terminal operation is invoked.
- they enable chaining to form a pipeline
- return a `Stream` (or a specialized primitive stream like `IntStream`, `LongStream`, `DoubleStream`), allowing for method chaining.

- *Operations*
  - **`filter(Predicate<T> predicate)`**
    - Used to select elements based on a condition.
    - Returns a stream consisting of the elements of this stream that match the given predicate.

  - **`map(Function<T, R> mapper)`**
    - Transforms each element in the stream into a new form. The type of the transformed elements can be different from the original.
    - Returns a stream consisting of the results of applying the given function to the elements of this stream.

  - **`flatMap(Function<T, Stream<R>> mapper)`**
    - Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. 
    - This effectively "flattens" a stream of streams into a single stream.

  - **`distinct()`**
    - Removes duplicate elements from the stream.
    - Returns a stream consisting of the distinct elements (according to `equals()`) of this stream.

  - **`sorted() / sorted(Comparator<T> comparator)`**
    - Sorts the elements in the stream.
    - `sorted()` --> Returns a stream consisting of the elements of this stream, sorted according to natural order.
    - `sorted(Comparator<T> comparator)` --> Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator.

  - **`peek(Consumer<T> action)`**
    - Primarily for debugging or logging purposes, allowing you to "peek" at elements at an intermediate stage of the pipeline without altering the stream's elements.
    - Returns a stream consisting of all elements from the original stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.

  - **`limit(long maxSize)`**
    - Limits the number of elements processed. Essential for infinite streams.
    - Returns a stream consisting of the elements of this stream, truncated to be no longer than `maxSize` in length. This is a **short-circuiting operation**.

  - **`skip(long n)`**
    - Skips a specified number of elements from the beginning of the stream.
    - Returns a stream consisting of the remaining elements of this stream after discarding the first `n` elements.


#### Terminal Operations
- These operations produce a result or a side-effect and mark the end of the stream pipeline.
- They trigger the actual execution of all preceding intermediate operations.
- Once a terminal operation is performed, the stream cannot be used again.
- produce a final result or a side-effect and consume the stream.

- *Conditions*
  - **`forEach(Consumer<T> action)`**
    - Iterates over elements and performs a side-effect (action).

  - **`collect(Collector<T, A, R> collector)`**
    - Gathers the results of the stream operations into a collection (e.g., `List`, `Set`, `Map`) or performs a summary operation. `Collectors` utility class provides many predefined collectors.
    - Performs a mutable reduction operation on the elements of this stream using a `Collector`. A `Collector` is an interface that provides methods for accumulating elements into a mutable result container and optionally transforming the result into a final representation.

  - **`reduce(T identity, BinaryOperator<T> accumulator)` / `reduce(BinaryOperator<T> accumulator)` / `reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)`**
    - Combines all elements into a single result
    - Performs a reduction on the elements of this stream, using an associative accumulation function, and returns an `Optional` describing the reduced value, or `identity` if provided.

  - **`count()`**
    - Gets the number of elements after all intermediate operations.

  - **`min(Comparator<T> comparator)` / `max(Comparator<T> comparator)`**
    - Finds the smallest/largest element.
    - Returns an `Optional` describing the minimum/maximum element of this stream according to the provided `Comparator`.

  - **`anyMatch(Predicate<T> predicate)` / `allMatch(Predicate<T> predicate)` / `noneMatch(Predicate<T> predicate)`**
    - Checks if elements satisfy a condition.
    - `anyMatch()` --> Returns whether any elements of this stream match the provided predicate. Short-circuiting.
    - `allMatch()` --> Returns whether all elements of this stream match the provided predicate. Short-circuiting.
    - `noneMatch()` --> Returns whether no elements of this stream match the provided predicate. Short-circuiting.

  - **`findFirst()` / `findAny()`**
    - Finds an element in the stream.
    - `findFirst()` --> Returns an `Optional` describing the first element of this stream, or an empty `Optional` if the stream is empty. Short-circuiting.
    - `findAny()` --> Returns an `Optional` describing any element of this stream, or an empty `Optional` if the stream is empty. Short-circuiting. Useful for parallel streams where finding any element is faster than finding the first in order.

  - **`toArray() / toArray(IntFunction<T[]> generator)`**
    - Converts the stream elements into an array.
    - `toArray()` --> Returns an array containing the elements of this stream.
    - `toArray(IntFunction<T[]> generator)` --> Returns an array containing the elements of this stream, using the provided `generator` function to allocate the returned array.



### Internal Processing and Memory Management

- **Lazy Evaluation (The Core Principle)**
  - Intermediate operations do not process data immediately. Instead, they build a pipeline of operations. 
  - Each intermediate operation returns a new `Stream` instance that conceptually represents the next stage of the pipeline.
  - The actual data processing only begins when a terminal operation is invoked. This is often referred to as "pull-based" processing. 
  - The terminal operation "pulls" elements from the stream source, and as each element is requested, it flows through the entire pipeline of intermediate operations one by one.
  - *Benefit* --> This avoids creating intermediate collections in memory.

- **Fusion of Operations**
  - When a terminal operation is called, the JVM (specifically, the Stream API implementation) effectively "fuses" or "stitches together" the intermediate operations into a single, optimized operation.
  - This means that for each element pulled from the source, all relevant intermediate operations are applied to it sequentially before moving to the next element. This "vertical" processing (processing one element completely through the pipeline) is more efficient than "horizontal" processing (applying one operation to all elements, then the next operation to all elements).

- **Short-Circuiting Operations**
  - Some intermediate (`limit()`) and terminal (`anyMatch()`, `allMatch()`, `noneMatch()`, `findFirst()`, `findAny()`) operations are "short-circuiting."
  - This means they can terminate processing of the stream early as soon as the result is determined, without processing all elements.

- **No Internal Storage**
  - stream itself does not store elements. It acts as a conduit.
  - it might use some memory while processing the operations(like sorting), but is very minimal compared to data storage

- **Parallel Streams and Fork/Join Pool**
  - When we use parallelStream(), the Stream API leverages the Java Fork/Join framework.
  - The source data is recursively split into smaller chunks (forked) that can be processed independently by different threads in a common `ForkJoinPool`.
  - The results from these sub-tasks are then combined (joined) to produce the final result.
