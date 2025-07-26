package Codes.Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }

    private static class BookShelfIterator implements Iterator<Book>{
        private BookShelf booksShelf;
        private int index = 0;

        public BookShelfIterator(BookShelf bs){
            this.booksShelf = bs;
        }

        @Override
        public boolean hasNext(){
            return index < booksShelf.last;
        }

        @Override
        public Book next(){
            if(!hasNext())
                throw new NoSuchElementException();
            
            Book book = booksShelf.books[index++];
            return book;
        }

        @Override
        // actually this is the default implementation of remove() given in the Iterator interface
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
}

public class IterableInterface {
    public static void main(String[] args){
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
