package com.jx.test.sift.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.sift.bean.MyDataId;
import com.jx.test.sift.bean.MyHome;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 武晓瑞 on 2017/12/5.
 */

public class HomeAdapter extends XRecyclerView.Adapter<HomeAdapter.MyViewHolder>  {

    List<MyHome.RetBean.ListBean> mlist;
    Context mContext;

    public HomeAdapter(List<MyHome.RetBean.ListBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_home, parent,
                false));
        return holder;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private HomeAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(HomeAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        holder.img.setImageURI(mlist.get(4).getChildList().get(position).getPic(),holder.img);
        holder.tv.setText(mlist.get(4).getChildList().get(position).getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dataId = mlist.get(4).getChildList().get(position).getDataId();
                    EventBus.getDefault().postSticky(new MyDataId(dataId));
                    mContext.startActivity(new Intent(mContext, ShiPActivity.class));
                }
            });
    }

    @Override
    public int getItemCount()
    {
        return mlist.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {

        SimpleDraweeView img;
        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            img=(SimpleDraweeView)itemView.findViewById(R.id.item_simp);
            tv=(TextView)itemView.findViewById(R.id.item_text1);
        }
    }
}
