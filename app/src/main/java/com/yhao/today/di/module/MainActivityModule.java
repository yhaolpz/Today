package com.yhao.today.di.module;

import com.yhao.today.ui.MainActivity;
import com.yhao.today.ui.favorite.FavoriteFragment;
import com.yhao.today.ui.home.HomeFragment;
import com.yhao.today.ui.notifications.NotificationsFragment;
import com.yhao.today.ui.person.PersonFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

@Module  //表明该类是module 负责提供实例
public class MainActivityModule {

    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }


    /**
     * 实例化对象有两种方法:
     * 1.可在此处设置 Provides方法,表明该方法用来提供实例(针对第三方库中无法在构造函数上添加Inject注解的情况)
     * 2.在构造函数上添加 Inject 注解
     * Component在所拥有的 Module 类中找不到依赖需求方需要类型的提供方法时,
     * Dagger2 就会检查该需要类型的有没有用 @Inject 声明的构造方法,有则用该构造方法创建一个
     */


    @Provides
    public HomeFragment provideHomeFragment() {
        return new HomeFragment();
    }

    @Provides
    public FavoriteFragment provideFavoriteFragment() {
        return new FavoriteFragment();
    }

    @Provides
    public NotificationsFragment provideNotificationsFragment() {
        return new NotificationsFragment();
    }

    @Provides
    public PersonFragment providePersonFragment() {
        return new PersonFragment();
    }
}
