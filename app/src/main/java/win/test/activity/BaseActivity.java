package win.test.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import win.test.R;


public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        mUnbinder = ButterKnife.bind(this);
        initViews();
        initDatas();
        initEvents();
    }


    protected abstract int getLayoutResID();

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void initDatas();


    /**
     * 实现沉浸式通知栏与导航栏
     *
     * @param hasFocus 是否有焦点
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    public static void setActivityState(Activity activity) {
        // 设置App只能竖屏显示
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    /**
     * @param toolbar            Support v7 Toolbar
     * @param title              Toolbar的标题
     * @param subtitle           Toolbar 的子标题
     * @param menu               Toolbar 的菜单，如果为0则不初始化菜单
     * @param isNavigationEnable 是否显示返回按钮并响应点击返回操作
     */
    protected void setupToolbar(@NonNull final Toolbar toolbar,
                                @StringRes int title,
                                @StringRes int subtitle,
                                @MenuRes int menu,
                                boolean isNavigationEnable) {
        if (title != 0) {
            toolbar.setTitle(title);
        }
        if (subtitle != 0) {
            toolbar.setSubtitle(subtitle);
        }
        if (menu != 0) {
            toolbar.inflateMenu(menu);
        }
        if (isNavigationEnable) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity) (toolbar.getContext())).finish();
                }
            });
        }
    }

    overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
