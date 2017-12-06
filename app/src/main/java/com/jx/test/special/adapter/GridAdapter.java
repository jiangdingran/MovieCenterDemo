package com.jx.test.special.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.special.bean.SpecialBean;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
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
        ImageLoader.getInstance().displayImage(childList.get(i).getPic(),holder.imageView);
        return view;
    }
    class ViewHolder{
        private TextView textView;
        private ImageView imageView;
    }
}
