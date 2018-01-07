package com.jiayuan.mainframework.application;

import android.app.Application;
import android.content.Context;

import com.jiayuan.mainframework.base.BaseActivity;
import com.jiayuan.mainframework.network.BaseRetrofit;
import com.jiayuan.mainframework.network.LoginRetrofit;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guojiayuan on 2017/8/22.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private List<BaseActivity> mBaseActivityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //initHuanXin();
        LoginRetrofit.getInstance().init(getApplicationContext());
        BaseRetrofit.getInstance().init(getApplicationContext());

        ZXingLibrary.initDisplayOpinion(this);

    }

    public static Context getContext() {
        return mContext;
    }


    //mApplication = (QQApplication) getApplication();
//    mApplication.addActivity(this);
    public void addActivity(BaseActivity activity){
        if (!mBaseActivityList.contains(activity)){
            mBaseActivityList.add(activity);
        }
    }
    public void removeActivity(BaseActivity activity){
        mBaseActivityList.remove(activity);
    }
}
