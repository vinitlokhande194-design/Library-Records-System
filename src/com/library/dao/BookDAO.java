package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public boolean addBook(Book book) throws SQLException {

        String sql =
                "INSERT INTO Book(title,author,isbn,available) VALUES(?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setBoolean(4, true);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Book> getAllBooks() throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM Book";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                books.add(
                        new Book(
                                rs.getInt("book_id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("isbn"),
                                rs.getBoolean("available")
                        )
                );
            }
        }

        return books;
    }

    public Book getBookById(int bookId) throws SQLException {

        String sql =
                "SELECT * FROM Book WHERE book_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("available")
                );
            }
        }

        return null;
    }

    public List<Book> searchBook(String keyword)
            throws SQLException {

        List<Book> books = new ArrayList<>();

        String sql =
                "SELECT * FROM Book WHERE title LIKE ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                books.add(
                        new Book(
                                rs.getInt("book_id"),
                                rs.getString("title"),
                                rs.getString("author"),
                                rs.getString("isbn"),
                                rs.getBoolean("available")
                        )
                );
            }
        }

        return books;
    }

    public boolean updateAvailability(
            int bookId,
            boolean available)
            throws SQLException {

        String sql =
                "UPDATE Book SET available=? WHERE book_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, available);
            ps.setInt(2, bookId);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(int bookId)
            throws SQLException {

        String sql =
                "DELETE FROM Book WHERE book_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            return ps.executeUpdate() > 0;
        }
    }
}