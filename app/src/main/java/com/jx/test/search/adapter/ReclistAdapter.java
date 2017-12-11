package com.jx.test.search.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jx.test.R;
import com.jx.test.search.bean.TestBean;

import java.util.ArrayList;

/**
 * Created by admin on 2017/12/10.
 */

public class ReclistAdapter extends BaseAdapter {
    ArrayList<TestBean> list;
    Context context;

    public ReclistAdapter(ArrayList<TestBean> list, Context context) {
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
        ViewHolder holder=null;
        if (view==null){
            view=View.inflate(context, R.layout.layout_sear_item,null);
            holder=new ViewHolder();
            holder.simpleDraweeView=view.findViewById(R.id.recsim);
            holder.textView=view.findViewById(R.id.recv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i).getName());
        holder.simpleDraweeView.setImageURI(list.get(i).getImg());
        return view;
    }
    class ViewHolder{
        private SimpleDraweeView simpleDraweeView;
        private TextView textView;
    }
}
