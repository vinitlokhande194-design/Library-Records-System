package com.library.model;

public abstract class Member {

    protected int memberId;
    protected String name;
    protected String email;
    protected String phone;

    public Member() {
    }

    public Member(int memberId,
                  String name,
                  String email,
                  String phone) {

        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public abstract int getBorrowLimit();

    public abstract int getBorrowDays();

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}