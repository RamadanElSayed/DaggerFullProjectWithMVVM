package com.android.daggerfullprojectwithmvvm.di.login;

import com.android.daggerfullprojectwithmvvm.authentication.view.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginFragmentBuildersModule {

    @ContributesAndroidInjector
    public abstract LoginFragment loginFragment();


}
