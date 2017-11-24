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
import com.yhao.today.di.component.AppComponent;
import com.yhao.today.di.component.DaggerAppComponent;
import com.yhao.today.di.module.AppModule;

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


    private AppComponent mAppComponent;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
//
//        AppComponentHolder.setAppComponent(appComponent);

//        AppInjector.init(this);

        init();
        App.setmBaseApplication(this);
    }


    private BingPicDao bingPicDao;
    private TodayApi mTodayservice;
    private AppExecutors mAppExecutors;

    public BingPicDao getBingPicDao() {
        return bingPicDao;
    }

    public TodayApi getTodayservice() {
        return mTodayservice;
    }

    public AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    private void init() {
        //先不用di
        MyDatabase myDatabase = Room.databaseBuilder(this, MyDatabase.class, "today.db").build();

        bingPicDao = myDatabase.bingPicDao();


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("today", "OkHttp====Message:" + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("http://route.showapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build();

        mTodayservice = mRetrofit.create(TodayApi.class);


        mAppExecutors = new AppExecutors();


        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("today")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

    }


//    @Override
//    public AndroidInjector<Activity> activityInjector() {
//        return null;
//    }
}
