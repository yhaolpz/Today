package com.yhao.today.dagger.module;

import android.support.v7.app.AppCompatActivity;

import com.yhao.today.dagger.scope.PerActivity;
import com.yhao.today.ui.HomeFragment;
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

    @Provides
    public HomeFragment provideHomeFragment() {
        return new HomeFragment();
    }
}
