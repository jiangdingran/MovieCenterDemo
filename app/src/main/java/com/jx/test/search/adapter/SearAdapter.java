package com.jx.test.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.search.bean.SearchBean;
import com.jx.test.sift.bean.MyDataId;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by admin on 2017/12/8.
 */

public class SearAdapter extends BaseAdapter {
    List<SearchBean.RetBean.ListBean> list;
    Context context;

    public SearAdapter(List<SearchBean.RetBean.ListBean> list, Context context) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            view=View.inflate(context, R.layout.layout_grid_item,null);
            holder=new ViewHolder();
            holder.simpleDraweeView=view.findViewById(R.id.simg);
            holder.textView=view.findViewById(R.id.stv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.simpleDraweeView.setImageURI(list.get(i).getPic());
        holder.textView.setText(list.get(i).getTitle());
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataId = list.get(i).getDataId();
                EventBus.getDefault().postSticky(new MyDataId(dataId));
                context.startActivity(new Intent(context, ShiPActivity.class));
            }
        });
        return view;
    }
    class ViewHolder{
        private TextView textView;
        private SimpleDraweeView simpleDraweeView;
    }
}
