package com.yhao.today.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yhao on 2017/11/23.
 * https://github.com/yhaolpz
 */

public class TimeUtil {


    public static String getDateStr() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        return sdf2.format(date);
    }



}
