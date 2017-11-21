package com.yhao.today.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
