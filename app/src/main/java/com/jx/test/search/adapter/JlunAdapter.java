package com.jx.test.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx.test.R;

import java.util.ArrayList;

/**
 * Created by admin on 2017/12/9.
 */

public class JlunAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<String> list;
    Context context;

    public JlunAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_text_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
     TextView textView;
    public MyViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.tt);
    }
}
