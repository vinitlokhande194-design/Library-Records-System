package com.library.model;

public class FacultyMember extends Member {

    public FacultyMember() {
    }

    public FacultyMember(int memberId,
                         String name,
                         String email,
                         String phone) {

        super(memberId, name, email, phone);
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }

    @Override
    public int getBorrowDays() {
        return 30;
    }

    @Override
    public String toString() {

        return "Faculty Member" +
                "\nID : " + memberId +
                "\nName : " + name +
                "\nBorrow Limit : " +
                getBorrowLimit();
    }
}