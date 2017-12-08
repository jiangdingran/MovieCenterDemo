package com.jx.test.detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.sift.bean.MyDataId;
import com.jx.test.sift.bean.MyShiPinBean;

import org.greenrobot.eventbus.EventBus;

import cn.carbs.android.expandabletextview.library.ExpandableTextView;

/**
 * Created by 武晓瑞 on 2017/12/7.
 */

public class JianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MyShiPinBean.RetBean ret;
    Context mContext;
    private enum Type_Item{
        Type_one,Type_two;
    }

    public JianAdapter(MyShiPinBean.RetBean ret, Context mContext) {
        this.ret = ret;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType== JianAdapter.Type_Item.Type_one.ordinal()){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_c, null);
            JianAdapter.ViewHondlerA hondler=new JianAdapter.ViewHondlerA(view);
            return hondler;
        }else if(viewType== JianAdapter.Type_Item.Type_two.ordinal()){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_d, null);
            JianAdapter.ViewHondlerB hondler=new JianAdapter.ViewHondlerB(view);
            return hondler;
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof JianAdapter.ViewHondlerA){
            ((ViewHondlerA)holder).tv1.setText(ret.getDirector());
            ((ViewHondlerA)holder).tv2.setText(ret.getActors());
            ((ViewHondlerA)holder).mETV.setText(ret.getDescription());
        }else if(holder instanceof JianAdapter.ViewHondlerB){
            /*LinearLayoutManager manager = new LinearLayoutManager(mContext);
            ((ViewHondlerB)holder).recyclerView.setLayoutManager(manager);*/
            ((ViewHondlerB)holder).recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
            GridAdapter gridAdapter = new GridAdapter(ret.getList().get(0).getChildList(),mContext);
            ((ViewHondlerB)holder).recyclerView.setAdapter(gridAdapter);
            gridAdapter.setOnItemClickLitener(new GridAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    String dataId = ret.getList().get(0).getChildList().get(position).getDataId();
                    EventBus.getDefault().postSticky(new MyDataId(dataId));
                    mContext.startActivity(new Intent(mContext, ShiPActivity.class));
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return JianAdapter.Type_Item.Type_one.ordinal();
        }
        else if(position==1){
            return JianAdapter.Type_Item.Type_two.ordinal();
        }
        return -1;
    }
    class ViewHondlerA extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        ExpandableTextView mETV;
        public ViewHondlerA(View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.dy);
            tv2=(TextView)itemView.findViewById(R.id.zy);
            mETV = (ExpandableTextView)itemView.findViewById(R.id.etv);
        }
    }
    class ViewHondlerB extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public ViewHondlerB(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.jian_xrecy);
        }
    }
}
