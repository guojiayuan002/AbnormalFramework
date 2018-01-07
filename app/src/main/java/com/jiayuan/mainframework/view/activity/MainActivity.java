package com.jiayuan.mainframework.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.jiayuan.mainframework.R;
import com.jiayuan.mainframework.otherbase.Base2Activity;
import com.jiayuan.mainframework.otherbase.Base2Presenter;
import com.jiayuan.mainframework.otherbase.BaseFragment2;
import com.jiayuan.mainframework.utils.AndroidWorkaround;
import com.jiayuan.mainframework.utils.FragmentFactory;
import com.jiayuan.mainframework.utils.StatusBarUtils;

public class MainActivity extends Base2Activity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mBottomNavigationBar;
    private TextBadgeItem mBadgeItem;

    @Override
    protected int getResView() {
        //设置沉浸式状态栏，这个activity中所有的Fragment都会沉浸
        StatusBarUtils.setStatusBar(this);
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        initBottomNavigation();
        initFirstFragment();
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((MainActivity) mContext, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    @Override
    protected Base2Presenter createPresenter() {
        return null;
    }

    private void initBottomNavigation() {

        BottomNavigationItem contactItem = new BottomNavigationItem(R.mipmap.hostclick, "首页");
        mBottomNavigationBar.addItem(contactItem);

        BottomNavigationItem nearbItem = new BottomNavigationItem(R.mipmap.meclick3x, "消息");
        mBottomNavigationBar.addItem(nearbItem);

        BottomNavigationItem conversationItem = new BottomNavigationItem(R.mipmap.cartclick3x, "购物车");
        mBadgeItem = new TextBadgeItem();
        mBadgeItem.setGravity(Gravity.RIGHT);
        mBadgeItem.setTextColor("#ffffff");
        mBadgeItem.setBackgroundColor("#ff0000");
        mBadgeItem.setText("5");
        mBadgeItem.show();

        conversationItem.setBadgeItem(mBadgeItem);
        mBottomNavigationBar.addItem(conversationItem);


        BottomNavigationItem pluginItem = new BottomNavigationItem(R.mipmap.meclick3x, "我的");
        mBottomNavigationBar.addItem(pluginItem);
        // mBottomNavigationBar.setActiveColor(R.color.btn_normal);//背景颜色的
        mBottomNavigationBar.setInActiveColor(R.color.inActive);//未选中的颜色
        mBottomNavigationBar.hide(false);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//设置显示底部文字，且没动画
        mBottomNavigationBar.initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
    }

    private void initFirstFragment() {
        /**
         * 如果这个Activity中已经有老（就是Activity保存的历史的状态，又恢复了）的Fragment，先全部移除
         */
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < 3; i++) {
            Fragment fragment = supportFragmentManager.findFragmentByTag(i + "");
            if (fragment != null) {
                fragmentTransaction.remove(fragment);
            }
        }
        fragmentTransaction.commit();

        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, FragmentFactory.getFragment(0), "0").commit();

    }

    @Override
    public void onTabSelected(int position) {
        /**
         * 先判断当前Fragment是否被添加到了MainActivity中
         * 如果添加了则直接显示即可
         * 如果没有添加则添加，然后显示
         */
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseFragment2 fragment = FragmentFactory.getFragment(position);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_content, fragment, "" + position);
        }
        transaction.show(fragment).commit();

    }

    @Override
    public void onTabUnselected(int position) {
        getSupportFragmentManager().beginTransaction().hide(FragmentFactory.getFragment(position)).commit();
    }

    @Override
    public void onTabReselected(int position) {
    }
}
