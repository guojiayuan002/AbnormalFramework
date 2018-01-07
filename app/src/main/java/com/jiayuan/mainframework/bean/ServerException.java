package com.jiayuan.mainframework.bean;

import android.util.Log;

/**
 * @创建者 CSDN_LQR
 * @描述 服务器异常
 */
public class ServerException extends Exception {

    public ServerException(String errorCode) {
        super("错误码====" + errorCode);
        Log.e("错误码====",errorCode);
    }

//    public ServerException(String message) {
//        super(message);
//
//    }

}
