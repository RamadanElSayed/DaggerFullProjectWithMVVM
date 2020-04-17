package com.android.daggerfullprojectwithmvvm.di.basemodule;

import androidx.lifecycle.ViewModelProvider;

import com.android.daggerfullprojectwithmvvm.base.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

}
