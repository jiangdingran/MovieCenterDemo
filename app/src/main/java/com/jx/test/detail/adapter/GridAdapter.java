package com.jx.test.detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jx.test.R;
import com.jx.test.sift.bean.MyShiPinBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 武晓瑞 on 2017/12/7.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyItem> {
    List<MyShiPinBean.RetBean.ListBean.ChildListBean> clist;
    Context mc;
    private View view;

    public GridAdapter(List<MyShiPinBean.RetBean.ListBean.ChildListBean> clist, Context mc) {
        this.clist = clist;
        this.mc = mc;
    }

    @Override
    public MyItem onCreateViewHolder(ViewGroup parent, int viewType) {
        MyItem holder = new MyItem(LayoutInflater.from(mc).inflate(R.layout.item_jian, parent, false));
        return holder;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private GridAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(GridAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final GridAdapter.MyItem holder, int position) {
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(clist.get(position).getPic(), holder.img);
//        holder.tv.setText(clist.get(position).getTitle());
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return clist.size();
    }

    class MyItem extends RecyclerView.ViewHolder {
        ImageView img;
//        TextView tv;

        public MyItem(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_simp);
//            tv = (TextView) itemView.findViewById(R.id.item_text1);
        }
    }
}
