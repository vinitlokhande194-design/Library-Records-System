package com.library.service;

import com.library.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportService {

    public void showOverdueBooks() {

        String sql =
        """
        SELECT
        m.name,
        b.title,
        bi.due_date
        FROM BookIssue bi
        JOIN Member m
        ON bi.member_id=m.member_id
        JOIN Book b
        ON bi.book_id=b.book_id
        WHERE bi.return_date IS NULL
        AND bi.due_date < CURDATE()
        """;

        try(Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()) {

            System.out.println(
                    "\n----- OVERDUE BOOKS -----");

            while(rs.next()) {

                System.out.println(
                        rs.getString("name")
                        + " | "
                        + rs.getString("title")
                        + " | "
                        + rs.getDate("due_date")
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void showTopBorrowedBooks() {

        String sql =
        """
        SELECT
        b.title,
        COUNT(*) total
        FROM BookIssue bi
        JOIN Book b
        ON bi.book_id=b.book_id
        GROUP BY b.title
        ORDER BY total DESC
        LIMIT 5
        """;

        try(Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()) {

            System.out.println(
                    "\n----- TOP 5 BOOKS -----");

            while(rs.next()) {

                System.out.println(
                        rs.getString("title")
                        + " -> "
                        + rs.getInt("total")
                        + " issues"
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}