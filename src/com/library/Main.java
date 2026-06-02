package com.library;

import com.library.model.*;
import com.library.service.LibraryService;
import com.library.service.ReportService;
import com.library.util.HTMLReportGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc =
            new Scanner(System.in);

    private static LibraryService libraryService =
            new LibraryService();

    private static ReportService reportService =
            new ReportService();

    public static void main(String[] args) {

        while(true) {

            try {

                System.out.println("\n==============================");
                System.out.println(" LIBRARY MANAGEMENT SYSTEM");
                System.out.println("==============================");

                System.out.println("1. Add Book");
                System.out.println("2. Register Student");
                System.out.println("3. Register Faculty");
                System.out.println("4. Issue Book");
                System.out.println("5. Return Book");
                System.out.println("6. List Books");
                System.out.println("7. Search Book");
                System.out.println("8. Overdue Books");
                System.out.println("9. Generate HTML Report");
                System.out.println("10. Top Borrowed Books");
                System.out.println("0. Exit");

                System.out.print("Enter Choice : ");

                int choice =
                        Integer.parseInt(sc.nextLine());

                switch(choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        registerStudent();
                        break;

                    case 3:
                        registerFaculty();
                        break;

                    case 4:
                        issueBook();
                        break;

                    case 5:
                        returnBook();
                        break;

                    case 6:
                        listBooks();
                        break;

                    case 7:
                        searchBook();
                        break;

                    case 8:
                        reportService.showOverdueBooks();
                        break;

                    case 9:
                        HTMLReportGenerator
                                .generateOverdueReport();
                        break;

                    case 10:
                        reportService.showTopBorrowedBooks();
                        break;

                    case 0:
                        System.exit(0);

                    default:
                        System.out.println(
                                "Invalid Choice");
                }

            } catch(Exception e) {

                System.out.println(
                        "Error : " +
                        e.getMessage());
            }
        }
    }

    private static void addBook()
            throws Exception {

        System.out.print("Title : ");
        String title = sc.nextLine();

        System.out.print("Author : ");
        String author = sc.nextLine();

        System.out.print("ISBN : ");
        String isbn = sc.nextLine();

        Book book =
                new Book(
                        0,
                        title,
                        author,
                        isbn,
                        true
                );

        libraryService.addBook(book);

        System.out.println(
                "Book Added Successfully");
    }

    private static void registerStudent()
            throws Exception {

        StudentMember student =
                new StudentMember();

        System.out.print("Name : ");
        student.setName(
                sc.nextLine());

        System.out.print("Email : ");
        student.setEmail(
                sc.nextLine());

        System.out.print("Phone : ");
        student.setPhone(
                sc.nextLine());

        libraryService.registerMember(
                student);

        System.out.println(
                "Student Registered");
    }

    private static void registerFaculty()
            throws Exception {

        FacultyMember faculty =
                new FacultyMember();

        System.out.print("Name : ");
        faculty.setName(
                sc.nextLine());

        System.out.print("Email : ");
        faculty.setEmail(
                sc.nextLine());

        System.out.print("Phone : ");
        faculty.setPhone(
                sc.nextLine());

        libraryService.registerMember(
                faculty);

        System.out.println(
                "Faculty Registered");
    }

    private static void issueBook()
            throws Exception {

        System.out.print("Book ID : ");
        int bookId =
                Integer.parseInt(
                        sc.nextLine());

        System.out.print("Member ID : ");
        int memberId =
                Integer.parseInt(
                        sc.nextLine());

        boolean result =
                libraryService.issueBook(
                        bookId,
                        memberId);

        if(result)
            System.out.println(
                    "Book Issued");
        else
            System.out.println(
                    "Issue Failed");
    }

    private static void returnBook()
            throws Exception {

        System.out.print("Issue ID : ");

        int issueId =
                Integer.parseInt(
                        sc.nextLine());

        double fine =
                libraryService.returnBook(
                        issueId,
                        LocalDate.now());

        if(fine > 0) {

            System.out.println(
                    "Fine = Rs. " +
                    fine);
        }
        else {

            System.out.println(
                    "Book Returned");
        }
    }

    private static void listBooks()
            throws Exception {

        List<Book> books =
                libraryService.getAllBooks();

        for(Book b : books) {

            System.out.println(
                    "\n----------------");

            System.out.println(b);
        }
    }

    private static void searchBook()
            throws Exception {

        System.out.print(
                "Keyword : ");

        String keyword =
                sc.nextLine();

        List<Book> books =
                libraryService
                        .searchBook(keyword);

        for(Book b : books) {

            System.out.println(
                    "\n----------------");

            System.out.println(b);
        }
    }
}