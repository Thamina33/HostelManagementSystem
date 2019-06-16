package com.example.hostelmanagementsystem;

public class modelForStudent {

    String postId , name , roll  , aclass  ,doa  , paymonthList , section , address , ph  ;

    public modelForStudent() {
    }

    public modelForStudent(String postId, String name, String roll, String aclass, String doa, String paymonthList, String section, String address, String ph) {
        this.postId = postId;
        this.name = name;
        this.roll = roll;
        this.aclass = aclass;
        this.doa = doa;
        this.paymonthList = paymonthList;
        this.section = section;
        this.address = address;
        this.ph = ph;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public modelForStudent(String postId, String name, String roll, String aclass, String doa, String paymonthList) {
        this.postId = postId;
        this.name = name;
        this.roll = roll;
        this.aclass = aclass;
        this.doa = doa;
        this.paymonthList = paymonthList;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getPaymonthList() {
        return paymonthList;
    }

    public void setPaymonthList(String paymonthList) {
        this.paymonthList = paymonthList;
    }
}
