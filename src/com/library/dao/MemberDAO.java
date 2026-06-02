package com.library.dao;

import com.library.model.*;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public boolean registerMember(Member member)
            throws SQLException {

        String sql =
        "INSERT INTO Member(name,email,phone,member_type) VALUES(?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPhone());

            if(member instanceof StudentMember)
                ps.setString(4,"STUDENT");
            else
                ps.setString(4,"FACULTY");

            return ps.executeUpdate() > 0;
        }
    }

    public Member getMemberById(int id)
            throws SQLException {

        String sql =
                "SELECT * FROM Member WHERE member_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                String type =
                        rs.getString("member_type");

                Member member;

                if(type.equalsIgnoreCase("STUDENT")) {

                    member = new StudentMember();
                } else {

                    member = new FacultyMember();
                }

                member.setMemberId(
                        rs.getInt("member_id"));

                member.setName(
                        rs.getString("name"));

                member.setEmail(
                        rs.getString("email"));

                member.setPhone(
                        rs.getString("phone"));

                return member;
            }
        }

        return null;
    }

    public List<Member> getAllMembers()
            throws SQLException {

        List<Member> members =
                new ArrayList<>();

        String sql = "SELECT * FROM Member";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ResultSet rs =
                    ps.executeQuery()) {

            while(rs.next()) {

                String type =
                        rs.getString("member_type");

                Member member;

                if(type.equalsIgnoreCase("STUDENT"))
                    member = new StudentMember();
                else
                    member = new FacultyMember();

                member.setMemberId(
                        rs.getInt("member_id"));

                member.setName(
                        rs.getString("name"));

                member.setEmail(
                        rs.getString("email"));

                member.setPhone(
                        rs.getString("phone"));

                members.add(member);
            }
        }

        return members;
    }
}