> *Topics --> Reflection, . . .*\
> *Link to Codes --> [Codes](../Codes/)*

## Reflection
- allows a running Java application to inspect and manipulate its own structure, behavior, and metadata at runtime.
- provides the ability to examine or modify the runtime behavior of classes, interfaces, fields, and methods without knowing their names at compile time.

- the compiler checks types and method calls at compile time. This is static typing. Reflection breaks this static barrier, enabling dynamic operations.

- *Purpose*
  - **Introspection (Inspection)** --> Discover information about classes, methods, fields, and constructors at runtime
  - **Manipulation** --> Invoke methods, access or modify fields, and create new instances of classes whose names were unknown at compile time.


- **Core Concepts and the `java.lang.Class` Object**
  -  entry point for all reflection operations in Java is the `java.lang.Class` class.
  - Every object in Java has a `Class` object associated with it, which provides runtime type information about the object.

  - **The `Class` Object**
    - When a `.class` file is loaded into the JVM (by the Class Loader Subsystem), the JVM creates an instance of `java.lang.Class` for that class.
    - This `Class` object contains all the metadata about the class: its name, superclass, interfaces it implements, fields, methods, constructors, annotations, etc.
    - There is only one `Class` object per loaded class in the JVM.

  - **The `java.lang.reflect` Package**
    - contains the core classes that represent the components of a loaded class, such as `Field`, `Method`, `Constructor`, `Array`, and `Modifier`. 
    - These classes allow you to interact with the underlying structure of the compiled code.

- ***Obtaining `Class` Objects*** (3 primary ways)
  1. **Using .class Literal (Most Common for known types)**
    - used when the class type is known at compile time.
    - *Syntax* --> `Class<MyClass> clazz = MyClass.class;`

  2. **Using `Object.getClass()` (For existing objects)**
    - used when we have an instance of an object and want to determine its actual runtime class.
    - *Syntax* --> `MyClass obj = new MyClass(); Class<?> clazz = obj.getClass();`

  3. **Using `Class.forName(String className)` (For dynamic loading)**
    - used when we only know the class name as a String at runtime 
    - This method also loads the class into the JVM if it hasn't been loaded already.
    - *Syntax* --> `Class<?> clazz = Class.forName("com.example.MyClass");`
    - *Exception* --> Throws `ClassNotFoundException` if the class with the specified name cannot be found.



### Inspecting and Manipulating Class Members Dynamically
- Once we have a `Class` object, we can use methods from `java.lang.reflect` to inspect its members and then manipulate them.

- **`Class` Object: Inspection of Class Metadata**
  - `Class` object itself is primarily used for introspection (inspection) of the class's structural metadata. 
  - allows us to discover information about the class, its superclass, interfaces, and its declared members.

  - *Basic Class Information*
    - `String getName()` --> Returns the fully qualified name of the class (e.g., "`java.lang.String`").
    - `String getSimpleName()` --> Returns the simple name of the class (e.g., "`String`").
    - `Class<?> getSuperclass()` --> Returns the `Class` object representing the immediate superclass. Returns `null` if this class represents `Object`, an `interface`, a primitive type, or `void`.
    - `Class<?>[] getInterfaces()` --> Returns an array of `Class` objects representing the interfaces directly implemented by the class.
    - `boolean isInterface()` / `boolean isArray()` / `boolean isPrimitive()` --> Checks if the `Class` object represents an interface, array, or primitive type.
    - `int getModifiers()` -->  Returns an integer representing the access modifiers (e.g., `public`, `private`, `static`, `final`). we then use `java.lang.reflect.Modifier` static methods to interpret this integer.
    
  - *Retrieving Member Objects (for further inspection/manipulation)*
    - `Method[] getMethods()` --> Returns an array of `Method` objects representing all `public` methods of the class and all its superclasses and implemented interfaces
    - `Method getMethod(String name, Class<?>... parameterTypes)` --> Returns a specific `public Method` object matching the given name and parameter types. Throws `NoSuchMethodException`.
    - `Method[] getDeclaredMethods()` --> Returns an array of `Method` objects representing all methods (public, protected, default, private) declared by this class only, not inherited ones.
    - `Field[] getFields()` --> Returns an array of `Field` objects representing all `public` fields of the class and its superclasses.
    - `Field getField(String name)` --> Returns a specific `public` `Field` object. Throws `NoSuchFieldException`.
    - `Field[] getDeclaredFields()` --> Returns an array of `Field` objects representing all fields (public, private, etc.) declared by this class only.
    - `Constructor<?>[] getConstructors()` --> Returns all `public` constructors.
    - `Constructor<T> getConstructor(Class<?>... parameterTypes)` --> Returns a specific `public` `Constructor` object. Throws `NoSuchMethodException`.
    - `Constructor<?>[] getDeclaredConstructors()` --> Returns all constructors (public, private, etc.) declared by this class only.



