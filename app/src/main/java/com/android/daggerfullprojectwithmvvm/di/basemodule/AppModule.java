package com.android.daggerfullprojectwithmvvm.di.basemodule;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.base.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    static SessionManager sessionManager() {
        return new SessionManager();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.color.bink_color)
                .error(R.color.colorPrimary);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions) {
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppLogo(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.ic_launcher_background);
    }

}
