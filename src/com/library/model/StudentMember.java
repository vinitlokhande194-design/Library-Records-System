package com.library.model;

public class StudentMember extends Member {

    public StudentMember() {
    }

    public StudentMember(int memberId,
                         String name,
                         String email,
                         String phone) {

        super(memberId, name, email, phone);
    }

    @Override
    public int getBorrowLimit() {
        return 3;
    }

    @Override
    public int getBorrowDays() {
        return 14;
    }

    @Override
    public String toString() {

        return "Student Member" +
                "\nID : " + memberId +
                "\nName : " + name +
                "\nBorrow Limit : " +
                getBorrowLimit();
    }
}