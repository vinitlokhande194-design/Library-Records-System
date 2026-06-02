package com.library.model;

public class Fine {

    private int fineId;
    private int issueId;
    private double amount;
    private boolean paid;

    public Fine() {
    }

    public Fine(int fineId,
                int issueId,
                double amount,
                boolean paid) {

        this.fineId = fineId;
        this.issueId = issueId;
        this.amount = amount;
        this.paid = paid;
    }

    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {

        return "Fine ID : " + fineId +
                "\nIssue ID : " + issueId +
                "\nAmount : " + amount +
                "\nPaid : " + paid;
    }
}