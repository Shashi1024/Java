package Codes.Generics;

import java.util.ArrayList;
import java.util.List;

public class GenericClass<T> {
    private T content;

    public void setContent(T content){
        this.content = content;
    }

    public T getContent(){
        return this.content;
    }

    @Override
    public String toString(){
        return "Box Containing "+ this.content;
    }


    public static void main(String[] args){
        GenericClass<Integer> gc = new GenericClass<>();
        gc.setContent(123);

        System.out.println(gc.getContent());


        GenericClass<String> stringBox = new GenericClass<>();
        stringBox.setContent("Hello Generics!");
        System.out.println("String Box Content: " + stringBox.getContent());

        System.out.println("\n--- Conceptual Demonstration of Type Erasure ---");
        List<String> ls = new ArrayList<>();
        List<Integer> li = new ArrayList<>();

        System.out.println("ls.getClass() == li.getClass(): " + (ls.getClass() == li.getClass()));
        System.out.println("Class of ls: " + ls.getClass());
        System.out.println("Class of li: " + li.getClass());
    }
}
