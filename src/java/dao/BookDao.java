package dao;

import java.util.List;
import model.Book;

public interface BookDao {

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int bookId);

    Book getBookById(int bookId);

    List<Book> getAllBooks();
}
