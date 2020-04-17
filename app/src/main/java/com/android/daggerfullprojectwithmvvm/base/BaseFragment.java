package com.android.daggerfullprojectwithmvvm.base;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    public static final String TAG = BaseFragment.class.getSimpleName();


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    protected abstract void initPresenter();

    protected abstract void initComponents();
}
