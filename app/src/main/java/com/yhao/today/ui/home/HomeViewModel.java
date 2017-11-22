package com.yhao.today.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.yhao.today.commen.net.Resource;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.repository.BingPicRepository;

import java.util.List;

/**
 * Created by yhao on 2017/11/21.
 * https://github.com/yhaolpz
 */

public class HomeViewModel extends ViewModel{
    private LiveData<Resource<BingPic>> mBingPicData;
    private BingPicRepository mBingPicRepository;

    public HomeViewModel() {
        mBingPicRepository = new BingPicRepository();
    }

    public LiveData<Resource<BingPic>> getBingPicData() {
        if (mBingPicData == null) {
            mBingPicData = new MediatorLiveData<>() ;
        }
        loadData();
        return mBingPicData;
    }


    private void loadData() {
        mBingPicData = mBingPicRepository.loadBingPic();
    }
}
