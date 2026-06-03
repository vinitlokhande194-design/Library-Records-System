package daoimpl;

import dao.BookDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
import util.DBConnection;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO book(title, author, category, isbn, quantity, available_quantity) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getIsbn());
            ps.setInt(5, book.getQuantity());
            ps.setInt(6, book.getAvailableQuantity());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Added Successfully");
            }

        } catch (Exception e) {
            System.out.println("**************************************");
        }
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE book SET title=?, author=?, category=?, isbn=?, quantity=?, available_quantity=? WHERE book_id=?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, book.getIsbn());
            ps.setInt(5, book.getQuantity());
            ps.setInt(6, book.getAvailableQuantity());
            ps.setInt(7, book.getBookId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Updated Successfully");
            } else {
                System.out.println("Book Not Found");
            }

        } catch (Exception e) {
            System.out.println("**************************************");
        }
    }

    @Override
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM book WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Deleted Successfully");
            } else {
                System.out.println("Book Not Found");
            }

        } catch (Exception e) {
            System.out.println("**************************************");
        }

    }

    @Override
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM book WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setIsbn(rs.getString("isbn"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailableQuantity(rs.getInt("available_quantity"));

                return book;
            }

        } catch (Exception e) {
            System.out.println("**************************************");
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM book";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setIsbn(rs.getString("isbn"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailableQuantity(rs.getInt("available_quantity"));

                books.add(book);
            }

        } catch (Exception ex) {
            System.out.println("**************************************");
        }
        return books;

    }

}