- **`Constructor<T>`: Dynamic Instance Creation**
  - The `Constructor` class represents a single constructor of a class. It's used for manipulation by creating new instances of the class at runtime.
  
  - *Inspection*
    - Obtain `Constructor` objects using `Class.getConstructor()`, `Class.getConstructors()`, `Class.getDeclaredConstructor()`, `Class.getDeclaredConstructors()`.
    - `Class<?>[] getParameterTypes()`: Returns an array of `Class` objects representing the parameter types of the constructor.
    - `int getModifiers()`: Returns the modifiers of the constructor.

  - *Manipulation (Instance Creation)*
    - `T newInstance(Object... initargs)` --> This is the primary method for manipulation. It creates a new instance of the class using this constructor. The `initargs` are the arguments passed to the constructor.
    
  - *Exceptions* --> Throws `InstantiationException` (if the class is abstract or an interface), `IllegalAccessException` (if access is denied), `IllegalArgumentException` (if arguments don't match), `InvocationTargetException` (if the constructor itself throws an exception).



- **`Method`: Dynamic Method Invocation**
  - `Method` class represents a single method of a class or interface. 
  - It's used for manipulation by invoking methods at runtime.

  - *Inspection*
    - Obtain `Method` objects using `Class.getMethod()`, `Class.getMethods()`, `Class.getDeclaredMethod()`, `Class.getDeclaredMethods()`.
    - `String getName()`: Returns the name of the method.
    - `Class<?> getReturnType()`: Returns the `Class` object representing the return type.
    - `Class<?>[] getParameterTypes()`: Returns an array of `Class` objects representing the parameter types.
    - `int getModifiers()`: Returns the modifiers of the method.
    - `Class<?>[] getExceptionTypes()`: Returns an array of `Class` objects representing the exceptions declared to be thrown by this method.

  - *Manipulation (Method Invocation)*
    - `Object invoke(Object obj, Object... args)`: This is the primary method for manipulation. It invokes the underlying method represented by this `Method` object, on the specified `obj` (the instance on which to invoke the method). The `args` are the arguments passed to the method.
    - If the method is `static`, `obj` can be `null`.
    
  - *Exceptions*: Throws `IllegalAccessException`, `IllegalArgumentException`, `InvocationTargetException` (if the underlying method throws an exception).


- **`Field`: Dynamic Field Access and Modification**
  - The `Field` class represents a single field (instance variable or static variable) of a class or interface. 
  - It's used for manipulation by accessing or modifying field values at runtime.

  - *Inspection*
    - Obtain `Field` objects using `Class.getField()`, `Class.getFields()`, `Class.getDeclaredField()`, `Class.getDeclaredFields()`.
    - `String getName()`: Returns the name of the field.
    - `Class<?> getType()`: Returns the `Class` object representing the type of the field.
    - `int getModifiers()`: Returns the modifiers of the field.

  - *Manipulation (Accessing/Modifying Values)*
    - Object `get(Object obj)`: Returns the value of the field represented by this `Field` object, on the specified `obj` (the instance whose field value you want).
    - `void set(Object obj, Object value)`: Sets the field represented by this `Field` object on the specified `obj` to the new `value`.
    - For `static` fields, `obj` can be `null`.

  - *Exceptions*: Throws `IllegalAccessException`, `IllegalArgumentException`.

  > Modifying `final` fields via reflection is generally discouraged and can lead to unexpected behavior or `IllegalAccessException` in newer Java versions, as it breaks the `final` contract.


- **`Modifier`: Interpreting Access Modifiers**
  - `Modifier` class provides static methods to inspect and decode the integer value returned by `getModifiers()` methods of `Class`, `Field`, `Method`, and `Constructor`.
  - *Inspection*
    - `static boolean isPublic(int mod)`: Checks if the modifier is public.
    - `static boolean isPrivate(int mod)`: Checks if the modifier is private.
    - `static boolean isStatic(int mod)`: Checks if the modifier is static.
    - `static boolean isFinal(int mod)`: Checks if the modifier is final.
    - `static String toString(int mod)`: Returns a string representation of the modifiers (e.g., "public static final").

  - *Manipulation* --> None directly, its a utility for inspection.


- **`Array`: Dynamic Array Creation and Manipulation**
  - The `java.lang.reflect.Array` class provides static methods to manipulate arrays dynamically, including creation, getting, and setting elements.
  - *Inspection*
    - `static int getLength(Object array)`: Returns the length of the specified array object.
    - `Class<?> getComponentType()`: (Called on the `Class` object of the array, e.g., `String[].class.getComponentType()`) Returns the `Class` representing the component type of the array.

  - *Manipulation*
    - `static Object newInstance(Class<?> componentType, int length)`: Creates a new array with the specified component type and length.
    - `static Object newInstance(Class<?> componentType, int... dimensions)`: Creates a new multi-dimensional array.
    - ``static Object get(Object array, int index)`: Returns the value of the indexed component in the specified array object.
    - `static void set(Object array, int index, Object value)`: Sets the value of the indexed component in the specified array object.





### 5. Accessing Private Members (`setAccessible(true)`)

By default, reflection respects Java's access modifiers (`private`, `protected`, `default`). This means you cannot directly access private fields or invoke private methods using `getField()`, `getMethod()`, `getConstructor()` or their `get()`/`invoke()` counterparts without special permission.

The `AccessibleObject` class (superclass of `Field`, `Method`, `Constructor`) provides the `setAccessible(boolean flag)` method.

* **`void setAccessible(true)`**: This method allows you to suppress Java language access checks for the reflected object. Once set to `true`, you can access `private` and `protected` members.
* **`void setAccessible(false)`**: Resets the access checks.

* **Security Implications**:
    * Using `setAccessible(true)` is a powerful operation that **bypasses Java's encapsulation and security mechanisms**. It should be used with extreme caution and only when absolutely necessary.
    * It can lead to `SecurityException` if a `SecurityManager` is active and disallows the operation.
    * In newer Java versions (modules system), `setAccessible(true)` might be restricted for modules that do not explicitly `open` their packages for reflection.