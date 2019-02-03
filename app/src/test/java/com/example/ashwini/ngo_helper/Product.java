package com.my.new2pma;

class Product {

    String imgurl;
    String name;
    String price;
    String contribution;
    int pid;

    public Product(String imgurl, String name, String price, String contribution, int pid) {
        this.imgurl = imgurl;
        this.name = name;
        this.price = price;
        this.contribution = contribution;
        this.pid = pid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getContribution() {
        return contribution;
    }

    public int getPid() {
        return pid;
    }
}
