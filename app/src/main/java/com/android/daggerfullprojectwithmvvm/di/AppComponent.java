package com.android.daggerfullprojectwithmvvm.di;

import android.app.Application;

import com.android.daggerfullprojectwithmvvm.base.MyApp;
import com.android.daggerfullprojectwithmvvm.di.basemodule.AppModule;
import com.android.daggerfullprojectwithmvvm.di.basemodule.NetworkModule;
import com.android.daggerfullprojectwithmvvm.di.basemodule.ViewModelFactoryModule;
import com.android.daggerfullprojectwithmvvm.di.login.LoginActivityBuildersModule;
import com.android.daggerfullprojectwithmvvm.di.main.MainActivityBuilderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class,
        NetworkModule.class, LoginActivityBuildersModule.class,ViewModelFactoryModule.class, MainActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<MyApp> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
