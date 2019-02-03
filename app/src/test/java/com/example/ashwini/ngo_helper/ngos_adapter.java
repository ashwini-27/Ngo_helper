package com.my.new2pma;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ngos_adapter extends RecyclerView.Adapter<ngos_adapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView i1;
        public TextView t1;
        public TextView t2;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.i1=(ImageView) itemView.findViewById(R.id.imageView2);
            this.t1=(TextView) itemView.findViewById(R.id.name);
            this.t2=(TextView) itemView.findViewById(R.id.bio);
        }
    }

    public ngos_adapter(List<ngo_explore> categories, Context context) {
        this.mContext = context;

        this.categoriesList = categories;
        Log.i("listsize",String.valueOf(categoriesList.size()));
    }



    private List<ngo_explore> categoriesList;
    private Context mContext;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exp_design, parent, false);//design

        return new MyViewHolder(itemView);}

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ngo_explore tempobj=categoriesList.get(position);
        //holder.i1.setImageURI(tempobj.getI1());
        holder.t1.setText(tempobj.getT1());
        holder.t2.setText(tempobj.getT2());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
