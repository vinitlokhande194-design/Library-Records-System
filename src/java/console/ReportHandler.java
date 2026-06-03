package console;

import dao.ReportDao;
import daoimpl.ReportDaoImpl;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import model.OverdueReport;

public class ReportHandler {

    private static Scanner sc = new Scanner(System.in);

    public static void reportMenu() {

        while (true) {

            System.out.println("\n===== REPORT MENU =====");
            System.out.println("1. Single Member Overdue Report");
            System.out.println("2. All Overdue Books Report");
            System.out.println("3. Top Defaulters Report");
            System.out.println("4. Back");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    singleMemberReport();
                    break;

//                case 2:
//                    allOverdueReport();
//                    break;
//
//                case 3:
//                    topDefaultersReport();
//                    break;
                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    private static void singleMemberReport() {

        try {

            System.out.print("Enter Member ID : ");
            int memberId = sc.nextInt();

            ReportDao dao = new ReportDaoImpl();

            List<OverdueReport> reports
                    = dao.getMemberOverdueReport(memberId);

            if (reports.isEmpty()) {

                System.out.println(
                        "No Overdue Books Found");

                return;
            }

            FileWriter fw
                    = new FileWriter(
                            "memberOverdueReport.html");

            fw.write("<html>");

            fw.write("<head>");

            fw.write("<title>Member Overdue Report</title>");

            fw.write("<link "
                    + "href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' "
                    + "rel='stylesheet'>");

            fw.write("<link rel='stylesheet' "
                    + "href='memberOverdueReport.css'>");

            fw.write("</head>");

            fw.write("<body class='bg-light'>");

            fw.write("<div class='container mt-4'>");

            OverdueReport member
                    = reports.get(0);

            fw.write("<h2 class='text-center text-primary mb-4'>");
            fw.write("LIBRARY MEMBER OVERDUE REPORT");
            fw.write("</h2>");

            fw.write("<div class='card mb-4'>");

            fw.write("<div class='card-header bg-primary text-white'>");
            fw.write("Member Information");
            fw.write("</div>");

            fw.write("<div class='card-body'>");

            fw.write("<p><strong>Member ID :</strong> "
                    + member.getMemberId()
                    + "</p>");

            fw.write("<p><strong>Member Name :</strong> "
                    + member.getMemberName()
                    + "</p>");

            fw.write("<p><strong>Email :</strong> "
                    + member.getEmail()
                    + "</p>");

            fw.write("<p><strong>Phone :</strong> "
                    + member.getPhone()
                    + "</p>");

            fw.write("</div>");

            fw.write("</div>");

            fw.write("<table "
                    + "class='table table-bordered "
                    + "table-striped table-hover'>");

            fw.write("<thead class='table-dark'>");

            fw.write("<tr>");

            fw.write("<th>Issue ID</th>");
            fw.write("<th>Book ID</th>");
            fw.write("<th>Book Name</th>");
            fw.write("<th>Issue Date</th>");
            fw.write("<th>Due Date</th>");
            fw.write("<th>Late Days</th>");
            fw.write("<th>Fine Amount</th>");
            fw.write("<th>Fine Status</th>");

            fw.write("</tr>");

            fw.write("</thead>");

            fw.write("<tbody>");

            double totalFine = 0;

            for (OverdueReport r : reports) {

                totalFine += r.getFineAmount();

                fw.write("<tr>");

                fw.write("<td>"
                        + r.getIssueId()
                        + "</td>");

                fw.write("<td>"
                        + r.getBookId()
                        + "</td>");

                fw.write("<td>"
                        + r.getTitle()
                        + "</td>");

                fw.write("<td>"
                        + r.getIssueDate()
                        + "</td>");

                fw.write("<td>"
                        + r.getDueDate()
                        + "</td>");

                fw.write("<td>"
                        + r.getLateDays()
                        + "</td>");

                fw.write("<td>₹"
                        + r.getFineAmount()
                        + "</td>");

                fw.write("<td>"
                        + r.getPaidStatus()
                        + "</td>");

                fw.write("</tr>");
            }

            fw.write("</tbody>");

            fw.write("</table>");

            fw.write("<div class='card mt-4'>");

            fw.write("<div class='card-header bg-danger text-white'>");
            fw.write("Summary");
            fw.write("</div>");

            fw.write("<div class='card-body'>");

            fw.write("<h5>Total Overdue Books : "
                    + reports.size()
                    + "</h5>");

            fw.write("<h5>Total Fine Amount : ₹"
                    + totalFine
                    + "</h5>");

            fw.write("</div>");

            fw.write("</div>");

            fw.write("<div class='text-center mt-4'>");

            fw.write("<button "
                    + "class='btn btn-success me-2' "
                    + "onclick='window.print()'>");
            fw.write("Print Report");
            fw.write("</button>");

            fw.write("<a href='mailto:"
                    + member.getEmail()
                    + "?subject=Library Overdue Notice'>");

            fw.write("<button "
                    + "type='button' "
                    + "class='btn btn-primary'>");

            fw.write("Email Reminder");

            fw.write("</button>");

            fw.write("</a>");

            fw.write("</div>");

            fw.write("</div>");

            fw.write("</body>");

            fw.write("</html>");

            fw.close();

            java.awt.Desktop.getDesktop()
                    .browse(
                            new java.io.File(
                                    "memberOverdueReport.html")
                                    .toURI());

            System.out.println(
                    "Report Generated Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
