package com.my.new2pma;

/**
 * Created by ashwini on 1/30/2018.
 */

class Subjects {

    String subname="Subject";
    int attended=0;
    int totalheld=0;
    int imageResource;

    public Subjects(String subname, int attended, int totalheld, int imageResource) {
        this.subname = subname;
        this.attended = attended;
        this.totalheld = totalheld;
        this.imageResource = imageResource;
    }

    public String getSubname() {
        return subname;
    }

    public int getAttended() {
        return attended;
    }

    public int getTotalheld() {
        return totalheld;
    }

    public int getImageResource() {
        return imageResource;
    }
}
