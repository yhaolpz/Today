package com.yhao.today.dagger.component;

import android.content.Context;

import com.yhao.today.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

    Context getContext();


}
