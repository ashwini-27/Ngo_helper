package com.my.new2pma;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.my.new2pma.R;
import com.my.new2pma.cart_adapter;

import java.util.List;

public class cart_adapter_adapter1 extends RecyclerView.Adapter<cart_adapter_adapter1.MyViewHolder> {

    public TextView t1, t2, t3;
    private List<cart_adapter> cart_adapterlist;
public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView t1,t2,t3;
    public MyViewHolder(View view) {
        super(view);
        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        t3 = (TextView) view.findViewById(R.id.t3);
    }

}
    public cart_adapter_adapter1(List<cart_adapter> cartlist, Context context) {
        this.cart_adapterlist = cartlist;
    }
    @Override
    public cart_adapter_adapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list_row, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        cart_adapter movie = cart_adapterlist.get(position);
        holder.t1.setText(movie.getS1());
        holder.t2.setText(movie.getS2());
        holder.t3.setText(movie.getS3());
    }

    @Override
    public int getItemCount() {
        return cart_adapterlist.size();
    }

}