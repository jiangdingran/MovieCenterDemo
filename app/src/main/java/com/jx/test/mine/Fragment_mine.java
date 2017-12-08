package com.jx.test.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.mummyding.colorpickerdialog.ColorPickerDialog;
import com.github.mummyding.colorpickerdialog.OnColorChangedListener;
import com.jx.test.R;
import com.jx.test.activity.LoginActivity;
import com.jx.test.activity.MeActivity;
import com.jx.test.activity.SaveActivity;
import com.jx.test.activity.SetActivity;
import com.jx.test.mine.utils.SharedPreferencesUtils;

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
    @BindView(R.id.goback)
    ImageView goback;
    @BindView(R.id.settv)
    TextView settv;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.gobackLayout)
    LinearLayout gobackLayout;
    @BindView(R.id.loginlayout)
    LinearLayout loginlayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        gobackLayout.setVisibility(View.GONE);
        settv.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.historymore, R.id.loadmore, R.id.save, R.id.themmore, R.id.mine_set,R.id.loginlayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.historymore:
                break;
            case R.id.loadmore:
                break;
            case R.id.save:
                startActivity(new Intent(getContext(), SaveActivity.class));
                break;
            case R.id.themmore:
                int[] colors = new int[]{Color.YELLOW, Color.BLACK, Color.BLUE, Color.GRAY,
                        Color.GREEN, Color.CYAN, Color.RED, Color.DKGRAY, Color.LTGRAY, Color.MAGENTA,
                        Color.rgb(100, 22, 33), Color.rgb(82, 182, 2), Color.rgb(122, 32, 12), Color.rgb(82, 12, 2),
                        Color.rgb(89, 23, 200), Color.rgb(13, 222, 23)};
                ColorPickerDialog dialog =
                        // Constructor,the first argv is Context,second one is the colors you want to add
                        new ColorPickerDialog(getActivity(), colors)
                                .setOnColorChangedListener(new OnColorChangedListener() {
                                    @Override
                                    public void onColorChanged(int newColor) {
                                        // do something here
                                        Toast.makeText(getActivity(), "Color " + newColor, Toast.LENGTH_SHORT).show();
                                        titleBarLayout.setBackgroundColor(newColor);
                                    }
                                })
                                .build()
                                .show();
                break;
            case R.id.mine_set:
                startActivity(new Intent(getContext(), SetActivity.class));
                break;
            case R.id.loginlayout:

                String login_code = SharedPreferencesUtils.getParam(getActivity(), "LOGIN_CODE", "3").toString();


                if(login_code.equals("0")){
                    Intent intent = new Intent(getActivity(), MeActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }


                break;
        }
    }


}
