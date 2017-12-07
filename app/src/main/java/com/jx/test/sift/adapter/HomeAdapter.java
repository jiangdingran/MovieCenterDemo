package com.jx.test.sift.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jx.test.R;

import com.jx.test.search.SearchActivity;

import com.jx.test.detail.ShiPActivity;

import com.jx.test.sift.bean.MyDataId;
import com.jx.test.sift.bean.MyHome;
import com.jx.test.sift.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 武晓瑞 on 2017/12/5.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<MyHome.RetBean.ListBean> mlist;
    Context mContext;
    ArrayList alist;
    private enum Type_Item{
        Type_one,Type_two;
    }
    public HomeAdapter(List<MyHome.RetBean.ListBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType== Type_Item.Type_one.ordinal()){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_a, null);
            ViewHondlerA hondler=new ViewHondlerA(view);
            return hondler;
        }else if(viewType== Type_Item.Type_two.ordinal()){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_b, null);
            ViewHondlerB hondler=new ViewHondlerB(view);
            return hondler;
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHondlerA){
            ((ViewHondlerA) holder).searc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, SearchActivity.class));
                }
            });
            alist=new ArrayList();
            for (int i=0;i<mlist.get(0).getChildList().size();i++){
                alist.add(mlist.get(0).getChildList().get(i).getPic());
            }
            ((ViewHondlerA)holder).myBanner.setImageLoader(new GlideImageLoader());
            ((ViewHondlerA)holder).myBanner.setImages(alist);
            ((ViewHondlerA)holder).myBanner.start();
            ((ViewHondlerA)holder).myBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    String dataId = mlist.get(0).getChildList().get(position).getDataId();
                    EventBus.getDefault().postSticky(new MyDataId(dataId));
                    mContext.startActivity(new Intent(mContext, ShiPActivity.class));
                }
            });
        }else if(holder instanceof ViewHondlerB){
            ((ViewHondlerB)holder).recyview.setLayoutManager(new LinearLayoutManager(mContext));
            ItemAdapter itemAdapter = new ItemAdapter(mlist, mContext);
            ((ViewHondlerB)holder).recyview.setAdapter(itemAdapter);
            itemAdapter.setOnItemClickLitener(new ItemAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(mContext, "请点击轮播图，谢谢配合！", Toast.LENGTH_SHORT).show();
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
            return Type_Item.Type_one.ordinal();
        }
        else if(position==1){
            return Type_Item.Type_two.ordinal();
        }
        return -1;
    }
    class ViewHondlerA extends RecyclerView.ViewHolder {
        Banner myBanner;
        EditText searc;
        public ViewHondlerA(View itemView) {
            super(itemView);
            searc=itemView.findViewById(R.id.edit_sou);
            myBanner=(Banner)itemView.findViewById(R.id.mybanner);
        }
    }
    class ViewHondlerB extends RecyclerView.ViewHolder {
        RecyclerView recyview;
        public ViewHondlerB(View itemView) {
            super(itemView);
            recyview=(RecyclerView)itemView.findViewById(R.id.recy_view1);
        }
    }
}
