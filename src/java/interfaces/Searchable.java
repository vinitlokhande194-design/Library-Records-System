package interfaces;

import java.util.List;
import model.Book;

public interface Searchable {

    List<Book> searchBookByTitle(String title);

}
