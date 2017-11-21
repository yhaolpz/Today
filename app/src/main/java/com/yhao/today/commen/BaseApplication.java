package com.yhao.today.commen;

import android.app.Application;

import com.yhao.today.dagger.component.AppComponent;
import com.yhao.today.dagger.component.AppComponentHolder;
import com.yhao.today.dagger.component.DaggerAppComponent;
import com.yhao.today.dagger.module.AppModule;

import javax.inject.Inject;


/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

public class BaseApplication extends Application {

    @Inject AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        AppComponent appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

        AppComponentHolder.setAppComponent(appComponent);



    }
}
