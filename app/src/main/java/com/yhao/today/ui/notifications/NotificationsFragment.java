package com.yhao.today.ui.notifications;

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

public class NotificationsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container,false);

        return view;
    }
}
