package com.yhao.today.commen;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yhao on 2017/11/19.
 * https://github.com/yhaolpz
 * 全局执行池
 */
public class AppExecutors {

    private static class AppExecutorsHolder{
        private static final AppExecutors instance = new AppExecutors();
    }

    public static AppExecutors getInstance() {
        return AppExecutorsHolder.instance;
    }

    private final ExecutorService mDiskIO;

    private final Executor mMainThread;


    private AppExecutors() {
        this.mDiskIO = Executors.newSingleThreadExecutor();
        this.mMainThread = new MainThreadExecutor();
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor mainThread() {
        return mMainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
