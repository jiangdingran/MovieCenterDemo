package com.jx.test.welfare.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jx.test.R;
import com.jx.test.welfare.bean.WelfareBean;

import java.util.ArrayList;

/**
 * Created by w h l on 2017/12/6.
 */

public class WelAdapter extends RecyclerView.Adapter<WelAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<WelfareBean.NewslistBean> list;

    public WelAdapter(Context context, ArrayList<WelfareBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_welfare, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        //用Fresco形式得到的图片
        Uri uri = Uri.parse(list.get(position).getPicUrl());
        holder.img.setImageURI(uri);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        //这是用来实现Fresco展示图片的控件
        SimpleDraweeView img;

        public MyViewHolder(View view)
        {
            super(view);
            img = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        }
    }
}
