package com.android.daggerfullprojectwithmvvm.authentication.viewmodel;
import android.util.Log;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.android.daggerfullprojectwithmvvm.authentication.model.User;
import com.android.daggerfullprojectwithmvvm.authentication.view.LoginActivity;
import com.android.daggerfullprojectwithmvvm.base.NetworkStatusResponse;
import com.android.daggerfullprojectwithmvvm.base.SessionManager;
import com.android.daggerfullprojectwithmvvm.network.login.LoginApi;
import com.android.daggerfullprojectwithmvvm.utils.KeyboardUtil;

import java.util.Objects;
import javax.inject.Inject;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";
    private final LoginApi loginApi;
    private final SessionManager sessionManager;
    public ObservableField<String> observableField;

    MediatorLiveData<NetworkStatusResponse<User>>mediatorLiveData=new MediatorLiveData<>();
    MutableLiveData<NetworkStatusResponse<User>> liveData=new MutableLiveData<>();
    private int userId;

    @Inject
    public LoginViewModel( LoginApi loginApi, SessionManager sessionManager) {
        this.loginApi = loginApi;
        this.sessionManager = sessionManager;
        observableField=new ObservableField<>();
    }

    public void authenticateUser(LoginActivity loginActivity)
    {
        KeyboardUtil.dismissKeyboard(loginActivity);
        userId=Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(observableField.get()).trim()));
       // sessionManager.authenticateUserById(mediatorLiveData);
        liveData.setValue(NetworkStatusResponse.loading((User) null));
        loginApi.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    Log.d(TAG, "onResponse: " + user.getEmail());
                    liveData.postValue(NetworkStatusResponse.authenticated(user));
                    sessionManager.authenticateUserById(liveData);
                } else {
                    liveData.postValue(NetworkStatusResponse.error("Unable to login", (User) null));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                liveData.postValue(NetworkStatusResponse.error(t.getLocalizedMessage(), (User) null));
            }
        });
    }

    public void authenticateUserUsingRxJava(int userId) {
        sessionManager.authenticateUserById(LiveDataReactiveStreams.fromPublisher(loginApi.getUserRx(userId)
        .onErrorReturn(new Function<Throwable, User>() {
            @Override
            public User apply(Throwable throwable) throws Exception {
                User errorUser = new User();
                errorUser.setId(-1);
                return errorUser;
            }
        }).map(new Function<User, NetworkStatusResponse<User>>() {
                    @Override
                    public NetworkStatusResponse<User> apply(User user) throws Exception {
                        if (user.getId() == -1)
                            return NetworkStatusResponse.error("Unable to login", null);
                        return NetworkStatusResponse.authenticated(user);
                    }
                }).subscribeOn(Schedulers.io())));
    }
    public LiveData<NetworkStatusResponse<User>> observeLoginState()
    {
        mediatorLiveData.addSource(liveData, new Observer<NetworkStatusResponse<User>>() {
            @Override
            public void onChanged(NetworkStatusResponse<User> userNetworkStatusResponse) {
                mediatorLiveData.setValue(userNetworkStatusResponse);
            }
        });
        return mediatorLiveData;
    }
    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        super.onCleared();
    }
}
