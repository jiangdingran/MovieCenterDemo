package com.jx.test.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.search.bean.RecodBean;

import java.util.List;

/**
 * Created by admin on 2017/12/9.
 */

public class RecodAdapter extends RecyclerView.Adapter<RecodAdapter.MyHolder> {
    List<RecodBean> recodBeans;
    Context context;

    public RecodAdapter(List<RecodBean> recodBeans, Context context) {
        this.recodBeans = recodBeans;
        this.context = context;
    }
    public interface ClickFinsh{
        void itemclick(TextView v,int position);
    }
    private ClickFinsh clickFinsh;

    public void setClickFinsh(ClickFinsh clickFinsh) {
        this.clickFinsh = clickFinsh;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_text_item,null);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.textView.setText(recodBeans.get(position).getSearid());
        if (clickFinsh!=null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    clickFinsh.itemclick(holder.textView,layoutPosition);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return recodBeans.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tt);
        }
    }
}


