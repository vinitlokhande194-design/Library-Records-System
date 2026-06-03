package daoimpl;

import dao.FineDao;
import java.sql.*;
import java.util.List;
import model.Fine;
import util.DBConnection;

public class FineDaoImpl implements FineDao {

    @Override
    public void generateOverdueFines() {

        String selectSql = "SELECT issue_id, due_date FROM book_issue WHERE status='ISSUED' AND due_date < CURDATE()";
        String checkSql = "SELECT fine_id FROM fine WHERE issue_id=?";
        String insertSql = "INSERT INTO fine(issue_id, fine_amount, paid_status) VALUES(?,?,?)";
        String updateSql = "UPDATE fine SET fine_amount=? WHERE issue_id=?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement psSelect = con.prepareStatement(selectSql); 
                ResultSet rs = psSelect.executeQuery()) {
            
            while (rs.next()) {
                
                int issueId = rs.getInt("issue_id");
                Date dueDate = rs.getDate("due_date");
                Date today = new Date(System.currentTimeMillis());
                long diff = today.getTime() - dueDate.getTime();
                long lateDays = diff / (1000 * 60 * 60 * 24);
                double fineAmount = lateDays * 10.0;
                
                PreparedStatement psCheck = con.prepareStatement(checkSql);
                psCheck.setInt(1, issueId);
                ResultSet checkRs = psCheck.executeQuery();

                if (checkRs.next()) {
                    PreparedStatement psUpdate = con.prepareStatement(updateSql);
                    psUpdate.setDouble(1, fineAmount);
                    psUpdate.setInt(2, issueId);
                    psUpdate.executeUpdate();
                } else {
                    PreparedStatement psInsert = con.prepareStatement(insertSql);
                    psInsert.setInt(1, issueId);
                    psInsert.setDouble(2, fineAmount);
                    psInsert.setString(3, "UNPAID");
                    psInsert.executeUpdate();
                }
            }
            System.out.println("Overdue Fines Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public List<Fine> getAllFines() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
