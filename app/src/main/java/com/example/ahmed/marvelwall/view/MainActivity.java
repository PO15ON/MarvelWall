package com.example.ahmed.marvelwall.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.example.ahmed.marvelwall.R;
import com.example.ahmed.marvelwall.SimpleIdlingResource;
import com.example.ahmed.marvelwall.model.ApiClient;
import com.example.ahmed.marvelwall.model.ApiInterface;
import com.example.ahmed.marvelwall.model.Post;
import com.example.ahmed.marvelwall.model.PostAdapter;
import com.example.ahmed.marvelwall.presenter.Presenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Presenter.View {

    public final String TAG = this.getClass().getSimpleName();

    Presenter presenter;

    RecyclerView postsRv;
    PostAdapter postAdapter;

    ArrayList<Post> postArrayList;

    ApiInterface apiInterface;

    @Nullable
    SimpleIdlingResource idlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(MainActivity.this);

        postArrayList = new ArrayList<>();

        postsRv = findViewById(R.id.posts_rv);
        postAdapter = new PostAdapter();

        postsRv.setAdapter(postAdapter);
        postsRv.setHasFixedSize(true);
        postsRv.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Post>> getPosts = apiInterface.getPosts();
        getPosts.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {

                if (response.body() != null) {
                    for(Post post : response.body()){ // loop over every post in the api
                        presenter.updatePost(post); // update the presenter with the post
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void updatePosts(Post post) {
        postArrayList.add(post);
        postAdapter.updateLists(postArrayList);
    }
}
