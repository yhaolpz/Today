package com.yhao.today.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yhao.today.R;
import com.orhanobut.logger.Logger;
import com.yhao.today.commen.net.Resource;
import com.yhao.today.pojo.BingPic;

import javax.inject.Inject;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

public class HomeFragment extends Fragment {


    private HomeViewModel mHomeViewModel;

    @Inject
    public HomeFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.getBingPicData().observe(this, new Observer<Resource<BingPic>>() {
            @Override
            public void onChanged(@Nullable Resource<BingPic> bingPicResource) {
                Logger.d("HomeFragment "+bingPicResource);
            }
        });



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        return view;
    }
}
