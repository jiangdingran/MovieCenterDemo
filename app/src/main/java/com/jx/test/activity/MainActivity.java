package com.jx.test.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mummyding.colorpickerdialog.ColorPickerDialog;
import com.github.mummyding.colorpickerdialog.OnColorChangedListener;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.jx.test.R;
import com.jx.test.find.Fragment_find;
import com.jx.test.find.greendao.HistroyBean;
import com.jx.test.find.greendao.gen.DaoMaster;
import com.jx.test.find.greendao.gen.DaoSession;
import com.jx.test.find.greendao.gen.HistroyBeanDao;
import com.jx.test.mine.Fragment_mine;
import com.jx.test.sift.view.Fragment_sift;
import com.jx.test.special.Fragment_special;

import com.jx.test.utils.NetUtils;

import com.jx.test.utils.JsonParser;

import com.jx.test.utils.ResideLayout;
import com.jx.test.utils.XCRoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhy.changeskin.SkinManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
    List<HistroyBean> list;
    HistroyBeanDao userDao;
    private Button button,Byy;
    private TextView tv_title;
    private EditText edit_yx,edit_content;
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();    // 用HashMap存储听写结果
    private static final String TAG = MainActivity.class .getSimpleName();

    @Override
    protected int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        int netWorkType = NetUtils.getNetWorkType(MainActivity.this);
        if (netWorkType==-1){
            Toast.makeText(MainActivity.this, "网络没有连接哦~", Toast.LENGTH_SHORT).show();
        }
        chbg.setBackgroundResource(R.mipmap.bg_colorful);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //UserDao接受
        userDao = daoSession.getHistroyBeanDao();

        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
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
        //调用语音
        initSpeech() ;
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


    @OnClick({R.id.rdb_sift, R.id.rdb_special, R.id.rdb_find, R.id.rdb_mine, R.id.sc, R.id.load, R.id.fl, R.id.jyfk, R.id.sz, R.id.gy, R.id.zt, R.id.roundImageView,R.id.fx})
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
                list = userDao.queryBuilder()
                        .build().list();

                fragment_mine.getData(list);
                break;
            case R.id.sc:
                startActivity(new Intent(MainActivity.this, SaveActivity.class));
                break;
            case R.id.load:
                Toast.makeText(mContext, "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fl:
                startActivity(new Intent(MainActivity.this, WelfareActivity.class));
                break;
            case R.id.fx:
                new ShareAction(MainActivity.this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN)
                        .setContentList(new ShareContent(),new ShareContent())
                        .withText("title")
                        .setListenerList(shareListener,shareListener)
                        .setShareboardclickCallback(shareBoardlistener)
                        .open();
                break;
            case R.id.jyfk:
                //实例化布局
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.alterdialog,null);
                //初始化控件
                Byy = (Button) view.findViewById(R.id.btn_yy);
                edit_yx = (EditText) view.findViewById(R.id.edit_yx);
                edit_content = (EditText) view.findViewById(R.id.edit_content);
                //点击调用语音识别
                Byy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startSpeechDialog();
                    }
                });
                //创建对话框
                AlertDialog dialog1 = new AlertDialog.Builder(MainActivity.this).create();
                dialog1.setView(view);//添加布局
                //设置按键
                dialog1.setButton(AlertDialog.BUTTON_POSITIVE, "发送", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "已成功发送反馈", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog1.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您已取消", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog1.show();
                break;
            case R.id.sz:
                startActivity(new Intent(MainActivity.this, SetActivity.class));
                break;
            case R.id.gy:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//当前环境
                builder.setIcon(R.mipmap.ic_action_users2);//提示图标
                builder.setTitle("关于我们");//提示框标题
                builder.setMessage("点开并没有什么" + "\n" + "但请记住,我们是:景行工作室");//提示内容
                builder.setNegativeButton("关闭", null);//关闭按钮
                builder.create().show();
                break;
            case R.id.zt:
                int[] colors = new int[]{Color.YELLOW, Color.BLUE,
                        Color.GREEN, Color.CYAN, Color.RED, Color.DKGRAY, Color.LTGRAY, Color.MAGENTA};
                ColorPickerDialog dialog =
                        // Constructor,the first argv is Context,second one is the colors you want to add
                        new ColorPickerDialog(MainActivity.this, colors)
                                .setOnColorChangedListener(new OnColorChangedListener() {
                                    @Override
                                    public void onColorChanged(int newColor) {
                                        // do something here
                                        String endName = "";
                                        Toast.makeText(MainActivity.this, "Color " + newColor, Toast.LENGTH_SHORT).show();
                                        if (newColor == -256) {
                                            endName = "default";
                                        } else if (newColor == -16776961) {
                                            endName = "blueone";
                                        } else if (newColor == -16711936) {
                                            endName = "green";
                                        } else if (newColor == -16711681) {
                                            endName = "bluetwo";
                                        } else if (newColor == -65536) {
                                            endName = "red";
                                        } else if (newColor == -12303292) {
                                            endName = "grayone";
                                        } else if (newColor == -3355444) {
                                            endName = "graytwo";
                                        } else if (newColor == -65281) {
                                            endName = "pink";
                                        }
                                        SkinManager.getInstance().changeSkin(endName);
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
    private ShareBoardlistener shareBoardlistener = new  ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            if (share_media==null){
                if (snsPlatform.mKeyword.equals("11")){
                    Toast.makeText(MainActivity.this,"add button success",Toast.LENGTH_LONG).show();
                }

            }
            else {
                UMImage image = new UMImage(MainActivity.this,
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                UMWeb web = new UMWeb("http://blog.csdn.net/qq_39357892" +
                        "");
                web.setTitle("Movie Center");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("一个神奇的视频APP");//描述
                new ShareAction(MainActivity.this).setPlatform(share_media).withMedia(web).setCallback(shareListener)
                        .withText("一个神奇的视频播放APP")
                        .share();
            }
        }
    };
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
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

    /*以下为语音集成*/
    //APPID
    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility. createUtility( this, SpeechConstant. APPID + "=5a2e498d" );
    }
    private void speekText() {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer( this, null);
        //2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录 13.2
        mTts.setParameter(SpeechConstant. VOICE_NAME, "vixyun" ); // 设置发音人
        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
        mTts.setParameter(SpeechConstant. VOLUME, "80" );// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant. TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
        //3.开始合成
        mTts.startSpeaking( edit_content.getText().toString(), (SynthesizerListener) new MySynthesizerListener()) ;
    }

    class MySynthesizerListener implements SynthesizerListener {

        @Override
        public void onSpeakBegin() {
            showTip(" 开始播放 ");
        }

        @Override
        public void onSpeakPaused() {
            showTip(" 暂停播放 ");
        }

        @Override
        public void onSpeakResumed() {
            showTip(" 继续播放 ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos ,
                                     String info) {
            // 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成 ");
            } else if (error != null ) {
                showTip(error.getPlainDescription( true));
            }
        }
        @Override
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
        }
    }

    private void startSpeechDialog() {
        //1. 创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener()) ;
        //2. 设置accent、 language等参数
        mDialog.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mDialog.setParameter(SpeechConstant. ACCENT, "mandarin" );
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        // 结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener( new MyRecognizerDialogListener()) ;
        //4. 显示dialog，接收语音输入
        mDialog.show() ;
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        /**
         * @param results
         * @param isLast  是否说完了
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString(); //为解析的
            showTip(result) ;
            System. out.println(" 没有解析的 :" + result);

            String text = JsonParser.parseIatResult(result) ;//解析过后的
            System. out.println(" 解析后的 :" + text);

            String sn = null;
            // 读取json结果中的 sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString()) ;
                sn = resultJson.optString("sn" );
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults .put(sn, text) ;//没有得到一句，添加到

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults .get(key));
            }

            edit_content.setText(resultBuffer.toString());// 设置输入框的文本
            edit_content .setSelection(edit_content.length()) ;//把光标定位末尾
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败 ");
            }

        }
    }

    /**
     * 语音识别
     */
    private void startSpeech() {
        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer( this, null); //语音识别器
        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
        mIat.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
        mIat.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mIat.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
        //3. 开始听写
        mIat.startListening( mRecoListener);
    }


    // 听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见 Demo中JsonParser 类；
        //isLast等于true 时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.e (TAG, results.getResultString());
            System.out.println(results.getResultString()) ;
            showTip(results.getResultString()) ;
        }

        // 会话发生错误回调接口
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true)) ;
            // 获取错误码描述
            Log. e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
        }

        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
        }

        // 扩展用接口
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
        }
    };

    private void showTip (String data) {
        /*Toast.makeText( this, data, Toast.LENGTH_SHORT).show() ;*/
    }

    //皮肤
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
