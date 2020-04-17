package com.android.daggerfullprojectwithmvvm.main.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.daggerfullprojectwithmvvm.authentication.model.User;
import com.android.daggerfullprojectwithmvvm.base.NetworkStatusResponse;
import com.android.daggerfullprojectwithmvvm.base.SessionManager;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;


    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public LiveData<NetworkStatusResponse<User>> observeLoginUser() {
        return sessionManager.getLoginUser();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }
}
