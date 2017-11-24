package com.yhao.today.di.component;

import com.yhao.today.di.module.AppModule;
import com.yhao.today.di.module.MainActivityModule;
import com.yhao.today.di.scope.ActivityScope;
import com.yhao.today.ui.MainActivity;

import dagger.Component;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

/*
    Component 有 modules 和  dependencies 两个属性

    modules的作用就是声明该Component含有哪几个Module,当Component需要某个依赖对象时,就会通过这些Module类中对应的方法获取依赖对象

    dependencies属性则是声明Component类的依赖关系
 */

@Component(modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
