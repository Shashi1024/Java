package Codes.ClassesObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

// there are 5 primary ways to create Objects in java
// --> using new keyword
// --> using Class.forName().newInstance()
// --> using Constructor.newInstance()
// --> using clone() method
// --> using deserialization


// class A is in another file
// class A implements Cloneable, Serializable{
//     String name;
//     int val;

//     A(){
//         name = "Nothing";
//         val = 0;
//     }

//     A(int x){
//         this.val = x;
//     }

//     @Override
//     protected Object clone() throws CloneNotSupportedException {
//         return super.clone();
//     }
// }

public class ObjectCreation {
    public static void main(String[] args) throws Exception{
        // using new keyword
        A obj1 = new A();


        // Class.forName loads the class and we can use it to create an instance (Reflection)
        // we can only invoke the no argument constructor using this method
        Class<?> clazz =  Class.forName("Codes.ClassesObjects.A");
        A obj2 = (A) clazz.newInstance();

        // getting the constructor of required type and using it to create an object (getConstructor() or getDeclaredConstructor())
        // we can get and use any constructor, to get a specific constructor, pass its parameter types as argument to getConstructor()
        // we can use getConstructor() if the constructor is public
        Constructor<A> cons = A.class.getDeclaredConstructor(int.class);
        A obj3 = cons.newInstance(10);

        // using the clone() method
        // the class A must implement Cloneable and override its method in order to use the clone() method
        A obj4 = (A) obj1.clone();

        // using deserialization (class A must implement the Serializable)
        System.out.println("--- 4. Using Deserialization ---");
        A objToSerialize = new A();
        String filename = "object.ser";

        // first serializing an object and writing it to a file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objToSerialize);
            System.out.println("Object serialized.");
        } catch (IOException e) {
            System.err.println("Serialization Error: " + e.getMessage());
        }

        // reading the serialized object from the file
        A obj5 = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            obj5 = (A) ois.readObject();
            System.out.println("Deserialized: " + obj4);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization Error: " + e.getMessage());
        } finally {
            new File(filename).delete();
        }
        System.out.println();

    }
}
