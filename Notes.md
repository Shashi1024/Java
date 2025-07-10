> Packages and Imports
- we cannot import two classes with same names.
  - if we want to use two classes with same name but different packages, we can import one and use another by referencing it directly. (pkg.cls)
  - if two wildcard imports have one or more same class names, compiler will throw error. (java.uti.* and java.sql.* both have Date)

> Instance Initializer Blocks (Static Initializers)

> we can add or include underscores in numbers in order to make it easier to read




---

> Local Variables --> declared inside a method, they cannot have access modifiers or static in their declaration (public, protected, private, static keywords cannot be used for local variables), only final keyword can be used.

> Instance Variables --> Variables that are not local but associated with objects.

> Class Variables --> Associated with classes and declared using `static` keyword.

> Instance and Class Variables do not require initialization before using, they are given a default value as soon as they are declared.

> Local variables cannot be used without initialization (Compiler will throw an error ), they do not have a default value and contain a garbage value util initialized. [Local variables can never have a scope larger than the method they are defi ned in.]


> instance variables are available as soon as they are defined and last for the entire lifetime of the object itself. 

> The rule for class (static) variables is they go into scope when declared like the other variables types. However, they stay in scope for the entire life of the program.


 ■ Local variables — in scope from declaration to end of block

 ■ Instance variables — in scope from declaration until object garbage collected

 ■ Class variables — in scope from declaration until program ends


| Element | Required | Ordering |
| -------- | -------- | -------- |
| Package | No | First Line of file |
| Imports | No | Immediately after package |
| Class Declaration | Yes | Immediately after imports |
| Method Declaration | No | Anywhere in a class |
| Field Declaration | No | Anywhere in a class |


> multiple classes can be defined in the same file, but only one of them is allowed to be public. (A file is also allowed to have neither class be public)



---
## Destroying Objects
- all java objects are stored in program memory's heap.
- **Heap**
  - heap is referred to as a free store, represents a large pool unused memory allocated to a java application

### Garbage Collection
- process of automatically freeing the memory on the heap by deleting objects that are no longer reachable in your program
- if an object or a collection of objects is declared as static, they wont be collected by GC.
- **No longer reachable**
  - The object no longer has any references pointing to it.
  - All references to the object have gone out of scope. 

- **System.gc**
  - it does not gurantee to run garbage collection
  - it just suggests java that it might be a good time to run garbage collection.
  - java is free to ignore the request
- `finalize()`
  - it is a method provided by the java "object" class, by default it does nothing (only the method signature is given by java).
  - Usually this method is invoked on an object by the java garbage collector when the object is about to be collected by the GC.
  - it is used to release the Non-java resources used by the object.
  - it is unpredictable (meaning it may or may not be invoked by GC and it can be invoked 0 or 1 time only, ...) and has been deprecated.

---

| Object | Reference |
| -------- | -------- |
| it sits on the heap and does not have a name | it is a variable and has a name |
| there is no way to access an object, except through a reference | used to access contents of an object |
| objects have different shapes/sizes/varying amount of memory | a reference an be assigned to another reference, passed to a method or returned from a method |
| an object cannot be assigned to other objects, nor can be passed to method or returned form a method | all references are of same size, no matter what their type is |
| it is the object that gets garbage collected not its reference | a reference may or may not be created on the heap |


--- 

