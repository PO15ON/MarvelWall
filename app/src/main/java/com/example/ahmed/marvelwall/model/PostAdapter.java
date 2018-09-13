package com.example.ahmed.marvelwall.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmed.marvelwall.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Post> postArrayList;

    public void updateLists(ArrayList<Post> posts) {
        postArrayList = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postArrayList.get(position);

        holder.postBody.setText(post.getBody());
        holder.postTitle.setText(post.getTitle());
        holder.userId.setText(String.valueOf("userId: " + post.getUserId()));
    }

    @Override
    public int getItemCount() {
        return postArrayList == null ? 0 : postArrayList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        final TextView userId, postTitle, postBody;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.user_id);
            postTitle = itemView.findViewById(R.id.post_title);
            postBody = itemView.findViewById(R.id.post_body);
        }
    }
}
