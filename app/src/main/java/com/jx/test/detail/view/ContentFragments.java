package com.jx.test.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx.test.R;
import com.jx.test.detail.adapter.PlayerAdapter;
import com.jx.test.detail.bean.MyComment;
import com.jx.test.detail.presenter.Ppresenter;

import java.util.HashMap;
import java.util.List;


/**
 * Created by 武晓瑞 on 2017/12/6.
 */

public class ContentFragments extends Fragment implements Pview {
    private PlayerAdapter adapter;
    private RecyclerView comment;
    private Ppresenter ppresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents, null, false);
        comment=(RecyclerView)view.findViewById(R.id.comment);
        String id = (String) getArguments().get("id");
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("mediaId",id);
        ppresenter=new Ppresenter(this);
        ppresenter.setPingdaTa("Commentary/getCommentList.do?",map);
        return view;
    }

    @Override
    public void getPing(List<MyComment.RetBean.ListBean> list) {
        comment.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new PlayerAdapter(list,getActivity());
        comment.setAdapter(adapter);
    }
}
