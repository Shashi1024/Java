package Codes.Generics;

import java.util.ArrayList;
import java.util.List;

public class Wildcards {
    // Method that can print elements of a List of any type (Unbounded Wildcard)
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }


    // UpperBounded Wildcard (<? extends T>)
    // Method that can process a list of Numbers or any of its subclasses (Integer, Double, etc.)
    // <? extends T> means "any type that is T or a subclass of T"
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }


    // LowerBounded Wildcard (<? super T>)
    // Method that can add Integers to a list that can hold Integers or any of its supertypes (Number, Object)
    // <? super T> means "any type that is T or a superclass of T"
    public static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
        // You can add Integer or its subtypes but the list must be of super type of Integer
        // list.add(new Double(5.0)); // Compile-time error
    }

    public static void main(String[] args){
        // UnBounded Wildcard
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        printList(intList);
        

        // UpperBounded Wildcard (<? extends T>)
        System.out.println("Sum of Integer List: " + sumOfList(intList)); //


        intList.clear();
        // LowerBounded Wildcard (<? super T>)
        addIntegers(intList);
        System.out.println("Integer List after adding: " + intList); // will also work with List<Number> , List<Object>

        List<Object> objectList = new ArrayList<>();
        addIntegers(objectList); // Works with List<Object>
        System.out.println("Object List after adding: " + objectList);
    }

    
}
