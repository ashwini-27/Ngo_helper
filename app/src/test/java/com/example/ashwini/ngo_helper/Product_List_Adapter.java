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


public class Product_List_Adapter extends RecyclerView.Adapter<Product_List_Adapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView pimg;
        public TextView price, contribution, name, pid;

        public MyViewHolder(View itemView) {
            super(itemView);
           this.pimg = (ImageView) itemView.findViewById(R.id.pimg);
            this.name = (TextView) itemView.findViewById(R.id.pname);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.contribution = (TextView) itemView.findViewById(R.id.contribution);
         //   this.pid = (TextView) itemView.findViewById(R.id.pid);
        }
    }

    private List<Product> categoriesList;
    private Context mContext;

    @Override
    public Product_List_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_card, parent, false);

        return new Product_List_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Product tempobj = categoriesList.get(position);
  //holder.pimg.setImageResource(tempobj.getImgurl());

        holder.name.setText(tempobj.getName());
        holder.price.setText(tempobj.getPrice());
        holder.contribution.setText(tempobj.getContribution());
        //holder.pid.setText(Integer.toString(tempobj.getPid()));
    }


    public Product_List_Adapter(List<Product> categories, Context context) {
        this.mContext = context;

        this.categoriesList = categories;
        Log.i("listsize", String.valueOf(categoriesList.size()));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}