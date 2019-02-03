package com.my.new2pma;

import android.widget.ImageView;
import android.widget.TextView;

public class ngo_explore {
    String i1;
    String t1;
    String t2;

    public String getI1() {
        return i1;
    }

    public String getT1() {
        return t1;
    }

    public String getT2() {
        return t2;
    }

    public ngo_explore(String image, String s1, String s2)
    {
        this.i1=image;
        this.t1=s1;
        this.t2=s2;
    }
}

