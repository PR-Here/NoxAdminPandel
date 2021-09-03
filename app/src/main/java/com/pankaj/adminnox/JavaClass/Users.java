package com.pankaj.adminnox.JavaClass;

public class Users {

    public Users(String name, String coins, String mobileno) {
        this.name = name;
        this.coins = coins;
        this.mobileno = mobileno;
    }

    public String name;
    public String coins;

    public String getName() {
        return name;
    }

    public String getCoins() {
        return coins;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String mobileno;

}
