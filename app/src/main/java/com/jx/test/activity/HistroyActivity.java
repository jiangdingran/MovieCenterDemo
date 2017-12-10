package com.jx.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jx.test.R;
import com.jx.test.find.greendao.HistroyBean;
import com.jx.test.find.greendao.gen.DaoMaster;
import com.jx.test.find.greendao.gen.DaoSession;
import com.jx.test.find.greendao.gen.HistroyBeanDao;

import java.util.List;

public class HistroyActivity extends AppCompatActivity {


    HistroyBeanDao userDao;
    List<HistroyBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy);


        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //UserDao接受
        userDao = daoSession.getHistroyBeanDao();

        initData();

    }

    public void initData(){
        list = userDao.queryBuilder()
                .build().list();

        for (int i = 0; i < list.size(); i++) {
            Log.d("google_lenve", "id=========" + list.get(i).getMovieid() + "name===== " + list.get(i).getMoviename() + "age====" + list.get(i).getMoviehead());
        }

    }
}
