package com.pankaj.adminnox.JavaClass;

public class classic_top {

    public String name;
    public String mobileno;
    public String coins;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String date;

    public classic_top(String name, String mobileno, String coins,String date) {
        this.name = name;
        this.mobileno = mobileno;
        this.coins = coins;
        this.date=date;
    }


    public String getName() {
        return name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getCoins() {
        return coins;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }



    public classic_top() {

    }



}
