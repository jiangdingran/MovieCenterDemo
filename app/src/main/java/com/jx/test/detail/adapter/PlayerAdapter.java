package com.jx.test.detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.detail.bean.MyComment;

import java.util.List;

/**
 * Created by 武晓瑞 on 2017/12/7.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    private List<MyComment.RetBean.ListBean> list;
    Context mContext;

    public PlayerAdapter(List<MyComment.RetBean.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.comment_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(list.get(position).getPhoneNumber());
        holder.tv2.setText(list.get(position).getTime());
        holder.tv3.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv1,tv2,tv3;

        public MyViewHolder(View view)
        {
            super(view);
            tv1 = (TextView) view.findViewById(R.id.comment_tv1);
            tv2 = (TextView) view.findViewById(R.id.comment_tv2);
            tv3 = (TextView) view.findViewById(R.id.comment_tv3);
        }
    }
}
