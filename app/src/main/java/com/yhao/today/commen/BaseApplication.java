package com.yhao.today.commen;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;

import com.yhao.today.db.MyDatabase;
import com.yhao.today.db.dao.BingPicDao;
import com.yhao.today.service.Webservice;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

//public class BaseApplication extends Application implements HasActivityInjector{
public class BaseApplication extends Application{

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
        MyDatabase myDatabase = Room.databaseBuilder(this, MyDatabase.class,"today.db").build();

        bingPicDao = myDatabase.bingPicDao();

        mWebservice = new Retrofit.Builder()
                    .baseUrl("http://route.showapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .build()
                    .create(Webservice.class);

        mAppExecutors=new AppExecutors();

        }







//    @Override
//    public AndroidInjector<Activity> activityInjector() {
//        return null;
//    }
}
