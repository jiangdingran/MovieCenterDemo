package com.jx.test.find.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jx.test.R;
import com.jx.test.find.greendao.CollectBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class CollectGridAdapter extends BaseAdapter {
    List<CollectBean> list;
    Context context;

    public CollectGridAdapter(List<CollectBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MyCollectViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_collect_grid,viewGroup,false);

            holder = new MyCollectViewHolder();

            holder.head = view.findViewById(R.id.collect_head);
            holder.name = view.findViewById(R.id.collect_name);

            view.setTag(holder);

        }else{
            holder = (MyCollectViewHolder) view.getTag();
        }


        Uri uri = Uri.parse(list.get(i).getNickhead());
        holder.head.setImageURI(uri);
        holder.name.setText(list.get(i).getNickname());

        return view;
    }

    class MyCollectViewHolder{
        SimpleDraweeView head;
        TextView name;
    }

}
