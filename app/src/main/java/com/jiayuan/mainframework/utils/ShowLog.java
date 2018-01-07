package com.jiayuan.mainframework.utils;

import android.util.Log;

/**
 * Created by guojiayuan on 2017/6/2.
 */

public class ShowLog {
    public static boolean isDebug = true;

    public static void e(Class clazz, String msg) {
        if (isDebug) {
            // TAG 当前类名，
            //ShowLog.d();
            Log.d(clazz + "====", msg);
        }
    }

    public static void syso(String msg) {
        System.out.println("====" + msg + "====");

    }
}
