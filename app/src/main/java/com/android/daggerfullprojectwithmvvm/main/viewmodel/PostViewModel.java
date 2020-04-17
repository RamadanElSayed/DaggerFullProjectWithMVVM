package com.android.daggerfullprojectwithmvvm.main.viewmodel;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import com.android.daggerfullprojectwithmvvm.base.SessionManager;
import com.android.daggerfullprojectwithmvvm.main.model.Post;
import com.android.daggerfullprojectwithmvvm.main.model.Resource;
import com.android.daggerfullprojectwithmvvm.network.main.MainApi;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";

    private MediatorLiveData<Resource<List<Post>>> postData = new MediatorLiveData<>();

    private SessionManager sessionManager;
    private final MainApi postApi;


    @Inject
    public PostViewModel(final SessionManager sessionManager, final MainApi postApi) {
        this.sessionManager = sessionManager;
        this.postApi = postApi;
    }

    public void getPosts() {
        int userId=getUserId();
        postData.setValue(Resource.loading(null));
        postApi.getPosts(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postData.setValue(Resource.success(response.body()));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postData.setValue(Resource.error("Unable to fetch user posts - " + t.getLocalizedMessage(),
                        (List<Post>) null));
            }
        });
    }

    public void getRXPosts(int userId)
    {
//        postData.setValue(Resource.loading(null));
//        LiveData<Resource<List<Post>>> listLiveData= LiveDataReactiveStreams.fromPublisher(postApi.getPostsRx(userId).onErrorReturn(
//                new Function<Throwable, Resource<List<Post>>>() {
//            @Override
//            public Resource<List<Post>> apply(Throwable throwable) throws Exception {
//                Post post=new Post();
//                post.setId(-1);
//                 Resource<List<Post>>listResource=new Resource<>(Resource.Status.ERROR,post,)
//            }
//        }).map(new Function<List<Post>, List<Post>>() {
//             @Override
//             public List<Post> apply(List<Post> posts) throws Exception {
//                 return posts;
//             }
//        }).subscribeOn(Schedulers.io()));
//


    }
    private int getUserId() {
       return Objects.requireNonNull(sessionManager.getLoginUser().getValue()).data.getId();
    }

    public LiveData<Resource<List<Post>>> observeUserPosts() {
        return postData;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }


}
