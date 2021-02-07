package com.example.mvstudio.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.mvstudio.PopularMovieDetailActivity;
import com.example.mvstudio.R;
import com.example.mvstudio.models.Result;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>   {
    private List<Result> mmovies;
    private Context mContext;


    public MovieListAdapter(Context context, List<Result> movies) {
        mContext = context;
        mmovies = movies;
    }
    public class MovieViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        @BindView(R.id.restaurantImageView) ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindMovie(Result movie) {

            Picasso.get().load("https://image.tmdb.org/t/p/w300"+movie.getPosterPath()).into(mRestaurantImageView);
            // Picasso.get().load("https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg").fit().into(mRestaurantImageView);
            // Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath()).into(mRestaurantImageView);
            mNameTextView.setText(movie.getTitle());
            mCategoryTextView.setText(movie.getReleaseDate());
            mRatingTextView.setText("Rating: " + movie.getVoteAverage() + "/10");
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PopularMovieDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mmovies));
            mContext.startActivity(intent);

        }
    }

    @NonNull
    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mmovies.get(position));

    }

    @Override
    public int getItemCount() {
        return mmovies.size();
    }



}
