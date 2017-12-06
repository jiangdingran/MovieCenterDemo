package com.jx.test.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jx.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by 蒋丁然 on 2017/12/4.
 */

public class Fragment_mine extends Fragment {

    View view;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.title_bar_layout)
    ConstraintLayout titleBarLayout;
    @BindView(R.id.my_bg_colorful)
    ImageView myBgColorful;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.historymore)
    LinearLayout historymore;
    @BindView(R.id.historyOne)
    SimpleDraweeView historyOne;
    @BindView(R.id.historyOne_name)
    TextView historyOneName;
    @BindView(R.id.historyTwo)
    SimpleDraweeView historyTwo;
    @BindView(R.id.historyTwo_name)
    TextView historyTwoName;
    @BindView(R.id.historyThree)
    SimpleDraweeView historyThree;
    @BindView(R.id.historyThree_name)
    TextView historyThreeName;
    @BindView(R.id.history)
    LinearLayout history;
    @BindView(R.id.loadmore)
    LinearLayout loadmore;
    @BindView(R.id.save)
    LinearLayout save;
    @BindView(R.id.themmore)
    LinearLayout themmore;
    @BindView(R.id.content_mine)
    LinearLayout contentMine;
    @BindView(R.id.mine_set)
    ImageView mineSet;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.historymore, R.id.loadmore, R.id.save, R.id.themmore, R.id.mine_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.historymore:
                break;
            case R.id.loadmore:
                break;
            case R.id.save:
                break;
            case R.id.themmore:
                break;
            case R.id.mine_set:
                break;
        }
    }
}
