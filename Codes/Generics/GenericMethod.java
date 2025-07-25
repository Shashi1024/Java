package Codes.Generics;

public class GenericMethod {
    
    public static <E> void printArray(E arr[]){
        for(E i: arr)
            System.out.print(i+" ");
    }

    public static <T extends Comparable<T>> T findMax(T x, T y) {
        // here extends Comparable is use because T must implement the Comparable interface (it has .compareTO() method)
        return x.compareTo(y) > 0 ? x : y;
    }

    public static void main(String[] args){
        Integer[] intArray = {1, 2, 3, 4, 5};
        printArray(intArray);


        String[] stringArray = {"Apple", "Banana", "Cherry"};
        System.out.print("String Array: ");
        printArray(stringArray);
        System.out.println();


        Integer maxInt = findMax(10, 20);
        System.out.println("Max of 10 and 20: " + maxInt);
    }
}
