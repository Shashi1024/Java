*Topics --> Serialization, Deserialization, . . .*

### Serialization
- Serialization is the process of converting an object's state into a byte stream. 
- The primary purpose of serialization is to persist objects, meaning to save their state so they can be recreated later, or to transfer objects across different Java Virtual Machines (JVMs) or processes.

- For an object to be serializable, its class must implement the `java.io.Serializable` interface. This is a marker interface, meaning it has no methods to implement. Its sole purpose is to "mark" a class, indicating to the JVM that objects of this class can be serialized.


- ***`transient` keyword***
  - The transient keyword is used to mark fields that should not be serialized. 
  - When an object is serialized, the values of transient fields are skipped. 
  - During deserialization, transient fields are reinitialized to their default values

- ***`static` Fields***
  - static fields belong to the class, not to any specific object instance. Therefore, static fields are not serialized as part of an object's state. 
  - Their values remain unchanged during serialization and deserialization.

- ***`serialVersionUID`***
  - it is a unique identifier for serializable class.
  - It's a `static final long` field
  - *Purpose* --> During deserialization, the JVM compares the `serialVersionUID` of the serialized object with the `serialVersionUID` of the class present in the current JVM.
    - If they match, deserializatison proceeds.
    - If they don't match, an `InvalidClassException` is thrown.

  - if the actual class is mdoified before deserializing an old object, errors may occur.


---

### Deserialization
-  It involves converting a byte stream back into a live Java object in memory. 
- This object will have the same state (values of its non-transient fields) as it had when it was serialized.

- Deserialization is achieved using the `java.io.ObjectInputStream` class. Its `readObject()` method reads the byte stream and reconstructs the object.

---


***Serialization Process (Under the Hood)***
- When you serialize an object using `ObjectOutputStream`,
  1. **Object Graph Traversal** --> `ObjectOutputStream` traverses the object graph starting from the object being serialized. It identifies all reachable non-`transient` and non-`static` fields.
  2. **Metadata Writing** --> It writes metadata about the class (e.g., class name, `serialVersionUID`, field names, and types) to the byte stream.
  3. **Field Value Writing** --> For each non-`transient` and non-`static` field, it writes its value to the byte stream. If a field is a reference to another object, that object is also serialized recursively (if it's serializable).
  4. **Byte Stream Output** --> The byte stream is then written to an underlying output stream (e.g., `FileOutputStream` for a file, `SocketOutputStream` for a network connection).


***Deserialization Process (Under the Hood)***
- When you deserialize an object using `ObjectInputStream`,
  1. **Read Metadata** --> `ObjectInputStream` reads the class metadata (including `serialVersionUID`) from the byte stream.
  2. **Class Loading and Verification** --> It attempts to load the corresponding class in the current JVM. It then compares the `serialVersionUID` from the stream with the loaded class's `serialVersionUID`. If they don't match, an `InvalidClassException` is thrown.
  3. **Memory Allocation** --> If the `serialVersionUID` matches, the JVM allocates memory for the new object. Crucially, the constructor of the class is NOT invoked during deserialization. This is a key difference from normal object creation using `new`.
  4. **Field Value Population** --> The non-`transient` and non-`static` fields of the newly allocated object are populated with the values read from the byte stream. `transient` fields are set to their default values.
  5. **Object Return** --> The fully reconstructed object is returned.

---


***Serialization with `Externalizable` Interface***

- While Serializable provides automatic serialization, the `java.io.Externalizable` interface offers greater control over the serialization process.
- It is also a marker interface, but it requires the implementing class to provide concrete implementations for two methods:
  - `void writeExternal(ObjectOutput out) throws IOException;`
  - `void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;`