package console;

import dao.BookIssueDao;
import daoimpl.BookIssueDaoImpl;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import model.BookIssue;

public class BookIssueHandler {

    private static Scanner sc = new Scanner(System.in);
    private static BookIssueDao bookIssueDao = new BookIssueDaoImpl();

    public static void issueMenu() {

        while (true) {

            System.out.println("\n===== ISSUE MENU =====");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. View All Issued Books");
            System.out.println("4. Back");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    issueBook();
                    break;

                case 2:
                    returnBook();
                    break;

                case 3:
                    viewAllIssues();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    private static void issueBook() {

        try {

            BookIssue issue = new BookIssue();

            System.out.print("Enter Member ID: ");
            issue.setMemberId(sc.nextInt());

            System.out.print("Enter Book ID: ");
            issue.setBookId(sc.nextInt());

            Date issueDate = new Date(System.currentTimeMillis());
            issue.setIssueDate(issueDate);

            long fourteenDays = 14L * 24 * 60 * 60 * 1000;

            Date dueDate = new Date(issueDate.getTime() + fourteenDays);
            issue.setDueDate(dueDate);

            issue.setReturnDate(null);
            issue.setStatus("ISSUED");

            bookIssueDao.issueBook(issue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void returnBook() {

        try {

            System.out.print("Enter Issue ID: ");
            int issueId = sc.nextInt();

            BookIssue issue = bookIssueDao.getIssueById(issueId);

            // Case 1: Issue record not found
            if (issue == null) {
                System.out.println("Issue Record Not Found");
                return;
            }

            // Case 2: Book already returned
            if ("RETURNED".equalsIgnoreCase(issue.getStatus())) {
                System.out.println("Book Already Returned");
                return;
            }

            // Display issue details
            System.out.println("\n----- Issue Details -----");
            System.out.println("Issue ID : " + issue.getIssueId());
            System.out.println("Member ID : " + issue.getMemberId());
            System.out.println("Book ID : " + issue.getBookId());
            System.out.println("Issue Date : " + issue.getIssueDate());
            System.out.println("Due Date : " + issue.getDueDate());

            // Check overdue
            java.sql.Date today
                    = new java.sql.Date(System.currentTimeMillis());

            if (today.after(issue.getDueDate())) {

                long diff
                        = today.getTime() - issue.getDueDate().getTime();

                long lateDays
                        = diff / (1000 * 60 * 60 * 24);

                double fine = lateDays * 10.0; // ₹10 per day

                System.out.println("Late By : "
                        + lateDays + " day(s)");

                System.out.println("Fine Amount : ₹"
                        + fine);
            }

            // Confirmation
            System.out.print("Confirm Return (Y/N): ");
            sc.nextLine(); // consume newline
            String choice = sc.nextLine();

            if (!choice.equalsIgnoreCase("Y")) {
                System.out.println("Return Cancelled");
                return;
            }

            // Return book
            bookIssueDao.returnBook(issueId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewAllIssues() {

        try {

            List<BookIssue> issues = bookIssueDao.getAllIssuedBooks();

            if (issues.isEmpty()) {

                System.out.println("No Issue Records Found");
                return;
            }

            for (BookIssue issue : issues) {

                System.out.println("\n------------------------");
                System.out.println("Issue ID : " + issue.getIssueId());
                System.out.println("Member ID : " + issue.getMemberId());
                System.out.println("Book ID : " + issue.getBookId());
                System.out.println("Issue Date : " + issue.getIssueDate());
                System.out.println("Due Date : " + issue.getDueDate());
                System.out.println("Return Date : " + issue.getReturnDate());
                System.out.println("Status : " + issue.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
