package com.example.androidproject3;

import java.io.Serializable;
import java.util.Date;

public class Daily implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDay() { return day; }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month){this.month=month;}
    public String getMonth(){return month;}

    public void setYear(String year){this.year=year;}
    public String getYear(){return year;}

    private String name;
    private int pictureId;
    private double price;
    private String month;
    private String day;
    private String year;
}
