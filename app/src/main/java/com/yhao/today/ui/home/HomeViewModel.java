package com.yhao.today.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.yhao.today.api.Resource;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.repository.BingPicRepository;
import com.yhao.today.repository.HistoryTodayRepository;

import java.util.List;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

public class HomeViewModel extends ViewModel {
    private LiveData<Resource<BingPic>> mBingPicData;

    private LiveData<Resource<List<HistoryToday>>> mHistoryTodayData;

    private BingPicRepository mBingPicRepository;
    private HistoryTodayRepository mHistoryTodayRepository;

    public HomeViewModel() {
        mBingPicRepository = new BingPicRepository();
        mHistoryTodayRepository = new HistoryTodayRepository();
    }

    public LiveData<Resource<BingPic>> getBingPicData() {
        if (mBingPicData == null) {
            mBingPicData = new MediatorLiveData<>();
        }
        mBingPicData = mBingPicRepository.loadBingPic();
        return mBingPicData;
    }

    public LiveData<Resource<List<HistoryToday>>> getHistoryTodayData() {
        if (mHistoryTodayData == null) {
            mHistoryTodayData = new MediatorLiveData<>();
        }
        mHistoryTodayData = mHistoryTodayRepository.loadHistoryToday();
        return mHistoryTodayData;
    }



}
