package console;

import dao.FineDao;
import daoimpl.FineDaoImpl;
import java.util.Scanner;

public class LibraryConsoleApp {

    public static void main(String[] args) {

        FineDao fineDao = new FineDaoImpl();

        fineDao.generateOverdueFines();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=================================");
            System.out.println("      LIBRARY RECORDS SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Manage Book Issues");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.println("=================================");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    BookHandler.bookMenu();
                    break;

                case 2:
                    MemberHandler.memberMenu();
                    break;

                case 3:
                    BookIssueHandler.issueMenu();
                    break;

                case 4:
                    ReportHandler.reportMenu();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
