package com.yhao.today.di.component;

import com.yhao.today.di.module.HomeFragmentModule;
import com.yhao.today.di.scope.FragmentScope;
import com.yhao.today.ui.home.HomeFragment;

import dagger.Component;

/**
 * Created by yhao on 2017/11/24.
 * https://github.com/yhaolpz
 */
@FragmentScope
@Component(modules = {HomeFragmentModule.class} ,dependencies = AppComponent.class)
public interface HomeFragmentComponent {
    void inject(HomeFragment homeFragment);
}
