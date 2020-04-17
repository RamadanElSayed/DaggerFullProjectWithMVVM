package com.android.daggerfullprojectwithmvvm.base;
import com.android.daggerfullprojectwithmvvm.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class MyApp extends DaggerApplication {

    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized MyApp getInstance() {
        return mInstance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
