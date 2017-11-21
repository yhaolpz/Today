package com.yhao.today.dagger.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.yhao.today.commen.AppExecutors;
import com.yhao.today.commen.BaseApplication;
import com.yhao.today.dagger.module.AppModule;
import com.yhao.today.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    SharedPreferences sharedPreferences();

    BaseApplication baseApplication();

    AppExecutors AppExecutors();

}
