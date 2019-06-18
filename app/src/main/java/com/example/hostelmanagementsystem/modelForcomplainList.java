package com.example.hostelmanagementsystem;

public class modelForcomplainList {

    String  postID  , name  , roll , aClass , section , date , reason , details  ;

    public modelForcomplainList() {
    }

    public modelForcomplainList(String postID, String name, String roll, String aClass, String section, String date, String reason, String details) {
        this.postID = postID;
        this.name = name;
        this.roll = roll;
        this.aClass = aClass;
        this.section = section;
        this.date = date;
        this.reason = reason;
        this.details = details;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
