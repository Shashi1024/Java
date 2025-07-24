package Codes.Generics;

import java.util.ArrayList;
import java.util.List;

interface Repository<T> {
    void add(T item);
    T get(int index);
    int size();
}

public class GenericInterface implements Repository<String> {
    private List<String> items = new ArrayList<>();

    @Override
    public void add(String item) {
        items.add(item);
        System.out.println("Added: " + item);
    }

    @Override
    public String get(int index) {
        return items.get(index);
    }

    @Override
    public int size() {
        return items.size();
    }

    public static void main(String[] args) {
        Repository<String> stringRepo = new GenericInterface();
        stringRepo.add("Item One");
        stringRepo.add("Item Two");

        System.out.println("Repository size: " + stringRepo.size());
        System.out.println("Item at index 0: " + stringRepo.get(0));

        // You can also create implementations for other types
        // Repository<Integer> intRepo = new IntegerRepository(); // Requires IntegerRepository class
    }
}
