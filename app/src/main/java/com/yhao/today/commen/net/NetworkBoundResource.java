package com.yhao.today.commen.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.yhao.today.commen.AppExecutors;
import com.yhao.today.service.WrapResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yhao on 17-11-21.
 * ResultType: 数据源类型 ；RequestType: API返回的类型
 * 之所以设置两种类型是因为数据源类型 和 API返回的类型可能不是同一类型
 */


public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final AppExecutors appExecutors;

    //MediatorLiveData  中介
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();


    // 返回代表数据源的LiveData
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        //正在加载时显示数据，相当于 placeHolder 占位数据
        result.setValue(Resource.loading(null));
        //从数据库加载本地数据
        LiveData<ResultType> dbSource = loadFromDb();
        //监听本地数据
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType data) {
                //先取消监听本地数据
                //后面再重新监听本地数据,之所以取消后再监听，是因为重新监听可以较快的分发最新的数据
                result.removeSource(dbSource);
                //判断是否需要从网络上获取数据
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource);
                } else {
                    //监听本地数据
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@Nullable ResultType newData) {
                            //最终数据
                            result.setValue(Resource.success(newData));
                        }
                    });
                }
            }
        });


    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource)  {
        //发送请求，从网络获取数据
        LiveData<WrapResult<RequestType>> apiResponse = new LiveData<WrapResult<RequestType>>(){
            @Override
            protected void onActive() {
                super.onActive();
                createCall().enqueue(new Callback<WrapResult<RequestType>>() {
                    @Override
                    public void onResponse(Call<WrapResult<RequestType>> call, Response<WrapResult<RequestType>> response) {
                        postValue(new WrapResult<>(response));
                    }

                    @Override
                    public void onFailure(Call<WrapResult<RequestType>> call, Throwable t) {
                        postValue(new WrapResult<>(t));
                    }
                });
            }

        };
        //监听本地数据
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType newData) {
                //加载中数据
                result.setValue(Resource.loading(newData));
            }
        });
        //监听从网络获取的apiResponse
        result.addSource(apiResponse, new Observer<WrapResult<RequestType>>() {
            @Override
            public void onChanged(@Nullable WrapResult<RequestType> response) {
                //网络请求结束，取消对所有数据的监听
                result.removeSource(apiResponse);
                result.removeSource(dbSource);
                if (response.isSuccessFul()) {
                    saveResultAndReInit(response);
                } else {
                    onFetchFailed();
                    //监听本地数据
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@Nullable ResultType newData) {
                            result.setValue(Resource.error(response.getShowapi_res_error(), newData));
                        }
                    });
                }
            }

        });
    }


    @MainThread
    private void saveResultAndReInit(WrapResult<RequestType> response) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //保存到本地
                saveCallResult(processResponse(response));
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        //监听本地数据
                        result.addSource(loadFromDb(), new Observer<ResultType>() {
                            @Override
                            public void onChanged(@Nullable ResultType resultType) {
                                result.setValue(Resource.success(resultType));
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * 从 ApiResponse 中 获取数据
     */
    @WorkerThread
    protected RequestType processResponse(WrapResult<RequestType> response) {

        return response.getShowapi_res_body();
    }


    /**
     * 将网络上获取的数据缓存到本地
     */
    @WorkerThread
    protected abstract void saveCallResult(RequestType item);

    /**
     * 是否需要从网络上获取数据
     */
    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    /**
     * 从数据库获取数据
     */
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    /**
     * 从网络上获取数据
     * TODO 由于从retrofit直接返回livedata有问题，先用Call，模仿 LiveDataCallAdapter 中的转换过程
     */
    @NonNull
    @MainThread
    protected abstract Call<WrapResult<RequestType>> createCall();

    /**
     * 从网络上获取数据失败
     */
    @MainThread
    protected abstract void onFetchFailed();


}
