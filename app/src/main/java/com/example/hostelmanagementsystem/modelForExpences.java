package com.example.hostelmanagementsystem;

public class modelForExpences {
    String reason , amount , date ;

    public modelForExpences() {
    }

    public modelForExpences(String reason, String amount, String date) {
        this.reason = reason;
        this.amount = amount;
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
