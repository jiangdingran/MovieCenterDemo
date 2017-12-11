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
import com.jx.test.find.greendao.HistroyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11 0011.
 */

public class HistoryGridAdapter extends BaseAdapter {
    List<HistroyBean> list;
    Context context;

    public HistoryGridAdapter(List<HistroyBean> list, Context context) {
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

        MyHistoryViewHolder holder;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_history_grid,viewGroup,false);

            holder = new MyHistoryViewHolder();

            holder.head = view.findViewById(R.id.history_head);
            holder.name = view.findViewById(R.id.history_name);

            view.setTag(holder);

        }else{
            holder = (MyHistoryViewHolder) view.getTag();
        }


        Uri uri = Uri.parse(list.get(i).getMoviehead());
        holder.head.setImageURI(uri);
        holder.name.setText(list.get(i).getMoviename());

        return view;
    }

    class MyHistoryViewHolder{
        SimpleDraweeView head;
        TextView name;
    }

}
