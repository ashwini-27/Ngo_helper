package com.my.new2pma;

import java.util.Date;

class Projects {
    String title;
    String total;
    String received;
    String postedon;
    String lastdate;
    int ngo;
    int id;

    // TODO: 2/2/19
//        we have to get the image from the server according to organizations symbol

//    public String getImagesrc(){
//        R.drawable.logo.png;
//    }

        Projects(String title, String total, String received, String posted, String lastdate, int id,int pid){
            this.title=title;
            this.total=total;
            this.received=received;
            this.postedon=posted;
            this.lastdate=lastdate;
            this.ngo=id;
            this.id=pid;
        }

    public int getImageResource() {

        return android.R.drawable.ic_menu_camera;
    }

    public String getSubname() {
        return this.title;
    }

    public String getReceived() {
        return this.received;
    }
    public String gettotal(){
        return this.total;
    }
    public int getpid(){
            return this.id;
    }
    public String getstart(){
            return this.lastdate;
    }

    public String getLastdate() {
        return lastdate;
    }
}
