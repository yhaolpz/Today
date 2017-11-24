package com.yhao.today.di.component;

import android.content.SharedPreferences;

import com.yhao.today.api.TodayApi;
import com.yhao.today.commen.BaseApplication;
import com.yhao.today.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {
    /*
         通过inject方法依赖需求方实例送到Component中, 从而帮助依赖需求方实现依赖
         但是我们这个BaseComponent是给其他Component提供依赖的，所以不需要inject方法
     */

    TodayApi getTodayApi();

    SharedPreferences getSharedPreferences();

    BaseApplication getBaseApplication();

}
