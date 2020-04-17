package com.android.daggerfullprojectwithmvvm.main.view;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.daggerfullprojectwithmvvm.R;
import com.android.daggerfullprojectwithmvvm.databinding.ItemPostLayoutBinding;
import com.android.daggerfullprojectwithmvvm.main.model.Post;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> postList = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostLayoutBinding postLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_post_layout, parent, false);
        return new PostViewHolder(postLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = postList.get(position);
        holder.itemPostLayoutBinding.setPost(post);
    }


    public void setData(ArrayList<Post> newData) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallBack(newData, postList));
        postList.clear();
        postList.addAll(newData);
        diffResult.dispatchUpdatesTo(this);

    }

    @Override
    public int getItemCount() {
        if (postList != null)
            return postList.size();
        else
            return 0;

    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ItemPostLayoutBinding itemPostLayoutBinding;

        PostViewHolder(@NonNull ItemPostLayoutBinding itemView) {
            super(itemView.getRoot());
            itemPostLayoutBinding = itemView;
        }
    }
}
