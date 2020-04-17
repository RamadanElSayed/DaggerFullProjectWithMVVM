package com.android.daggerfullprojectwithmvvm.di.login;

import com.android.daggerfullprojectwithmvvm.authentication.view.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginActivityBuildersModule {

    @LoginScope
    @ContributesAndroidInjector(modules = {LoginFragmentBuildersModule.class,
            LoginModule.class})
    abstract LoginActivity loginActivity();


}
