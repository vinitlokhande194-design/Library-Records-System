package daoimpl;

import dao.ReportDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.OverdueReport;
import util.DBConnection;

public class ReportDaoImpl implements ReportDao {

    @Override
    public List<OverdueReport> getMemberOverdueReport(
            int memberId) {

        List<OverdueReport> reports
                = new ArrayList<>();

        String sql
                = "SELECT m.member_id, m.member_name, "
                + "m.email, m.phone, "
                + "bi.issue_id, "
                + "b.book_id, b.title, "
                + "bi.issue_date, bi.due_date, "
                + "DATEDIFF(CURDATE(), bi.due_date) "
                + "AS late_days, "
                + "f.fine_amount, "
                + "f.paid_status "
                + "FROM member m "
                + "JOIN book_issue bi "
                + "ON m.member_id = bi.member_id "
                + "JOIN book b "
                + "ON bi.book_id = b.book_id "
                + "LEFT JOIN fine f "
                + "ON bi.issue_id = f.issue_id "
                + "WHERE m.member_id = ? "
                + "AND bi.status = 'ISSUED' "
                + "AND bi.due_date < CURDATE()";

        try (Connection con
                = DBConnection.getConnection(); PreparedStatement ps
                = con.prepareStatement(sql)) {

            ps.setInt(1, memberId);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                OverdueReport r
                        = new OverdueReport();

                r.setMemberId(
                        rs.getInt("member_id"));

                r.setMemberName(
                        rs.getString(
                                "member_name"));

                r.setEmail(
                        rs.getString("email"));

                r.setPhone(
                        rs.getString("phone"));

                r.setIssueId(
                        rs.getInt("issue_id"));

                r.setBookId(
                        rs.getInt("book_id"));

                r.setTitle(
                        rs.getString("title"));

                r.setIssueDate(
                        rs.getDate(
                                "issue_date"));

                r.setDueDate(
                        rs.getDate(
                                "due_date"));

                r.setLateDays(
                        rs.getInt(
                                "late_days"));

                r.setFineAmount(
                        rs.getDouble(
                                "fine_amount"));

                r.setPaidStatus(
                        rs.getString(
                                "paid_status"));

                reports.add(r);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return reports;
    }
}
