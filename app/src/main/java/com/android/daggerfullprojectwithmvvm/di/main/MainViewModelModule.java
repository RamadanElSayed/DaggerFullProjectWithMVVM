package com.android.daggerfullprojectwithmvvm.di.main;

import androidx.lifecycle.ViewModel;

import com.android.daggerfullprojectwithmvvm.di.ViewModelKey;
import com.android.daggerfullprojectwithmvvm.main.viewmodel.PostViewModel;
import com.android.daggerfullprojectwithmvvm.main.viewmodel.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel postViewModel(PostViewModel postViewModel);

    @MainScope
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel profileViewModel(ProfileViewModel profileViewModel);
}
