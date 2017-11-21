package com.yhao.today.commen;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

public class App {
    public static BaseApplication mBaseApplication;

    public static BaseApplication get() {
        return mBaseApplication;
    }

    public static void setmBaseApplication(BaseApplication mBaseApplication) {
        App.mBaseApplication = mBaseApplication;
    }
}
