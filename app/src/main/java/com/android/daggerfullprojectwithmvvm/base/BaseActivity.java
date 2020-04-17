package com.android.daggerfullprojectwithmvvm.base;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.authentication.model.User;
import com.android.daggerfullprojectwithmvvm.authentication.view.LoginActivity;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@SuppressLint("Registered")
public abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Inject
    public SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
    }


    public void replaceCurrentFragment(int containerViewId, Fragment targetFragment, boolean addToBackStack) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(containerViewId, targetFragment, targetFragment.getClass().getName());
        if (addToBackStack) {
            ft.addToBackStack(targetFragment.getClass().getName());
        }
        ft.commit();

    }

    protected void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            if (getSupportActionBar() != null) {
                if (getSupportActionBar().isShowing())
                    getSupportActionBar().hide();
                else
                    getSupportActionBar().show();
            }
        } else {
            finish();
            super.onBackPressed();
        }
    }




    public void navLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
