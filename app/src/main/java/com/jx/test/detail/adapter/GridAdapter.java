package com.jx.test.detail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.sift.bean.MyShiPinBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 武晓瑞 on 2017/12/7.
 */

public class GridAdapter extends BaseAdapter {
    List<MyShiPinBean.RetBean.ListBean.ChildListBean> clist;
    Context mc;
    private View view;

    public GridAdapter(List<MyShiPinBean.RetBean.ListBean.ChildListBean> clist, Context mc) {
        this.clist = clist;
        this.mc = mc;
    }

    @Override
    public int getCount() {
        return clist.size();
    }

    @Override
    public Object getItem(int i) {
        return clist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder v1=null;
        if(view==null){
            view = View.inflate(mc, R.layout.item_jian, null);
            v1=new ViewHolder();
            v1.img=(ImageView)view.findViewById(R.id.item_simp);
            v1.tv=(TextView)view.findViewById(R.id.item_text1);
            view.setTag(v1);
        }else{
            v1= (ViewHolder) view.getTag();
        }
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(clist.get(i).getPic(),v1.img);
        v1.tv.setText(clist.get(i).getTitle());
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView tv;
    }
}
