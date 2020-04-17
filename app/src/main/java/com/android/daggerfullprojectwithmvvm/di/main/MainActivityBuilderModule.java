package com.android.daggerfullprojectwithmvvm.di.main;

import com.android.daggerfullprojectwithmvvm.main.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuilderModule.class,
            MainModule.class, MainViewModelModule.class})
    public abstract MainActivity mainActivity();
}
