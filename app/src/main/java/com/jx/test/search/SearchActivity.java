package com.jx.test.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import com.jx.test.R;
import com.jx.test.find.greendao.gen.DaoMaster;
import com.jx.test.find.greendao.gen.DaoSession;
import com.jx.test.find.greendao.gen.RecodBeanDao;
import com.jx.test.search.adapter.ReclistAdapter;
import com.jx.test.search.adapter.RecodAdapter;
import com.jx.test.search.adapter.SearAdapter;
import com.jx.test.search.bean.RecodBean;
import com.jx.test.search.bean.SearchBean;
import com.jx.test.search.bean.TestBean;
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
    GridView rmsre;
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
    private RecodAdapter recodAdapter;
    private ReclistAdapter reclistAdapter;
    RecodBeanDao userDao;
    private List<RecodBean> recodBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        persent = new SearPersent(this);
        ArrayList<TestBean> list = new ArrayList<>();
        list.add(new TestBean("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2036166846,300012129&fm=27&gp=0.jpg","模拟111"));
        list.add(new TestBean("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=184313215,176252173&fm=27&gp=0.jpg","模拟222"));
        reclistAdapter = new ReclistAdapter(list,SearchActivity.this);
        rmsre.setAdapter(reclistAdapter);
        //初始化管理的包（后面new的OpenHelper包）
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //UserDao接受
        userDao = daoSession.getRecodBeanDao();
        recodBeans = userDao.loadAll();
        rser.setLayoutManager(new GridLayoutManager(SearchActivity.this,6));
        recodAdapter=new RecodAdapter(recodBeans,SearchActivity.this);
        if (recodAdapter!=null){
            rser.setAdapter(recodAdapter);
        }
        recodAdapter.setClickFinsh(new RecodAdapter.ClickFinsh() {
            @Override
            public void itemclick(TextView v, int position) {
                String s = v.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("keyword", s);
                persent.setSeardata("getVideoListByKeyWord.do", map);
            }
        });
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
                String s1 = seartv.getText().toString();
                if (s1.equals("搜索")){
                    HashMap<String, String> map = new HashMap<>();
                    String s = shuru.getText().toString();
                    map.put("keyword", s);
                    persent.setSeardata("getVideoListByKeyWord.do", map);
                    for (int i=0;i<recodBeans.size();i++){
                        String searid = recodBeans.get(i).getSearid();
                        if (searid.equals(s)){
                            flag=1;
                        }else {
                            flag=0;
                        }
                    }
                    if (flag==0){
                        RecodBean recodBean = new RecodBean(null,s);
                        userDao.insert(recodBean);
                    }
                }else{
                      finish();
                }
                break;
            case R.id.clea:
                userDao.deleteAll();
                recodAdapter.notifyDataSetChanged();
                recodBeans.clear();
                break;
        }
    }

    @Override
    public void getSearView(List<SearchBean.RetBean.ListBean> list) {
        adapter = new SearAdapter(list, SearchActivity.this);
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
