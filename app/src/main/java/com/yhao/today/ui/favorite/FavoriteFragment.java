package com.yhao.today.ui.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yhao.today.R;

import javax.inject.Inject;

/**
 * Created by yhao on 2017/11/20.
 * https://github.com/yhaolpz
 */

public class FavoriteFragment extends Fragment {

    @Inject
    public FavoriteFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container,false);

        return view;
    }
}
