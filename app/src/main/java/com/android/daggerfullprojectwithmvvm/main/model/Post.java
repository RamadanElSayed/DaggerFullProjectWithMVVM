package com.android.daggerfullprojectwithmvvm.main.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post implements Comparable, Cloneable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int compareTo(Object o) {
        Post compare = (Post) o;

        if (compare.id.equals(this.id) &&compare.userId == this.userId && compare.title.equals(this.title) && compare.body == (this.body)) {
            return 0;
        }
        return 1;
    }

    @Override
    public Post clone() {

        Post clone;
        try {
            clone = (Post) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); //should not happen
        }

        return clone;
    }
}
