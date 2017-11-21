package com.yhao.today.di.module;

import com.yhao.today.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

@Module
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity){
        this.activity = activity;
    }

    @Provides
    public MainActivity provideActivity(){
        return activity;
    }


    /**
     * 实例化对象有两种方法:
     * 1.可在此处设置Provides方法,(针对第三方库中无法在构造函数上添加Inject注解的情况)
     * 2.在构造函数上添加Inject注解
     */
//    @Provides
//    public HomeFragment provideHomeFragment() {
//        return new HomeFragment();
//    }
}
