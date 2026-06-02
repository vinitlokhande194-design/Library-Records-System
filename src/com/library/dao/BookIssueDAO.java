package com.library.dao;

import com.library.model.BookIssue;
import com.library.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookIssueDAO {

    public boolean issueBook(BookIssue issue)
            throws SQLException {

        String sql =
        "INSERT INTO BookIssue(book_id,member_id,issue_date,due_date) VALUES(?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, issue.getBookId());
            ps.setInt(2, issue.getMemberId());

            ps.setDate(
                    3,
                    Date.valueOf(issue.getIssueDate()));

            ps.setDate(
                    4,
                    Date.valueOf(issue.getDueDate()));

            return ps.executeUpdate() > 0;
        }
    }

    public boolean returnBook(
            int issueId,
            LocalDate returnDate)
            throws SQLException {

        String sql =
                "UPDATE BookIssue SET return_date=? WHERE issue_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setDate(
                    1,
                    Date.valueOf(returnDate));

            ps.setInt(2, issueId);

            return ps.executeUpdate() > 0;
        }
    }

    public List<BookIssue> getIssuedBooks()
            throws SQLException {

        List<BookIssue> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM BookIssue";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ResultSet rs =
                    ps.executeQuery()) {

            while(rs.next()) {

                BookIssue issue =
                        new BookIssue();

                issue.setIssueId(
                        rs.getInt("issue_id"));

                issue.setBookId(
                        rs.getInt("book_id"));

                issue.setMemberId(
                        rs.getInt("member_id"));

                issue.setIssueDate(
                        rs.getDate("issue_date")
                                .toLocalDate());

                issue.setDueDate(
                        rs.getDate("due_date")
                                .toLocalDate());

                Date returnDate =
                        rs.getDate("return_date");

                if(returnDate != null)
                    issue.setReturnDate(
                            returnDate.toLocalDate());

                list.add(issue);
            }
        }

        return list;
    }
}