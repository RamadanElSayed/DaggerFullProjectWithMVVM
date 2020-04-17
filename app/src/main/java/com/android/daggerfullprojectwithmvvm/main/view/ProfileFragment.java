package com.android.daggerfullprojectwithmvvm.main.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.authentication.model.User;
import com.android.daggerfullprojectwithmvvm.base.BaseFragment;
import com.android.daggerfullprojectwithmvvm.base.NetworkStatusResponse;
import com.android.daggerfullprojectwithmvvm.base.ViewModelProviderFactory;
import com.android.daggerfullprojectwithmvvm.databinding.FragmentProfileBinding;
import com.android.daggerfullprojectwithmvvm.main.viewmodel.ProfileViewModel;

import javax.inject.Inject;

public class ProfileFragment extends BaseFragment {
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    ProfileViewModel profileViewModel;
    private FragmentProfileBinding fragmentProfileBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding=FragmentProfileBinding.inflate(inflater);
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileViewModel=new ViewModelProvider(this,viewModelProviderFactory).get(ProfileViewModel.class);
        observeLoginUser();
    }

    private void setUserDetails(User user) {
       fragmentProfileBinding.setModel(user);
    }

    public String makeToast()
    {
        return ",smxsxnsnx";
    }

    private void setErrorDetails(String message) {
        fragmentProfileBinding.name.setText(message);
    }

    private void observeLoginUser() {
        profileViewModel.observeLoginUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.observeLoginUser().observe(getViewLifecycleOwner(), new Observer<NetworkStatusResponse<User>>() {
            @Override
            public void onChanged(NetworkStatusResponse<User> resource) {
                if (resource != null) {
                    switch (resource.status) {
                        case AUTHENTICATED:
                            setUserDetails(resource.data);
                            break;
                        case ERROR:
                            setErrorDetails(resource.message);
                            break;
                    }
                }
            }
        });
    }
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {

    }
}
