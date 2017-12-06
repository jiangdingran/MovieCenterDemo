package com.jx.test.special.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.special.ListActivity;
import com.jx.test.special.bean.PostionBean;
import com.jx.test.special.bean.SpecialBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/6.
 */

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyViewHolder>{
    List<SpecialBean.RetBean.ListBean> list;
    Context context;
    private String s;

    public SpecialAdapter(List<SpecialBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_special_item, null);
        MyViewHolder holder=new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ArrayList<String> imgs = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String title = list.get(i).getTitle();
            titles.add(title);
    }
        //holder.simpleDraweeView.setImageURI(list.get(4).getChildList().get(position).getPic());
        holder.textView.setText(list.get(position).getTitle());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.default_200)
                .showImageOnFail(R.mipmap.bg_colorful)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(list.get(4).getChildList().get(position).getPic(),holder.simpleDraweeView,options);
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ListActivity.class));
                EventBus.getDefault().postSticky(new PostionBean(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView simpleDraweeView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv);
            simpleDraweeView=itemView.findViewById(R.id.sim);
        }
    }
}
