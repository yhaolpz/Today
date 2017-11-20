package com.yhao.today.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */
@Module
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    /**
     * 全局唯一
     * @return
     */
    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }



//    @Provides @Singleton
//    public ToastUtil provideToastUtil(){
//        return new ToastUtil(context);
//    }
}
