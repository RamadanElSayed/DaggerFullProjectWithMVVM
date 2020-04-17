package com.android.daggerfullprojectwithmvvm.di.main;

import com.android.daggerfullprojectwithmvvm.main.view.PostFragment;
import com.android.daggerfullprojectwithmvvm.main.view.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    public abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    public abstract PostFragment postFragment();
}
