package Codes.Generics;

public class MultipleTypeParams {
    static public class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void printPair() {
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }  

    public static void main(String[] args) {
        // Create a Pair with String key and Integer value
        Pair<String, Integer> studentGrade = new Pair<>("Alice", 95);
        studentGrade.printPair();

        // Create a Pair with Integer key and String value
        Pair<Integer, String> productCode = new Pair<>(101, "Laptop");
        productCode.printPair();

        // Create a Pair with different types
        Pair<Boolean, Double> sensorReading = new Pair<>(true, 25.7);
        sensorReading.printPair();
    }
}
