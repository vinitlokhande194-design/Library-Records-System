package console;

import dao.BookDao;
import daoimpl.BookDaoImpl;
import model.Book;

import java.util.List;
import java.util.Scanner;

public class BookHandler {

    private static Scanner sc = new Scanner(System.in);
    private static BookDao bookDao = new BookDaoImpl();

    public static void bookMenu() {

        while (true) {

            System.out.println("\n===== BOOK MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book By ID");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Back");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    viewAllBooks();
                    break;

                case 3:
                    searchBookById();
                    break;

                case 4:
                    updateBook();
                    break;

                case 5:
                    deleteBook();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private static void addBook() {

        try {

            Book book = new Book();

            System.out.print("Enter Title: ");
            book.setTitle(sc.nextLine());

            System.out.print("Enter Author: ");
            book.setAuthor(sc.nextLine());

            System.out.print("Enter Category: ");
            book.setCategory(sc.nextLine());

            System.out.print("Enter ISBN: ");
            book.setIsbn(sc.nextLine());

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            book.setQuantity(quantity);
            book.setAvailableQuantity(quantity);

            bookDao.addBook(book);

            System.out.println("Book Added Successfully");

        } catch (Exception e) {
            System.out.println("***********************************************");
        }
    }

    private static void viewAllBooks() {

        List<Book> books = bookDao.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No Books Found");
            return;
        }

        for (Book book : books) {

            System.out.println("------------------------");
            System.out.println("ID : " + book.getBookId());
            System.out.println("Title : " + book.getTitle());
            System.out.println("Author : " + book.getAuthor());
            System.out.println("Category : " + book.getCategory());
            System.out.println("ISBN : " + book.getIsbn());
            System.out.println("Quantity : " + book.getQuantity());
            System.out.println("Available : " + book.getAvailableQuantity());
        }
    }

    private static void searchBookById() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        Book book = bookDao.getBookById(id);

        if (book != null) {

            System.out.println("ID : " + book.getBookId());
            System.out.println("Title : " + book.getTitle());
            System.out.println("Author : " + book.getAuthor());
            System.out.println("Category : " + book.getCategory());

        } else {

            System.out.println("Book Not Found");
        }
    }

    private static void updateBook() {

        try {

            Book book = new Book();

            System.out.print("Enter Book ID: ");
            book.setBookId(sc.nextInt());
            sc.nextLine();

            System.out.print("Enter New Title: ");
            book.setTitle(sc.nextLine());

            System.out.print("Enter New Author: ");
            book.setAuthor(sc.nextLine());

            System.out.print("Enter New Category: ");
            book.setCategory(sc.nextLine());

            System.out.print("Enter New ISBN: ");
            book.setIsbn(sc.nextLine());

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();

            book.setQuantity(quantity);
            book.setAvailableQuantity(quantity);

            bookDao.updateBook(book);

            System.out.println("Book Updated Successfully");

        } catch (Exception e) {
            System.out.println("***********************************************");
        }
    }

    private static void deleteBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        bookDao.deleteBook(id);

        System.out.println("Book Deleted Successfully");
    }
}