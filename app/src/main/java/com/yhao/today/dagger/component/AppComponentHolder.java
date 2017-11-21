package com.yhao.today.dagger.component;

/**
 * Created by yhao on 17-11-21.
 * 方便获取appComponent对象
 */

public class AppComponentHolder {
    private static AppComponent myAppComponent;

    public static void setAppComponent(AppComponent component) {
        myAppComponent = component;
    }

    public static AppComponent getAppComponent() {
        return myAppComponent;
    }
}
