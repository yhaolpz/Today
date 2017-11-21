package com.yhao.today.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.yhao.today.commen.AppExecutors;
import com.yhao.today.commen.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */
@Module
public class AppModule {

    private final BaseApplication mBaseApplication;

    public AppModule(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }


    //提供全局对象


    @Provides
    BaseApplication provideApplication() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    public AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    SharedPreferences provideSharedPreferences() {
        return mBaseApplication.getSharedPreferences("spfile", Context.MODE_PRIVATE);
    }
}
