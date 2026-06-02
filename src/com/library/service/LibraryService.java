package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.BookIssueDAO;
import com.library.dao.FineDAO;
import com.library.dao.MemberDAO;
import com.library.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LibraryService {

    private BookDAO bookDAO = new BookDAO();
    private MemberDAO memberDAO = new MemberDAO();
    private BookIssueDAO issueDAO = new BookIssueDAO();
    private FineDAO fineDAO = new FineDAO();

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public void registerMember(Member member) throws SQLException {
        memberDAO.registerMember(member);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    public List<Book> searchBook(String keyword)
            throws SQLException {

        return bookDAO.searchBook(keyword);
    }

    public boolean issueBook(
            int bookId,
            int memberId)
            throws SQLException {

        Book book =
                bookDAO.getBookById(bookId);

        Member member =
                memberDAO.getMemberById(memberId);

        if(book == null || member == null) {
            return false;
        }

        if(!book.isAvailable()) {
            return false;
        }

        LocalDate issueDate =
                LocalDate.now();

        LocalDate dueDate =
                issueDate.plusDays(
                        member.getBorrowDays());

        BookIssue issue =
                new BookIssue();

        issue.setBookId(bookId);
        issue.setMemberId(memberId);
        issue.setIssueDate(issueDate);
        issue.setDueDate(dueDate);

        issueDAO.issueBook(issue);

        bookDAO.updateAvailability(
                bookId,
                false);

        return true;
    }

    public double returnBook(
            int issueId,
            LocalDate returnDate)
            throws SQLException {

        List<BookIssue> issues =
                issueDAO.getIssuedBooks();

        BookIssue selected = null;

        for(BookIssue issue : issues) {

            if(issue.getIssueId() == issueId) {

                selected = issue;
                break;
            }
        }

        if(selected == null) {
            return -1;
        }

        issueDAO.returnBook(
                issueId,
                returnDate);

        bookDAO.updateAvailability(
                selected.getBookId(),
                true);

        long lateDays =
                ChronoUnit.DAYS.between(
                        selected.getDueDate(),
                        returnDate);

        if(lateDays > 0) {

            double fineAmount =
                    lateDays * 2.0;

            Fine fine =
                    new Fine();

            fine.setIssueId(issueId);
            fine.setAmount(fineAmount);
            fine.setPaid(false);

            fineDAO.addFine(fine);

            return fineAmount;
        }

        return 0;
    }
}