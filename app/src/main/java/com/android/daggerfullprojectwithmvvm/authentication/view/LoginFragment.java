package com.android.daggerfullprojectwithmvvm.authentication.view;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.daggerfullprojectwithmvvm.main.view.MainActivity;
import com.android.daggerfullprojectwithmvvm.authentication.model.User;
import com.android.daggerfullprojectwithmvvm.authentication.viewmodel.LoginViewModel;
import com.android.daggerfullprojectwithmvvm.base.BaseFragment;
import com.android.daggerfullprojectwithmvvm.base.NetworkStatusResponse;
import com.android.daggerfullprojectwithmvvm.base.ViewModelProviderFactory;
import com.android.daggerfullprojectwithmvvm.databinding.FragmentLoginBinding;
import com.bumptech.glide.RequestManager;

import java.util.Objects;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding fragmentLoginBinding;
    private static final String TAG = "LoginFragment";

    @Inject
    Drawable appLogo;

    @Inject
    RequestManager glideManager;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private LoginViewModel loginViewModel;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLoginBinding=FragmentLoginBinding.inflate(inflater);
        loginViewModel=new ViewModelProvider(this,viewModelProviderFactory).get(LoginViewModel.class);
        progressBar=fragmentLoginBinding.progressBar;
        fragmentLoginBinding.setViewModel(loginViewModel);
        fragmentLoginBinding.setLoginActivity((LoginActivity) getActivity());
        setLogo();
        observeUser();
        return fragmentLoginBinding.getRoot();
    }
    private void setLogo() {
        glideManager.load(appLogo).into(fragmentLoginBinding.appLogo);
    }

    private void observeUser() {
        loginViewModel.observeLoginState().observe(getViewLifecycleOwner(), new Observer<NetworkStatusResponse<User>>() {
            @Override
            public void onChanged(NetworkStatusResponse<User> userNetworkStatusResponse) {
                if(userNetworkStatusResponse!=null)
                {
                    switch (userNetworkStatusResponse.status)
                    {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case AUTHENTICATED:
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS - " + userNetworkStatusResponse.data.getEmail());
                            onLoginSuccess();
                            break;
                        case NOT_AUTHENTICATED:
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: LOGOUT");
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), userNetworkStatusResponse.message, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onChanged: ERROR - " + userNetworkStatusResponse.message);
                            break;
                    }

                }
            }
        });
    }

    private void onLoginSuccess() {
        Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), MainActivity.class));
        Objects.requireNonNull(getActivity()).finish();


    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {

    }
}
