package daoimpl;

import dao.BookIssueDao;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.BookIssue;
import util.DBConnection;

public class BookIssueDaoImpl implements BookIssueDao {

    @Override
    public void issueBook(BookIssue bookIssue) {
        String sql1 = "INSERT INTO book_issue(member_id, book_id, issue_date, due_date, return_date, status) VALUES(?,?,?,?,?,?)";
        String sql2 = "UPDATE book SET available_quantity = available_quantity - 1 " + "WHERE book_id = ? AND available_quantity > 0";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps1 = con.prepareStatement(sql1); PreparedStatement ps2 = con.prepareStatement(sql2)) {

            ps1.setInt(1, bookIssue.getMemberId());
            ps1.setInt(2, bookIssue.getBookId());

            ps1.setDate(3, bookIssue.getIssueDate());
            ps1.setDate(4, bookIssue.getDueDate());

            if (bookIssue.getReturnDate() != null) {
                ps1.setDate(5, bookIssue.getReturnDate());
            } else {
                ps1.setNull(5, Types.DATE);
            }

            ps1.setString(6, bookIssue.getStatus());

            ps2.setInt(1, bookIssue.getBookId());

            int updatedRows = ps2.executeUpdate();

            if (updatedRows == 0) {
                System.out.println("Book Not Available");
                return;
            }

            int rows = ps1.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Issued Successfully");
            }

        } catch (Exception e) {
            System.out.println("************************************************");
        }
    }

    @Override
    public void returnBook(int issueId) {

        String sql1 = "SELECT book_id, status FROM book_issue WHERE issue_id=?";

        String sql2 = "UPDATE book_issue SET return_date=?, status=? WHERE issue_id=?";

        String sql3 = "UPDATE book SET available_quantity = available_quantity + 1 WHERE book_id=?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps1 = con.prepareStatement(sql1); PreparedStatement ps2 = con.prepareStatement(sql2); PreparedStatement ps3 = con.prepareStatement(sql3)) {

            // Get book_id and status
            ps1.setInt(1, issueId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("Issue Record Not Found");
                return;
            }

            int bookId = rs.getInt("book_id");
            String status = rs.getString("status");

            // Check already returned
            if ("RETURNED".equals(status)) {
                System.out.println("Book Already Returned");
                return;
            }

            // Update issue record
            ps2.setDate(1, new Date(System.currentTimeMillis()));
            ps2.setString(2, "RETURNED");
            ps2.setInt(3, issueId);

            int rows = ps2.executeUpdate();

            // Increase available quantity
            ps3.setInt(1, bookId);
            ps3.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Returned Successfully");
            }

        } catch (Exception e) {
            System.out.println("***********************************************");
        }
    }

    @Override
    public BookIssue getIssueById(int issueId) {
        String sql = "SELECT * FROM book_issue WHERE issue_id=?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, issueId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                BookIssue issue = new BookIssue();

                issue.setIssueId(rs.getInt("issue_id"));
                issue.setMemberId(rs.getInt("member_id"));
                issue.setBookId(rs.getInt("book_id"));
                issue.setIssueDate(rs.getDate("issue_date"));
                issue.setDueDate(rs.getDate("due_date"));
                issue.setReturnDate(rs.getDate("return_date"));
                issue.setStatus(rs.getString("status"));

                return issue;
            }

        } catch (Exception e) {
            System.out.println("***********************************************");
        }

        return null;
    }

    @Override
    public List<BookIssue> getAllIssuedBooks() {
        List<BookIssue> issues = new ArrayList<>();

        String sql = "SELECT * FROM book_issue";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                BookIssue issue = new BookIssue();

                issue.setIssueId(rs.getInt("issue_id"));
                issue.setMemberId(rs.getInt("member_id"));
                issue.setBookId(rs.getInt("book_id"));
                issue.setIssueDate(rs.getDate("issue_date"));
                issue.setDueDate(rs.getDate("due_date"));
                issue.setReturnDate(rs.getDate("return_date"));
                issue.setStatus(rs.getString("status"));

                issues.add(issue);
            }

        } catch (Exception e) {
            System.out.println("***********************************************");
        }

        return issues;
    }

}
