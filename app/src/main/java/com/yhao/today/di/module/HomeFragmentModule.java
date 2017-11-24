package com.yhao.today.di.module;

import android.arch.lifecycle.ViewModelProviders;

import com.yhao.today.ui.home.HomeFragment;
import com.yhao.today.ui.home.HomeViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yhao on 2017/11/24.
 * https://github.com/yhaolpz
 */
@Module
public class HomeFragmentModule {

    private HomeFragment mHomeFragment;

    public HomeFragmentModule(HomeFragment homeFragment) {
        mHomeFragment = homeFragment;
    }

    @Provides
    public HomeViewModel providesHomeViewModel() {
        return ViewModelProviders.of(mHomeFragment).get(HomeViewModel.class);
    }



}
