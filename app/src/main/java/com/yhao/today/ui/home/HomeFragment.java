package com.yhao.today.ui.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.bumptech.glide.signature.StringSignature;
import com.example.yhao.today.R;
import com.orhanobut.logger.Logger;
import com.yhao.today.api.Resource;
import com.yhao.today.api.Status;
import com.yhao.today.commen.BaseApplication;
import com.yhao.today.di.component.DaggerHomeFragmentComponent;
import com.yhao.today.di.module.HomeFragmentModule;
import com.yhao.today.pojo.BingPic;

import javax.inject.Inject;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    @Inject
    HomeViewModel mHomeViewModel;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DaggerHomeFragmentComponent.builder()
                .homeFragmentModule(new HomeFragmentModule(this))
                .appComponent(((BaseApplication)getActivity().getApplication()).getAppComponent())
                .build().inject(this);

        mHomeViewModel.getBingPicData().observe(this, new Observer<Resource<BingPic>>() {
            @Override
            public void onChanged(@Nullable Resource<BingPic> bingPicResource) {
                bindBingPicData(bingPicResource);

            }
        });
    }


    private void bindBingPicData(Resource<BingPic> bingPicResource) {
        if (bingPicResource.status == Status.SUCCESS) {
            BingPic bingPic = bingPicResource.data;
            Glide.with(this).load(bingPic.getImg_1366()).signature(new StringSignature(bingPic.getTitle())).into(mBingPicIv);
            mBingPicTitleTv.setText(bingPic.getTitle());
            mBingPicSubTitleTv.setText(bingPic.getSubtitle());
            mBingPicDescriptionTv.setText(bingPic.getDescription());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mBingPicIv = view.findViewById(R.id.bingPicIv);
        mBingPicTitleTv = view.findViewById(R.id.BingPicTitleTv);
        mBingPicSubTitleTv = view.findViewById(R.id.BingPicSubTitleTv);
        mBingPicDescriptionTv = view.findViewById(R.id.BingPicDescriptionTv);
        mToolbarMenuLink = view.findViewById(R.id.toolbarMenuLink);
        mToolbarMenuFind = view.findViewById(R.id.toolbarMenuFind);
        mBingPicContentLL = view.findViewById(R.id.BingPicContentLL);
        mBingPicContentLL.setOnClickListener(this);
        mToolbarMenuLink.setOnClickListener(this);
        mToolbarMenuFind.setOnClickListener(this);
        return view;
    }

    private ImageView mBingPicIv;
    private TextView mBingPicTitleTv;
    private TextView mBingPicSubTitleTv;
    private TextView mBingPicDescriptionTv;
    private ImageButton mToolbarMenuLink;
    private ImageButton mToolbarMenuFind;
    private LinearLayout mBingPicContentLL;

    @Override
    public void onClick(View v) {
        if (v == mToolbarMenuLink) {
        }

        if (v == mToolbarMenuFind) {
        }

        if (v == mBingPicContentLL) {

        }

    }
}
