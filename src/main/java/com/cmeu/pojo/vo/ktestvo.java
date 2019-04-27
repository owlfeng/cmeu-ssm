package com.cmeu.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ktestvo {
        private int id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private Date lasttime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private Date intime;
        private int count;
        private String price;

    public ktestvo() {
    }

    public ktestvo(int id, Date lasttime, Date intime, int count, String price) {
        this.id = id;
        this.lasttime = lasttime;
        this.intime = intime;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
