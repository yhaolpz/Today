//package com.yhao.today.di.component;
//
//import android.app.Application;
//import android.content.SharedPreferences;
//
//import com.yhao.today.commen.AppExecutors;
//import com.yhao.today.commen.BaseApplication;
//import com.yhao.today.di.module.AppModule;
//import com.yhao.today.di.module.MainActivityModule;
//
//import javax.inject.Singleton;
//
//import dagger.BindsInstance;
//import dagger.Component;
//import dagger.android.AndroidInjectionModule;
//
///**
// * Created by yhao on 2017/11/20.
// * https://github.com/yhaolpz
// */
//
//@Singleton
//@Component(modules = {
//        AndroidInjectionModule.class,
//        AppModule.class,
//        MainActivityModule.class
//})
//public interface AppComponent {
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(Application application);
//        AppComponent build();
//    }
//    void inject(BaseApplication baseApplication);
//}
