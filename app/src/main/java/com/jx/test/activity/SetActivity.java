package com.jx.test.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jx.test.R;
import com.jx.test.utils.CacheUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.tj)
    LinearLayout tj;
    @BindView(R.id.clean)
    LinearLayout clean;
    @BindView(R.id.gy)
    LinearLayout gy;
    @BindView(R.id.jy)
    LinearLayout jy;
    @BindView(R.id.goback)
    ImageView goback;
    @BindView(R.id.gobackLayout)
    LinearLayout gobackLayout;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.settv)
    TextView settv;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.text_hc)
    TextView textHc;

    @Override
    protected int getRootView() {
        return R.layout.activity_set;
    }

    @Override
    protected void init() {
        titleBarName.setText("设置");
        settv.setVisibility(View.INVISIBLE);
        /*获取缓存*/
        try {
            textHc.setText(CacheUtil.getTotalCacheSize(SetActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tj, R.id.clean, R.id.gy, R.id.jy, R.id.goback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tj:
               final AlertDialog.Builder builder = new AlertDialog.Builder(
                        SetActivity.this);
                builder.setIcon(R.mipmap.ic_action_emo_wink);//提示图标
                builder.setTitle("发现一个看片神器");//提示标题
                builder.setMessage("https://github.com/jiangdingran/MovieCenterDemo");//提示内容
                builder.setPositiveButton("复制", new DialogInterface.OnClickListener() {//复制(确定按钮)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //复制到剪切板
                        ClipboardManager cm = (ClipboardManager) SetActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setPrimaryClip(ClipData.newPlainText("text","https://github.com/jiangdingran/MovieCenterDemo"));
                        Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {//取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.clean:
                CacheUtil.clearAllCache(SetActivity.this);
                textHc.setText("0KB");
                Toast.makeText(SetActivity.this, "缓存已清理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gy:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(SetActivity.this);//当前环境
                builder2.setIcon(R.mipmap.ic_action_users);//提示图标
                builder2.setTitle("关于我们");//提示框标题
                builder2.setMessage("关于我们?" + "\n" + "我们是一群...呃...无所谓了");//提示内容
                builder2.setNegativeButton("关闭", null);//关闭按钮
                builder2.create().show();
                break;
            case R.id.jy:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(SetActivity.this);//当前环境
                builder3.setIcon(R.mipmap.ic_action_undo);//提示图标
                builder3.setTitle("建议反馈");//提示框标题
                builder3.setMessage("做项目很辛苦的" + "\n" + "还想挑错?"+ "\n" + "别逗了....");//提示内容
                builder3.setNegativeButton("关闭", null);//关闭按钮
                builder3.create().show();
                break;
            case R.id.goback:
                finish();
                break;
        }
    }

}
