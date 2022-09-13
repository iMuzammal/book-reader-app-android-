package com.example.imuza.bookproject.Model;

public class dataModel {

    private int id;
    private String  Bookimg;
    private String Bname;
    private String Wname;
    private String Price;


    public dataModel() {
    }

    public dataModel(int id, String bookimg, String bname, String wname, String price) {
        this.id = id;
        Bookimg = bookimg;
        Bname = bname;
        Wname = wname;
        Price = price;
    }

    public int getId() {
        return id;
    }

    public String  getBookimg() {
        return Bookimg;
    }

    public String getBname() {
        return Bname;
    }

    public String getWname() {
        return Wname;
    }

    public String getPrice() {
        return Price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookimg(String  bookimg) {
        Bookimg = bookimg;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public void setWname(String wname) {
        Wname = wname;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
