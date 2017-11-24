package com.rosinante24.healthyline.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.rosinante24.healthyline.R;
import com.rosinante24.healthyline.fragments.AmbulanceFragment;
import com.rosinante24.healthyline.fragments.HospitalFragment;
import com.rosinante24.healthyline.fragments.PuskesmasFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        fragment = new HospitalFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment).commit();
        setTitle("Hospital");

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home:
                        fragment = new HospitalFragment();
                        setTitle("Hospital");
                        break;
                    case R.id.navigation_dashboard:
                        fragment = new PuskesmasFragment();
                        setTitle("Puskesmas");
                        break;
                    case R.id.navigation_notifications:
                        fragment = new AmbulanceFragment();
                        setTitle("Ambulance");
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment).commit();
                return true;
            }
        });
    }

}
