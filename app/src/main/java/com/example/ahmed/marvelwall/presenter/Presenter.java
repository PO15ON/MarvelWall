package com.example.ahmed.marvelwall.presenter;

import com.example.ahmed.marvelwall.model.Post;

public class Presenter {

    private View view;

    public Presenter(View view) {
        this.view = view;
    }

    public void updatePost(Post post){
        view.updatePosts(post);
    }

    public interface View{
        void updatePosts(Post post);
    }
}
