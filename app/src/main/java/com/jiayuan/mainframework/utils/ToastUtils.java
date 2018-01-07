package com.jiayuan.mainframework.utils;

import android.widget.Toast;

import com.jiayuan.mainframework.application.MyApplication;


/**
 * Created by guojiayuan on 2017/6/2.
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showToast( String msg) {
        if (sToast == null) {

            sToast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT);
        }
        //如果这个Toast已经在显示了，那么这里会立即修改文本
        sToast.setText(msg);
        sToast.show();
    }

}
