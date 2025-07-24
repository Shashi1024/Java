package Codes.Generics;

import java.util.*;

public class Generics {

    static class Box <T>{
        private T content;

        public Box(T content){
            this.content = content;
        }

        public T getContent(){
            return this.content;
        }

        public void setContent(T content){
            this.content = content;
        }

        @Override
        public String toString() {
            return "Box containing: " + content;
        }
    }



    public static <T> void printArray(T[] array) {
        System.out.print("Array elements: ");
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> T findMax(T x, T y) {
        return x.compareTo(y) > 0 ? x : y;
    }


    public static void processNumbers(List<? extends Number> list) {
        System.out.println("\nProcessing numbers (Upper Bounded Wildcard):");
        double sum = 0;
        for (Number n : list) {
            System.out.println("  Found: " + n);
            sum += n.doubleValue();
        }
        System.out.println("  Total sum: " + sum);
    }

    
    public static void addIntegers(List<? super Integer> list) {
        System.out.println("\nAdding integers (Lower Bounded Wildcard):");
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("  Added 10, 20, 30 to the list.");
        Object obj = list.get(0);
        System.out.println("  First element (retrieved as Object): " + obj);
    }

    
    public static void printList(List<?> list) {
        System.out.println("\nPrinting list (Unbounded Wildcard):");
        System.out.print("  List elements: ");
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {

        System.out.println("--- Demonstrating Generic Class (Box) ---");
        Box<String> stringBox = new Box<>("Hello Generics!");
        System.out.println(stringBox.getContent());

        Box<Integer> integerBox = new Box<>(123);
        System.out.println(integerBox.getContent());

        System.out.println("\n--- Demonstrating Generic Methods ---");

        Integer[] intArray = {1, 2, 3, 4, 5};
        printArray(intArray);

        String[] stringArray = {"Apple", "Banana", "Cherry"};
        printArray(stringArray);

        Integer maxInt = findMax(10, 20);
        System.out.println("Max of 10 and 20: " + maxInt);

        String maxString = findMax("Java", "Python");
        System.out.println("Max of 'Java' and 'Python': " + maxString);

        System.out.println("\n--- Demonstrating Bounded Type Parameters (Wildcards) ---");
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        processNumbers(integerList);

        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        processNumbers(doubleList);

        List<Number> numberList = new ArrayList<>();
        addIntegers(numberList);
        System.out.println("Number list after adding integers: " + numberList);

        List<Object> objectList = new ArrayList<>();
        addIntegers(objectList);
        System.out.println("Object list after adding integers: " + objectList);

        printList(integerList);
        printList(stringBox.getContent().getClass().getName().equals("java.lang.String") ? Arrays.asList(stringBox.getContent()) : new ArrayList<>());
        printList(doubleList);

        System.out.println("\n--- Conceptual Demonstration of Type Erasure ---");
        List<String> ls = new ArrayList<>();
        List<Integer> li = new ArrayList<>();

        System.out.println("ls.getClass() == li.getClass(): " + (ls.getClass() == li.getClass()));
        System.out.println("Class of ls: " + ls.getClass());
        System.out.println("Class of li: " + li.getClass());

        System.out.println("\n--- Demonstrating Diamond Operator ---");

        List<String> oldStyleList = new ArrayList<String>();
        oldStyleList.add("Old Style");
        System.out.println("Old style list: " + oldStyleList);

        List<String> newStyleList = new ArrayList<>();
        newStyleList.add("New Style");
        System.out.println("New style list: " + newStyleList);

        Box<Double> doubleBox = new Box<>(3.14);
        System.out.println(doubleBox);
    }
}

