> *Topics --> Generics, . . .*\
> *Link to Codes --> [Codes](../codes/collections/Generics.java)*



## Generics
- introduced in Java 5
- allows you to define classes, interfaces, and methods with type parameters.
- provides compile-time type safety.

**What are Generics?**
- Think of generics as placeholders for actual types. When you define a generic class like `List<T>`, `T` is a type parameter. When you use it, you replace `T` with a concrete type, like `List<String>` or `List<Integer>`.

**Benefits**
- *Compile-time Type Safety* --> Generics allow the compiler to check for type mismatches at compile time, catching errors early before the program runs.
- *Elimination of Type Casting* --> no need for explicitly casting objects retieved from generic collections.


### Generic Classes
- A generic class is a class that is defined with one or more type parameters.
    ```
    class Box<T> { // T is a type parameter
        private T content;

        public Box(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }
    }
    ```
- here while creating an object we can use any type in the place of `T`, and the object will be created for that Type.



### Generic Methods
- a method that introduces its own type parameters
- These type parameters can be used in the method's return type, parameter types, or local variable types.

    ```
    public class Util {
        public static <T> void printArray(T[] array) { // <T> declares T as a type parameter for this method
            for (T element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        public static <T extends Comparable<T>> T findMax(T x, T y) { // Bounded generic method
            return x.compareTo(y) > 0 ? x : y;
        }
    }
    ```
- here when we invoke the method, based on the arguments passed the `T` will be automatically identified (as the parameter is of type `T`)



### Bounded Type Parameters
- if we want to restrict the types that can be used as type arguments for a generic type, bounded type parameters can be used.

- **Upper Bounded Wildcards (`<? extends T>`)**
  - `<? extends T>` means "any type that is `T` or a subclass of `T`".

- **Lower Bounded Wildcards (`<? super T>`)**
  - `<? super T>` means "any type that is `T` or a superclass of `T`".

- **Unbounded Wildcards (`<?>`)**
  - `<?>` means "any type". It's equivalent to `<? extends Object>`



### Type Erasure
- Generics in Java are implemented using type erasure. 
- meaning that type information is only present at compile time and is *erased* at runtime.

- *How it works* --> The compiler replaces all type parameters with their bounds (e.g., `Object` if no explicit bound is given) and inserts appropriate casts to ensure type safety.
  - At runtime, `List<String>` and `List<Integer>` become simply `List`. This is for backward compatibility with older Java versions that didn't have generics.


---

- while declaring Collections we specify the type arguments two time in the diamond operator (right and left hand side), the left side part is to let the compiler know what the collection is expected to store (Compile-time safety) and the right side part is to actually specify the type of collection to allocate memory and instantiate. (`List<Integer> l = new ArrayList<Integer>()`)
- actully from Java 7 we can Omit the Type in right side part (compiler will infer them from the left side)
