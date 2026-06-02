package com.library.dao;

import com.library.model.Fine;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FineDAO {

    public boolean addFine(Fine fine)
            throws SQLException {

        String sql =
        "INSERT INTO Fine(issue_id,amount,paid) VALUES(?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, fine.getIssueId());
            ps.setDouble(2, fine.getAmount());
            ps.setBoolean(3, fine.isPaid());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Fine> getAllFines()
            throws SQLException {

        List<Fine> fines =
                new ArrayList<>();

        String sql = "SELECT * FROM Fine";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ResultSet rs =
                    ps.executeQuery()) {

            while(rs.next()) {

                fines.add(
                        new Fine(
                                rs.getInt("fine_id"),
                                rs.getInt("issue_id"),
                                rs.getDouble("amount"),
                                rs.getBoolean("paid")
                        )
                );
            }
        }

        return fines;
    }

    public boolean markPaid(int fineId)
            throws SQLException {

        String sql =
                "UPDATE Fine SET paid=true WHERE fine_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(1, fineId);

            return ps.executeUpdate() > 0;
        }
    }
}