package Codes.Collections;

public class CustomList {
    
}



import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// A simplified implementation of the List interface using an array.
public class SimpleArrayList<E> implements List<E> {

    private Object[] elementData;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public SimpleArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    // --- Key Implemented Methods ---

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();
        elementData[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elementData[index];
    }

    @Override
    public void clear() {
        // Clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    
    // A very basic implementation of remove.
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        @SuppressWarnings("unchecked")
        E oldValue = (E) elementData[index];
        
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // clear to let GC do its work
        
        return oldValue;
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, size * 2);
        }
    }
    
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i == size - 1) {
                return sb.append(']').toString();
            }
            sb.append(", ");
        }
        return "[]"; // Should not be reached
    }

    // --- Methods we choose not to implement for this example ---
    // A full implementation would require coding logic for all of these.

    @Override
    public boolean contains(Object o) { throw new UnsupportedOperationException(); }
    @Override
    public Iterator<E> iterator() { throw new UnsupportedOperationException(); }
    @Override
    public Object[] toArray() { throw new UnsupportedOperationException(); }
    @Override
    public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }
    @Override
    public boolean remove(Object o) { throw new UnsupportedOperationException(); }
    @Override
    public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override
    public boolean addAll(Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override
    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override
    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override
    public E set(int index, E element) { throw new UnsupportedOperationException(); }
    @Override
    public void add(int index, E element) { throw new UnsupportedOperationException(); }
    @Override
    public int indexOf(Object o) { throw new UnsupportedOperationException(); }
    @Override
    public int lastIndexOf(Object o) { throw new UnsupportedOperationException(); }
    @Override
    public ListIterator<E> listIterator() { throw new UnsupportedOperationException(); }
    @Override
    public ListIterator<E> listIterator(int index) { throw new UnsupportedOperationException(); }
    @Override
    public List<E> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }

    public static void main(String[] args) {
        List<String> myList = new SimpleArrayList<>();
        myList.add("First");
        myList.add("Second");
        myList.add("Third");

        System.out.println("My custom list: " + myList);
        System.out.println("Element at index 1: " + myList.get(1));
        System.out.println("Size of list: " + myList.size());
        
        myList.remove(1);
        System.out.println("List after removing element at index 1: " + myList);
        
        myList.clear();
        System.out.println("List after clearing: " + myList);
        System.out.println("Is list empty? " + myList.isEmpty());
    }
}
