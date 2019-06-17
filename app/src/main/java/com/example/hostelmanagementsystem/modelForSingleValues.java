package com.example.hostelmanagementsystem;

public class modelForSingleValues {

    String expense  ,recieve ;

    public modelForSingleValues() {
    }

    public modelForSingleValues(String expense, String recieve) {
        this.expense = expense;
        this.recieve = recieve;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getRecieve() {
        return recieve;
    }

    public void setRecieve(String recieve) {
        this.recieve = recieve;
    }
}
