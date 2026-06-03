package model;

public class FacultyMember extends Member {

    @Override
    public int getBorrowLimit() {
        return 10;
    }
    
}
