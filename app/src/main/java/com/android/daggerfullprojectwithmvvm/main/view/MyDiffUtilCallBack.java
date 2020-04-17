package com.android.daggerfullprojectwithmvvm.main.view;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import com.android.daggerfullprojectwithmvvm.main.model.Post;
import java.util.ArrayList;

public class MyDiffUtilCallBack extends DiffUtil.Callback {
    ArrayList<Post> newList;
    ArrayList<Post> oldList;

    public MyDiffUtilCallBack(ArrayList<Post> newList, ArrayList<Post> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getId().equals(oldList.get(oldItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
