package com.jx.test.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jx.test.R;
import com.jx.test.search.adapter.JlunAdapter;
import com.jx.test.search.adapter.SearAdapter;
import com.jx.test.search.bean.SearchBean;
import com.jx.test.search.present.SearPersent;
import com.jx.test.search.view.SearView;
import com.jx.test.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearView {

    @BindView(R.id.seartv)
    TextView seartv;
    @BindView(R.id.rtit)
    RelativeLayout rtit;
    @BindView(R.id.rser)
    RecyclerView rser;
    @BindView(R.id.rmsre)
    RecyclerView rmsre;
    @BindView(R.id.sgr)
    GridView sgr;
    @BindView(R.id.lear)
    LinearLayout lear;
    @BindView(R.id.shuru)
    EditText shuru;
    @BindView(R.id.clea)
    ImageView clea;
    @BindView(R.id.jl)
    LinearLayout jl;
    private SearPersent persent;
    private SearAdapter adapter;
    private ArrayList<String> strings;
    private JlunAdapter jlunAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        persent = new SearPersent(this);
        strings = new ArrayList<>();
        shuru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LogUtils.d("onTex", i2 + "a");
                if (charSequence.length() > 0) {
                    LogUtils.d("onTextChanged", i2 + "");
                    seartv.setText("搜索");
                } else {
                    seartv.setText("取消");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @OnClick({R.id.seartv, R.id.clea})
    public void onViewClicked(View view) {
        int flag=0;
        switch (view.getId()) {
            case R.id.seartv:
                LogUtils.d("onclick", "aa");
                HashMap<String, String> map = new HashMap<>();
                String s = shuru.getText().toString();
                map.put("keyword", s);
                persent.setSeardata("getVideoListByKeyWord.do", map);
                break;
            case R.id.clea:
                break;
        }
    }

    @Override
    public void getSearView(List<SearchBean.RetBean.ListBean> list) {
        adapter = new SearAdapter(list, SearchActivity.this);
        Toast.makeText(SearchActivity.this,""+list.size(),Toast.LENGTH_LONG).show();
        sgr.setAdapter(adapter);
        if (adapter != null) {
            jl.setVisibility(View.GONE);
            lear.setVisibility(View.VISIBLE);
        } else {
            jl.setVisibility(View.VISIBLE);
            lear.setVisibility(View.GONE);
        }

    }
}
