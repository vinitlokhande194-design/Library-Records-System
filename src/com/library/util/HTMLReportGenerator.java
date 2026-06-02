package com.library.util;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HTMLReportGenerator {

    public static void generateOverdueReport() {

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

        try(
            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            FileWriter writer =
                    new FileWriter(
                    "output/overdue_report.html")
        ) {

            writer.write("""
            <html>
            <head>
            <title>Overdue Report</title>

            <style>

            body{
                font-family:Arial;
            }

            table{
                border-collapse:collapse;
                width:80%;
            }

            th,td{
                border:1px solid black;
                padding:8px;
            }

            th{
                background:#dddddd;
            }

            </style>

            </head>

            <body>

            <h2>Overdue Books Report</h2>

            <table>

            <tr>
            <th>Member</th>
            <th>Book</th>
            <th>Due Date</th>
            </tr>
            """);

            while(rs.next()) {

                writer.write(
                "<tr>" +
                "<td>" +
                rs.getString("name") +
                "</td>" +

                "<td>" +
                rs.getString("title") +
                "</td>" +

                "<td>" +
                rs.getDate("due_date") +
                "</td>" +

                "</tr>");
            }

            writer.write("""
            </table>
            </body>
            </html>
            """);

            System.out.println(
            "HTML Report Generated Successfully");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}