package com.android.daggerfullprojectwithmvvm.base;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.android.daggerfullprojectwithmvvm.authentication.model.User;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";
    private MediatorLiveData<NetworkStatusResponse<User>> cachedUser = new MediatorLiveData<>();

    public void authenticateUserById(final LiveData<NetworkStatusResponse<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(NetworkStatusResponse.loading(null));
            cachedUser.addSource(source, new Observer<NetworkStatusResponse<User>>() {
                @Override
                public void onChanged(NetworkStatusResponse<User> resource) {
                    cachedUser.setValue(resource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logout() {
        Log.d(TAG, "logout: ");
        cachedUser.setValue(NetworkStatusResponse.<User>logout());
    }

    public LiveData<NetworkStatusResponse<User>> getLoginUser() {
        return cachedUser;
    }
}
