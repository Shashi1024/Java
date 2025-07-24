package Codes.Generics;

public class GenericMethod {
    
    public static <E> void printArray(E arr[]){
        for(E i: arr)
            System.out.print(i+" ");
    }

    public static void main(String[] args){
        Integer[] intArray = {1, 2, 3, 4, 5};
        printArray(intArray);


        String[] stringArray = {"Apple", "Banana", "Cherry"};
        System.out.print("String Array: ");
        printArray(stringArray);
    }
}
