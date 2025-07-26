package Codes.Collections;

// creating a custom collection of items implementing the iterable
// consider a data structure (named BookShelf) which must store a collection of books 
// also implements iterable allowing us to iterate over the collection - BookShelf

class Book{
    private String name;

    public Book(String n){
        this.name = n;
    }

    @Override
    public String toString(){
        return "Book Name: "+ this.name;
    }
}

class BookShelf implements Iterable<Book>{
    private Book books[];
    private int last = 0;

    public BookShelf(int size){
        this.books = new Book[size];
    }

    public void addBook(Book book){
        if(last < books.length)
            this.books[last++] = book;
    }


}

public class IterableInterface {

    public static void main(String[] args){

    }
}




import java.util.Iterator;
import java.util.NoSuchElementException;

// A custom class representing a collection of Books.
class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "'}";
    }
}

// Our custom collection class that implements the Iterable interface.
public class BookShelf implements Iterable<Book> {
    private Book[] books;
    private int last = 0;

    public BookShelf(int maxSize) {
        this.books = new Book[maxSize];
    }

    public void addBook(Book book) {
        if (last < books.length) {
            this.books[last] = book;
            last++;
        }
    }

    // The core method of the Iterable interface.
    // It must return an instance of an Iterator.
    @Override
    public Iterator<Book> iterator() {
        // We return a new instance of our custom Iterator implementation.
        return new BookShelfIterator(this);
    }

    // A private inner class that implements the Iterator logic for our BookShelf.
    private static class BookShelfIterator implements Iterator<Book> {
        private BookShelf bookShelf;
        private int index = 0;

        public BookShelfIterator(BookShelf bookShelf) {
            this.bookShelf = bookShelf;
        }

        // Checks if there is a next element to iterate over.
        @Override
        public boolean hasNext() {
            return index < bookShelf.last;
        }

        // Returns the next element in the iteration.
        @Override
        public Book next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Book book = bookShelf.books[index];
            index++;
            return book;
        }
    }

    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(5);
        shelf.addBook(new Book("The Lord of the Rings"));
        shelf.addBook(new Book("Dune"));
        shelf.addBook(new Book("Foundation"));

        // Because BookShelf implements Iterable, we can use it in a for-each loop!
        System.out.println("Iterating through the bookshelf:");
        for (Book book : shelf) {
            System.out.println(book);
        }
    }
}
