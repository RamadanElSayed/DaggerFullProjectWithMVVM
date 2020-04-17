package com.android.daggerfullprojectwithmvvm.main.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.base.BaseFragment;
import com.android.daggerfullprojectwithmvvm.base.ViewModelProviderFactory;
import com.android.daggerfullprojectwithmvvm.databinding.FragmentPostBinding;
import com.android.daggerfullprojectwithmvvm.main.model.Post;
import com.android.daggerfullprojectwithmvvm.main.model.Resource;
import com.android.daggerfullprojectwithmvvm.main.viewmodel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PostFragment extends BaseFragment {

    private static final String TAG = "PostFragment";

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    PostViewModel postViewModel;
    @Inject
    PostAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private FragmentPostBinding fragmentPostBinding;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPostBinding=FragmentPostBinding.inflate(inflater);
        return fragmentPostBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=fragmentPostBinding.recyclerView;
        progressBar=fragmentPostBinding.progressBar;

        setRecyclerView();
        observeUserPosts();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void observeUserPosts() {
        postViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(PostViewModel.class);
        postViewModel.observeUserPosts().removeObservers(getViewLifecycleOwner());
        postViewModel.observeUserPosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> postPostResource) {
                if (postPostResource != null) {
                    switch (postPostResource.status) {
                        case LOADING:
                            showLoading(true);
                            break;
                        case SUCCESS:
                            showLoading(false);
                            showPosts(postPostResource.data);
                            break;
                        case ERROR:
                            showLoading(false);
                            showErrorDetails(postPostResource.message);
                            break;
                    }
                }
            }
        });
        postViewModel.getPosts();
    }

    private void showPosts(List<Post> posts) {
        recyclerAdapter.setData((ArrayList<Post>) posts);
        fragmentPostBinding.setAdapter(recyclerAdapter);
    }


    private void showLoading(boolean isShowing) {
        if (isShowing) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            fragmentPostBinding.error.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            fragmentPostBinding.error.setVisibility(View.GONE);
        }
    }



    private void showErrorDetails(String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        fragmentPostBinding.error.setVisibility(View.VISIBLE);
        fragmentPostBinding.error.setText(message);
    }
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initComponents() {

    }
}
