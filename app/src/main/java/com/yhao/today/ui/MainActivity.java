package com.yhao.today.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.yhao.today.R;
import com.yhao.today.di.component.DaggerMainActivityComponent;
import com.yhao.today.di.module.MainActivityModule;
import com.yhao.today.ui.favorite.FavoriteFragment;
import com.yhao.today.ui.home.HomeFragment;
import com.yhao.today.ui.notifications.NotificationsFragment;
import com.yhao.today.ui.person.PersonFragment;

import javax.inject.Inject;

/**
 * Created by yhao on 2017/11/19.
 * https://github.com/yhaolpz
 */


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @Inject
    HomeFragment mHomeFragment;

    @Inject
    FavoriteFragment mFavoriteFragment;

    @Inject
    NotificationsFragment mNotificationsFragment;

    @Inject
    PersonFragment mPersonFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.create().inject(this);



        BottomNavigationBar mBottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_home))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_border_black_24dp, R.string.title_favorite))
                .addItem(new BottomNavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_notifications))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_outline_black_24dp, R.string.title_person))
                .initialise();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mHomeFragment).commitAllowingStateLoss();

        mBottomNavigationBar.setTabSelectedListener(this);

    }



    @Override
    public void onTabSelected(int position) {
        changeFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    private void changeFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = mHomeFragment;
                break;
            case 1:
                fragment = mFavoriteFragment;
                break;
            case 2:
                fragment = mNotificationsFragment;
                break;
            case 3:
                fragment = mPersonFragment;
                break;
            default:
                fragment = null;
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commitAllowingStateLoss();
    }


}
