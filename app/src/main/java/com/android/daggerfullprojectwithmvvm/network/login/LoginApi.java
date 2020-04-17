package com.android.daggerfullprojectwithmvvm.network.login;
import com.android.daggerfullprojectwithmvvm.authentication.model.User;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface LoginApi {

    @GET("/users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("/users/{id}")
    Flowable<User> getUserRx(@Path("id") int userId);
}