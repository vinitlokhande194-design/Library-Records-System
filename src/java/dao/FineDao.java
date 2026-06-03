package dao;

import java.util.List;
import model.Fine;

public interface FineDao {

    void generateOverdueFines();

    List<Fine> getAllFines();
}