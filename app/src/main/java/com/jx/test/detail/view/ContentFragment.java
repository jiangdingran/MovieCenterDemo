package com.jx.test.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx.test.R;
import com.jx.test.detail.adapter.JianAdapter;
import com.jx.test.detail.presenter.Spresenter;
import com.jx.test.sift.bean.MyShiPinBean;

import java.util.HashMap;


/**
 * Created by 武晓瑞 on 2017/12/6.
 */

public class ContentFragment extends Fragment implements Sview{
    private RecyclerView jian_recy;
    private JianAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null, false);
        jian_recy=(RecyclerView)view.findViewById(R.id.jian_recy);
        String id = (String) getArguments().get("id");
        Log.d("sss","bbbbbbbbbbbbb"+id);

        Spresenter spresenter = new Spresenter(this);
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("mediaId",id);
        spresenter.getShiPindaTa("videoDetailApi/videoDetail.do?",map);

        jian_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    @Override
    public void getShiPin(MyShiPinBean.RetBean ret) {

        adapter=new JianAdapter(ret,getActivity());
        jian_recy.setAdapter(adapter);
    }
}
