package com.yhao.today.commen;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.yhao.today.db.MyDatabase;
import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.service.Webservice;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

//public class BaseApplication extends Application implements HasActivityInjector{
public class BaseApplication extends Application {

//    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();

//        AppComponent appComponent = DaggerAppComponent
//                .builder()
//                .appModule(new AppModule(this))
//                .build();
//
//        AppComponentHolder.setAppComponent(appComponent);

//        AppInjector.init(this);

        init();
        App.setmBaseApplication(this);
    }


    private BingPicDao bingPicDao;
    private Webservice mWebservice;
    private AppExecutors mAppExecutors;

    public BingPicDao getBingPicDao() {
        return bingPicDao;
    }

    public Webservice getWebservice() {
        return mWebservice;
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

        mWebservice = mRetrofit.create(Webservice.class);


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
