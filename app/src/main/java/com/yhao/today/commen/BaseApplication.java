package com.yhao.today.commen;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.yhao.today.api.TodayApi;
import com.yhao.today.db.MyDatabase;
import com.yhao.today.db.dao.BingPicDao;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */


public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        App.init(this);
    }
}
