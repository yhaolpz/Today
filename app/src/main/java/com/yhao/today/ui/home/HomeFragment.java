package com.yhao.today.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.example.yhao.today.R;
import com.orhanobut.logger.Logger;
import com.yhao.today.api.Resource;
import com.yhao.today.api.Status;
import com.yhao.today.pojo.BingPic;
import com.yhao.today.pojo.HistoryToday;
import com.yhao.today.pojo.MovieOffice;
import com.yhao.today.ui.OnItemClickListener;
import com.yhao.today.util.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

public class HomeFragment extends Fragment implements View.OnClickListener {


    private HomeViewModel mHomeViewModel;

    @Inject
    public HomeFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mHomeViewModel.getBingPicData().observe(this, this::bindBingPicData);
        mHomeViewModel.getHistoryTodayData().observe(this, this::bindHistoryTodayData);
        mHomeViewModel.getMovieOfficeData().observe(this, new Observer<Resource<List<MovieOffice>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<MovieOffice>> listResource) {
                Logger.d(listResource);
            }
        });
    }

    private void bindHistoryTodayData(Resource<List<HistoryToday>> listResource) {
        if (listResource.status == Status.SUCCESS) {
            AutoClearedValue<HistoryTodayAdapter> historyTodayAdapter = new AutoClearedValue<>(
                    this, new HistoryTodayAdapter(listResource.data));
            historyTodayAdapter.get().setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(int position) {

                }
            });
            mHistoryTodayRecyclerView.setAdapter(historyTodayAdapter.get());
        }
    }


    private void bindBingPicData(Resource<BingPic> bingPicResource) {
        if (bingPicResource.status == Status.SUCCESS) {
            BingPic bingPic = bingPicResource.data;
            if (bingPic == null) {
                return;
            }
            if (!TextUtils.isEmpty(bingPic.getTitle())) {
                Glide.with(this).load(bingPic.getImg_1366()).signature(new StringSignature(bingPic.getTitle())).into(mBingPicIv);
            }
            mBingPicTitleTv.setText(bingPic.getTitle());
            mBingPicSubTitleTv.setText(bingPic.getSubtitle());
            mBingPicDescriptionTv.setText(bingPic.getDescription());
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //findView
        mBingPicIv = view.findViewById(R.id.bingPicIv);
        mBingPicTitleTv = view.findViewById(R.id.BingPicTitleTv);
        mBingPicSubTitleTv = view.findViewById(R.id.BingPicSubTitleTv);
        mBingPicDescriptionTv = view.findViewById(R.id.BingPicDescriptionTv);
        mToolbarMenuLink = view.findViewById(R.id.toolbarMenuLink);
        mToolbarMenuFind = view.findViewById(R.id.toolbarMenuFind);
        mBingPicFloatView = view.findViewById(R.id.bingPicFloatView);
        mHistoryTodayTitle = view.findViewById(R.id.historyTodayTitle);
        mHistoryTodayRecyclerView = view.findViewById(R.id.historyTodayRecyclerView);

        //initView
        mHistoryTodayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        //initEvent
        mToolbarMenuLink.setOnClickListener(this);
        mToolbarMenuFind.setOnClickListener(this);
        mBingPicFloatView.setOnClickListener(this);
        mHistoryTodayTitle.setOnClickListener(this);
        return view;
    }

    private ImageView mBingPicIv;
    private TextView mBingPicTitleTv;
    private TextView mBingPicSubTitleTv;
    private TextView mBingPicDescriptionTv;
    private ImageButton mToolbarMenuLink;
    private ImageButton mToolbarMenuFind;
    private View mBingPicFloatView;
    private LinearLayout mHistoryTodayTitle;
    private RecyclerView mHistoryTodayRecyclerView;


    @Override
    public void onClick(View v) {
        if (v == mToolbarMenuLink) {
        }

        if (v == mToolbarMenuFind) {
        }

        if (v == mBingPicFloatView) {

        }
        if (v == mHistoryTodayTitle) {

        }

    }
}
