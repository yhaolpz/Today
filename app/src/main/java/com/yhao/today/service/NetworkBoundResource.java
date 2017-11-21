package com.yhao.today.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

/**
 * Created by yhao on 17-11-21.
 */

// ResultType: 数据源类型
// RequestType: API返回的类型

public abstract class NetworkBoundResource<ResultType, RequestType>  {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    // 被调用保存API返回的结果至数据库
    @WorkerThread
    protected abstract void saveCallResult( RequestType item);

    // 被调用去判断是否应该从网络获取数据
    @MainThread
    protected abstract boolean shouldFetch( ResultType data);

    // 被调用从数据库获取缓存数据
    @NonNull @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // 被调用创建API请求
    @NonNull @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    // 当获取数据失败时候调用
    @MainThread
    protected void onFetchFailed() {
    }

    // 返回代表数据源的LiveData
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

    @MainThread
    NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource,
                        newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // 重新连接dbSource作为新的源,
        //这样会快速分发最新的数据
        result.addSource(dbSource,
                newData -> result.setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            if (response.isSuccessful()) {
                saveResultAndReInit(response);
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> result.setValue(
                                Resource.error(response.errorMessage, newData)));
            }
        });
    }

    @MainThread
    private void saveResultAndReInit(ApiResponse<RequestType> response) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response.body);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // 我们专门请求一个新的LiveData
                // 另一方面获取最新的缓存数据，可能并不是网络请求得到的最新数据
                result.addSource(loadFromDb(),
                        newData -> result.setValue(Resource.success(newData)));
            }
        }.execute();
    }



}
