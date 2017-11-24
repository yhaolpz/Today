
基于 Android 组件架构 1.0 的 Android 应用 <今天>
===========================================================

This is a sample app that uses Android Architecture Components with Dagger 2.

此 App 主要用于 Android 组件架构的实践学习。


关键库  ： Lifecycle + LiveData + ViewModel + Room/Retrofit + Dagger2

界  面  :  模仿获 2017 Material Design 设计奖应用 <Blinkist>

架  构  :  MVVM  ,   (关注点分离、模型驱动界面)

  M :  Repository Module 数据仓库模块

       负责处理数据，从网络/本地获取、持久化等

  V ： Activity/Fragment

       负责显示 UI 数据，处理交互、操作系统通信

 VM :  ViewModel 数据容器

       负责提供 UI 数据

目 标  :  打造一个属于自己的优秀的开发架构。


安卓组件架构 1.0 学习交流Q群 ： 675179777