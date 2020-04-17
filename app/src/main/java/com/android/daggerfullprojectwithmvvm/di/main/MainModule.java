package com.android.daggerfullprojectwithmvvm.di.main;

import com.android.daggerfullprojectwithmvvm.main.view.PostAdapter;
import com.android.daggerfullprojectwithmvvm.main.view.PostFragment;
import com.android.daggerfullprojectwithmvvm.main.view.ProfileFragment;
import com.android.daggerfullprojectwithmvvm.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    private static final String TAG = "MainModule";

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static PostAdapter postRecyclerAdapter() {
        return new PostAdapter();
    }


    @MainScope
    @Provides
    static PostFragment postFragmentObject() {
        return new PostFragment();
    }


    @MainScope
    @Provides
    static ProfileFragment postProfileFragment() {
        return new ProfileFragment();
    }
}
