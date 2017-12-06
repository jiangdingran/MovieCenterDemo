package com.jx.test.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jx.test.R;
import com.jx.test.find.adapter.MyRecyclerAdapter;
import com.jx.test.find.bean.HomeBean;
import com.jx.test.find.cardswipelayout.CardItemTouchHelperCallback;
import com.jx.test.find.cardswipelayout.CardLayoutManager;
import com.jx.test.find.cardswipelayout.OnSwipeListener;
import com.jx.test.find.presenter.UserPresenter;
import com.jx.test.find.view.IView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 蒋丁然 on 2017/12/4.
 */

public class Fragment_find extends Fragment implements IView {
    View view;
    @BindView(R.id.find_recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_change)
    Button btnChange;
    Unbinder unbinder;


    UserPresenter userPresenter;
    String HOST = "http://api.svipmovie.com/";

    ArrayList<HomeBean.RetBean.ListBean> mlist;
    MyRecyclerAdapter adapter;

    int page=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, null);
        unbinder = ButterKnife.bind(this, view);

        userPresenter = new UserPresenter(this);

        initData(page);



        return view;
    }

    public void initData(int mpage){
        HashMap<String,String> mmap = new HashMap<>();
        mmap.put("catalogId","402834815584e463015584e539330016");
        mmap.put("pnum",mpage+"");

        userPresenter.getUrl(HOST,mmap);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {

        if(page==20){
            page=1;
        }else{
            page++;
        }

        initData(page);

    }

    @Override
    public void getData(ArrayList<HomeBean.RetBean.ListBean> list) {
        if(mlist == null){
            mlist = new ArrayList<>();
            mlist = list;

            adapter = new MyRecyclerAdapter(mlist,getActivity());

            recyclerview.setItemAnimator(new DefaultItemAnimator());
            recyclerview.setAdapter(adapter);
            CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerview.getAdapter(), list);
            cardCallback.setOnSwipedListener(new OnSwipeListener<HomeBean.RetBean.ListBean>() {
                @Override
                public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                }
                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, HomeBean.RetBean.ListBean listBean, int direction) {
                }
                @Override
                public void onSwipedClear() {
                }
            });
            final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
            final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerview, touchHelper);
            recyclerview.setLayoutManager(cardLayoutManager);
            touchHelper.attachToRecyclerView(recyclerview);
        }else{
            mlist.clear();
            mlist = list;
            adapter = new MyRecyclerAdapter(mlist,getActivity());
            recyclerview.setItemAnimator(new DefaultItemAnimator());
            recyclerview.setAdapter(adapter);
            CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerview.getAdapter(), list);
            cardCallback.setOnSwipedListener(new OnSwipeListener<HomeBean.RetBean.ListBean>() {
                @Override
                public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                }
                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, HomeBean.RetBean.ListBean listBean, int direction) {
                }
                @Override
                public void onSwipedClear() {
                }
            });
            final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
            final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerview, touchHelper);
            recyclerview.setLayoutManager(cardLayoutManager);
            touchHelper.attachToRecyclerView(recyclerview);
        }


        adapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " click"+mlist.get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
