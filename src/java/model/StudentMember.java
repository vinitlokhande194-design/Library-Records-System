package model;

public class StudentMember extends Member {

    @Override
    public int getBorrowLimit() {
        return 3;
    }
    
}
