package com.yhao.today.commen;

import android.arch.persistence.room.Room;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.yhao.today.api.TodayApi;
import com.yhao.today.db.MyDatabase;
import com.yhao.today.util.RateLimiter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 * 依赖 BaseApplication 实现或较小的单例在此声明
 * 不依赖 BaseApplication 的单例且交庞大的类在内部自行实现
 * 此类用于维护 app 中的所有单例
 */

public class App {


    public static void init(BaseApplication mBaseApplication) {
        App.mBaseApplication = mBaseApplication;
        //初始化日志
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("today")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


    /**
     * 单例
     */
    private static BaseApplication mBaseApplication;
    private static TodayApi mTodayApi;
    private static MyDatabase mMyDatabase;
    private static RateLimiter<String> oneMinuteRateLimit = new RateLimiter<>(1, TimeUnit.MINUTES);


    public static BaseApplication get() {
        return mBaseApplication;
    }

    public static TodayApi getTodayApi() {
        if (mTodayApi == null) {
            mTodayApi = initTodayApi();
        }
        return mTodayApi;
    }

    public static MyDatabase getMyDatabase() {
        if (mMyDatabase == null) {
            mMyDatabase = initMyDatabase();
        }
        return mMyDatabase;
    }


    public static RateLimiter<String> getOneMinRateLimit() {
        return oneMinuteRateLimit;
    }

    public static AppExecutors getAppExecutors() {
        return AppExecutors.getInstance();
    }


    private static TodayApi initTodayApi() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("today", "OkHttp====Message:" + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://route.showapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(TodayApi.class);
    }

    private static MyDatabase initMyDatabase() {
        return Room.databaseBuilder(mBaseApplication, MyDatabase.class, "today.db")
                .fallbackToDestructiveMigration()
                .build();
    }


}
