package com.jiayuan.mainframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guojiayuan on 2017/6/4.
 */

public class CheckPwdUtils {


    public static boolean checkPwd(String pwd) {
        int legth = pwd.length();
        if (legth >= 6 && legth <= 20) {
            String regex = "^[A-Za-z0-9]+$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(pwd);

            while (m.matches()) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
