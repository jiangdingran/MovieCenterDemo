package com.jx.test.special.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.sift.bean.MyDataId;
import com.jx.test.special.bean.SpecialBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by admin on 2017/12/6.
 */

public class GridAdapter extends BaseAdapter {
    List<SpecialBean.RetBean.ListBean.ChildListBean> childList;
    Context context;

    public GridAdapter(List<SpecialBean.RetBean.ListBean.ChildListBean> childList, Context context) {
        this.childList = childList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int i) {
        return childList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            view=View.inflate(context, R.layout.layout_list_item,null);
            holder=new ViewHolder();
            holder.imageView=view.findViewById(R.id.img);
            holder.textView=view.findViewById(R.id.gtv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.textView.setText(childList.get(i).getTitle());
        holder.imageView.setImageURI(childList.get(i).getPic(),holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataId = childList.get(i).getDataId();
                EventBus.getDefault().postSticky(new MyDataId(dataId));
                context.startActivity(new Intent(context, ShiPActivity.class));
            }
        });
        return view;
    }
    class ViewHolder{
        private TextView textView;
        private SimpleDraweeView imageView;
    }
}
