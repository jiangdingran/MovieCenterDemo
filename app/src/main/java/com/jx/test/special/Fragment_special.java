package com.jx.test.special;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx.test.R;
import com.jx.test.special.adapter.SpecialAdapter;
import com.jx.test.special.bean.SpecialBean;
import com.jx.test.special.presenter.Mypresent;
import com.jx.test.special.view.Sview;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 蒋丁然 on 2017/12/4.
 */

public class Fragment_special extends Fragment implements Sview {
    View view;
    @BindView(R.id.spec)
    RecyclerView spec;
    Unbinder unbinder;
    private Mypresent mypresent;
    private SpecialAdapter specialAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_special, null);

        mypresent = new Mypresent(this);
        mypresent.setdata("homePage.do");
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void getsview(List<SpecialBean.RetBean.ListBean> list) {
        spec.setLayoutManager(new GridLayoutManager(getActivity(),2));
        specialAdapter=new SpecialAdapter(list,getActivity());
        spec.setAdapter(specialAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
