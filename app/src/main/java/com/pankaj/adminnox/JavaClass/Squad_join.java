package com.pankaj.adminnox.JavaClass;

public class Squad_join {

    public String mobileno,paymentType,pubgname;

    public Squad_join(){

    }

    public Squad_join(String mobileno, String paymentType, String pubgname) {
        this.mobileno = mobileno;
        this.paymentType = paymentType;
        this.pubgname = pubgname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPubgname() {
        return pubgname;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setPubgname(String pubgname) {
        this.pubgname = pubgname;
    }


}
