package com.jx.test.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mummyding.colorpickerdialog.ColorPickerDialog;
import com.github.mummyding.colorpickerdialog.OnColorChangedListener;
import com.jx.test.R;
import com.jx.test.find.Fragment_find;
import com.jx.test.mine.Fragment_mine;
import com.jx.test.sift.Fragment_sift;
import com.jx.test.special.Fragment_special;
import com.jx.test.utils.ResideLayout;
import com.jx.test.utils.XCRoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rdb_sift)
    RadioButton rdbSift;
    @BindView(R.id.rdb_special)
    RadioButton rdbSpecial;
    @BindView(R.id.rdb_find)
    RadioButton rdbFind;
    @BindView(R.id.rdb_mine)
    RadioButton rdbMine;
    @BindView(R.id.sc)
    LinearLayout sc;
    @BindView(R.id.load)
    LinearLayout load;
    @BindView(R.id.fl)
    LinearLayout fl;
    @BindView(R.id.fx)
    LinearLayout fx;
    @BindView(R.id.jyfk)
    LinearLayout jyfk;
    @BindView(R.id.sz)
    LinearLayout sz;
    @BindView(R.id.gy)
    LinearLayout gy;
    @BindView(R.id.zt)
    LinearLayout zt;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.my_radiogroup)
    RadioGroup myRadiogroup;
    @BindView(R.id.btn_layout)
    LinearLayout btnLayout;
    @BindView(R.id.roundImageView)
    XCRoundImageView roundImageView;
    @BindView(R.id.tex_name)
    TextView texName;
    @BindView(R.id.resideLayout)
    ResideLayout resideLayout;

    UMAuthListener umAuthListener;
    @BindView(R.id.chbg)
    RelativeLayout chbg;
    private Fragment_find fragment_find;
    private Fragment_mine fragment_mine;
    private Fragment_sift fragment_sift;
    private Fragment_special fragment_special;

    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    protected int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initView();
        rdbSift.setChecked(true);
        //侧滑的监听
        resideLayout.setPanelSlideListener(new ResideLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelOpened(View panel) {
                /*Toast.makeText(MainActivity.this, "打开", Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onPanelClosed(View panel) {
                /*Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();*/
            }
        });
        //友盟第三方监听
        umAuthListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                System.out.println("uid========" + map.get("uid"));
                System.out.println("name========" + map.get("name"));
                System.out.println("iconurl========" + map.get("iconurl"));
                //设置QQ头像
                ImageLoader.getInstance().displayImage(map.get("iconurl"), roundImageView);
                //设置QQ名字
                texName.setText(map.get("name"));
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        };
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void initView() {
        fragment_find = new Fragment_find();
        fragment_mine = new Fragment_mine();
        fragment_sift = new Fragment_sift();
        fragment_special = new Fragment_special();

        AddFragment();

        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ftransaction = fm1.beginTransaction();

        ftransaction.show(fragment_sift);
        ftransaction.hide(fragment_find);
        ftransaction.hide(fragment_mine);
        ftransaction.hide(fragment_special);
        ftransaction.commit();

    }

    public void AddFragment() {
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ftransaction = fm1.beginTransaction();
        ftransaction.add(R.id.frame_layout, fragment_sift);
        ftransaction.add(R.id.frame_layout, fragment_mine);
        ftransaction.add(R.id.frame_layout, fragment_find);
        ftransaction.add(R.id.frame_layout, fragment_special);
        ftransaction.commit();
    }


    @OnClick({R.id.rdb_sift, R.id.rdb_special, R.id.rdb_find, R.id.rdb_mine, R.id.sc, R.id.load, R.id.fl, R.id.jyfk, R.id.sz, R.id.gy, R.id.zt, R.id.roundImageView})
    public void onViewClicked(View view) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.rdb_sift:
                transaction.show(fragment_sift);
                transaction.hide(fragment_mine);
                transaction.hide(fragment_find);
                transaction.hide(fragment_special);
                break;
            case R.id.rdb_special:
                //transaction.replace(R.id.frame_layout, fragment_special);
                transaction.show(fragment_special);
                transaction.hide(fragment_mine);
                transaction.hide(fragment_sift);
                transaction.hide(fragment_find);
                break;
            case R.id.rdb_find:
                //transaction.replace(R.id.frame_layout, fragment_find);
                transaction.show(fragment_find);
                transaction.hide(fragment_mine);
                transaction.hide(fragment_sift);
                transaction.hide(fragment_special);
                break;
            case R.id.rdb_mine:
                //transaction.replace(R.id.frame_layout, fragment_mine);
                transaction.show(fragment_mine);
                transaction.hide(fragment_find);
                transaction.hide(fragment_sift);
                transaction.hide(fragment_special);
                break;
            case R.id.sc:
                startActivity(new Intent(MainActivity.this, CollectActivity.class));
                break;
            case R.id.load:
                Toast.makeText(mContext, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fl:
                startActivity(new Intent(MainActivity.this, WelfareActivity.class));
                break;
            case R.id.fx:
                break;
            case R.id.jyfk:
                break;
            case R.id.sz:
                startActivity(new Intent(MainActivity.this, SetActivity.class));
                break;
            case R.id.gy:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//当前环境
                builder.setIcon(R.mipmap.ic_action_users);//提示图标
                builder.setTitle("关于我们");//提示框标题
                builder.setMessage("点开并没有什么" + "\n" + "但请记住,我们是:景行工作室");//提示内容
                builder.setNegativeButton("关闭", null);//关闭按钮
                builder.create().show();
                break;
            case R.id.zt:
                int[] colors = new int[]{Color.YELLOW, Color.BLACK, Color.BLUE, Color.GRAY,
                        Color.GREEN, Color.CYAN, Color.RED, Color.DKGRAY, Color.LTGRAY, Color.MAGENTA,
                        Color.rgb(100, 22, 33), Color.rgb(82, 182, 2), Color.rgb(122, 32, 12), Color.rgb(82, 12, 2),
                        Color.rgb(89, 23, 200), Color.rgb(13, 222, 23)};
                ColorPickerDialog dialog =
                        // Constructor,the first argv is Context,second one is the colors you want to add
                        new ColorPickerDialog(MainActivity.this, colors)
                                .setOnColorChangedListener(new OnColorChangedListener() {
                                    @Override
                                    public void onColorChanged(int newColor) {
                                        // do something here
                                        Toast.makeText(MainActivity.this, "Color " + newColor, Toast.LENGTH_SHORT).show();
                                        chbg.setBackgroundColor(newColor);
                                    }
                                })
                                .build()
                                .show();
                break;
            case R.id.roundImageView:
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
        transaction.commit();
        Log.d("sss", "onViewClicked: ddd");
    }

    //第三方登录重写onactivityresult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
