package Codes.Generics;

public class GenericClass<T> {
    private T content;

    public void setContent(T content){
        this.content = content;
    }

    public T getContent(){
        return this.content;
    }


    public static void main(String[] args){
        GenericClass<Integer> gc = new GenericClass<>();
        gc.setContent(123);

        System.out.println(gc.getContent());


        GenericClass<String> stringBox = new GenericClass<>();
        stringBox.setContent("Hello Generics!");
        System.out.println("String Box Content: " + stringBox.getContent());
    }
}
