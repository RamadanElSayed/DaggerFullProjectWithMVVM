package com.android.daggerfullprojectwithmvvm.di.login;

import androidx.lifecycle.ViewModel;

import com.android.daggerfullprojectwithmvvm.authentication.view.LoginActivity;
import com.android.daggerfullprojectwithmvvm.authentication.view.LoginFragment;
import com.android.daggerfullprojectwithmvvm.authentication.viewmodel.LoginViewModel;
import com.android.daggerfullprojectwithmvvm.di.ViewModelKey;
import com.android.daggerfullprojectwithmvvm.network.login.LoginApi;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import retrofit2.Retrofit;

@Module
public abstract class LoginModule {


    @LoginScope
    @Provides
    static LoginApi provideLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }


    @LoginScope
    @Provides
    static LoginFragment loginFragment() {
        return new LoginFragment();
    }


    @LoginScope
    @Provides
    static LoginActivity loginActivity()
    {
        return new LoginActivity();
    }


    @LoginScope
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    public abstract ViewModel loginViewModel(LoginViewModel loginViewModel);


}
