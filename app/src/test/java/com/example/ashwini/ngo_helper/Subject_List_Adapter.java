package com.my.new2pma;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ashwini on 1/30/2018.
 */

public class Subject_List_Adapter extends RecyclerView.Adapter<Subject_List_Adapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView category;
        public TextView subname,totalAttended,totalHeld,idfeild;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.category=(ImageView) itemView.findViewById(R.id.category);
            this.subname=(TextView) itemView.findViewById(R.id.subjectname);
            this.totalAttended=(TextView) itemView.findViewById(R.id.Attendencecount);
            this.totalHeld=(TextView) itemView.findViewById(R.id.totalheld);
            this.idfeild=(TextView) itemView.findViewById(R.id.projectid);

        }
    }
    private List<Projects> categoriesList;
    private Context mContext;

    @Override
    public Subject_List_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_attendence, parent, false);

        return new Subject_List_Adapter.MyViewHolder(itemView);}

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Projects  tempobj=categoriesList.get(position);
        holder.category.setImageResource(tempobj.getImageResource());

        holder.subname.setText(tempobj.getSubname());
        holder.totalAttended.setText(tempobj.gettotal());
        holder.totalHeld.setText(tempobj.getReceived());
        holder.idfeild.setText(Integer.toString(tempobj.getpid()));
    }



    public Subject_List_Adapter(List<Projects> categories, Context context) {
        this.mContext = context;

        this.categoriesList = categories;
        Log.i("listsize",String.valueOf(categoriesList.size()));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}
