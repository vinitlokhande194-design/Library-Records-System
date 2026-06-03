package dao;

import java.util.List;
import model.BookIssue;

public interface BookIssueDao {

    void issueBook(BookIssue bookIssue);

    void returnBook(int issueId);

    BookIssue getIssueById(int issueId);

    List<BookIssue> getAllIssuedBooks();
}
