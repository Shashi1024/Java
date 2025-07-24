package Codes.collections;

import java.util.HashMap;
import java.util.Objects;

public class MapInternal {
    static class MyKey {
        int id;
        String name;

        public MyKey(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // Overriding hashCode() to create collisions for demonstration
        @Override
        public int hashCode() {
            // This simple hashCode will cause collisions for different names
            // but the same ID, demonstrating the role of equals()
            return Integer.hashCode(id);
        }

        // Overriding equals() for proper key comparison
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            // here getClass() returns a Class Object that represents runtime class of the current object
            MyKey myKey = (MyKey) obj;
            return id == myKey.id && Objects.equals(name, myKey.name);
        }

        @Override
        public String toString() {
            return "MyKey{id=" + id + ", name='" + name + "'}";
        }
    }

    public static void main(String[] args){
        System.out.println("\n---  HashMap: Internal Handling ---");
        HashMap<MyKey, String> hashMap = new HashMap<>();

        // Demonstrate put, get, and collision handling
        MyKey key1 = new MyKey(101, "Alice");
        MyKey key2 = new MyKey(102, "Bob");
        MyKey key3 = new MyKey(101, "Charlie"); // Same ID as key1, different name -> collision in bucket
        MyKey key4 = new MyKey(101, "Alice"); // Same ID and name as key1 -> considered equal

        hashMap.put(key1, "Value A");
        hashMap.put(key2, "Value B");
        System.out.println("HashMap after adding key1, key2: " + hashMap);
        System.out.println("Value for key1: " + hashMap.get(key1));
        System.out.println("Value for key2: " + hashMap.get(key2));

        // Adding key3, which has the same hashCode as key1 but is not equal
        // This will go into the same bucket as key1, forming a linked list/tree
        hashMap.put(key3, "Value C");
        System.out.println("HashMap after adding key3 (collision with key1's bucket): " + hashMap);
        System.out.println("Value for key1: " + hashMap.get(key1)); // Still Value A
        System.out.println("Value for key3: " + hashMap.get(key3)); // Value C

        // Adding key4, which is equal to key1. This will replace key1's value.
        hashMap.put(key4, "Value A_Updated");
        System.out.println("HashMap after adding key4 (equal to key1): " + hashMap);
        System.out.println("Value for key1 (now updated by key4): " + hashMap.get(key1));
        System.out.println("Value for key4: " + hashMap.get(key4)); // Same as key1's value

        System.out.println("HashMap contains key1: " + hashMap.containsKey(key1));
        System.out.println("HashMap contains key4: " + hashMap.containsKey(key4)); // True, as it's equal to key1

    }
}
