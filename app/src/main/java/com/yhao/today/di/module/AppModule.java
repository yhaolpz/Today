package com.yhao.today.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.yhao.today.commen.AppExecutors;
import com.yhao.today.commen.BaseApplication;
import com.yhao.today.commen.LiveDataCallAdapterFactory;
import com.yhao.today.service.Webservice;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */
@Module()
public class AppModule {

    private final BaseApplication mBaseApplication;

    public AppModule(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }

    //提供全局对象

    @Singleton @Provides
    BaseApplication provideApplication() {
        return mBaseApplication;
    }


    @Singleton @Provides
    Webservice provideWebService() {
        return new Retrofit.Builder()
                .baseUrl("http://route.showapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(Webservice.class);
    }



    @Singleton @Provides
    SharedPreferences provideSharedPreferences() {
        return mBaseApplication.getSharedPreferences("spfile", Context.MODE_PRIVATE);
    }
}
