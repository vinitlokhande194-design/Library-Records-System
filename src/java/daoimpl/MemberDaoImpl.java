package daoimpl;

import dao.MemberDao;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.FacultyMember;
import model.Member;
import model.StudentMember;
import util.DBConnection;

public class MemberDaoImpl implements MemberDao {

    @Override
    public void addMember(Member member) {
        String sql = "INSERT INTO member(member_name,email,phone,member_type,registration_date) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getMemberName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getMemberType());
            ps.setDate(5, member.getRegistrationDate()); // now java.sql.Date

            ps.executeUpdate();
            System.out.println("Member Added Successfully");

        } catch (Exception e) {
            System.out.println("Unable to add member. Please try again.");
        }
    }

    @Override
    public void updateMember(Member member) {
        String sql = "UPDATE member SET member_name=?, email=?, phone=?, member_type=?, registration_date=? WHERE member_id=?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getMemberName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getMemberType());
            ps.setDate(5, member.getRegistrationDate());
            ps.setInt(6, member.getMemberId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Member Updated Successfully");
            } else {
                System.out.println("Member Not Found");
            }

        } catch (Exception e) {
            System.out.println("Unable to update member. Please try again.");
        }
    }

    @Override
    public void deleteMember(int memberId) {
        String sql = "DELETE FROM member WHERE member_id = ?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, memberId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Member Deleted Successfully");
            } else {
                System.out.println("Member Not Found");
            }

        } catch (Exception e) {
            System.out.println("Unable to delete member. Please try again.");
        }
    }

    @Override
    public Member getMemberById(int memberId) {
        String sql = "SELECT * FROM member WHERE member_id = ?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, memberId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Member member;

                if ("STUDENT".equals(rs.getString("member_type"))) {
                    member = new StudentMember();
                } else {
                    member = new FacultyMember();
                }

                member.setMemberId(rs.getInt("member_id"));
                member.setMemberName(rs.getString("member_name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setMemberType(rs.getString("member_type"));
                member.setRegistrationDate(rs.getDate("registration_date"));

                return member;
            }

        } catch (Exception e) {
            System.out.println("Unable to get member. Please try again.");
        }
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();

        String sql = "SELECT * FROM member";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Member member;

                if ("STUDENT".equals(rs.getString("member_type"))) {
                    member = new StudentMember();
                } else {
                    member = new FacultyMember();
                }

                member.setMemberId(rs.getInt("member_id"));
                member.setMemberName(rs.getString("member_name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setMemberType(rs.getString("member_type"));
                member.setRegistrationDate(rs.getDate("registration_date"));
                
                members.add(member);
            }
        } catch (Exception e) {
            System.out.println("Unable to get all member. Please try again.");
        }
        return members;
    }

}
