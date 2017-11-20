package com.yhao.today.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.yhao.today.R;
import com.yhao.today.dagger.component.DaggerMainActivityComponent;
import com.yhao.today.dagger.module.MainActivityModule;

import javax.inject.Inject;

/**
 * Created by yhao on 2017/11/19.
 * https://github.com/yhaolpz
 */


public class MainActivity extends AppCompatActivity {

    @Inject HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent
                .builder()
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

        BottomNavigationBar mBottomNavigationBar = findViewById(R.id.bottomNavigationBar);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, R.string.title_home))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_border_black_24dp, R.string.title_favorite))
                .addItem(new BottomNavigationItem(R.drawable.ic_notifications_black_24dp, R.string.title_notifications))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_outline_black_24dp, R.string.title_person))
                .initialise();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mHomeFragment).commitAllowingStateLoss();

    }

}
