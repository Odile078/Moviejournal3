package com.example.mvstudio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvstudio.models.Result;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment  implements View.OnClickListener {
    @BindView(R.id.movieImageView) ImageView mImageLabel;
    @BindView(R.id.movieNameTextView) TextView mNameLabel;
    @BindView(R.id.genreTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveMovieButton) TextView mSaveMovieButton;

    private Result mMovie;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(Result movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load("https://image.tmdb.org/t/p/w300"+mMovie.getPosterPath()).into(mImageLabel);

       /* List<String> categories = new ArrayList<>();

        for (Category category: mmovie.getCategories()) {
            categories.add(category.getTitle());
        }*/

        mNameLabel.setText(mMovie.getTitle());
        //mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        // mRatingLabel.setText(Double.toString(mMovie.VoteAverage()) + "/5");
        mRatingLabel.setText("Rating: " + mMovie.getVoteAverage() + "/10");
        mPhoneLabel.setText(mMovie.getReleaseDate());
        mAddressLabel.setText(mMovie.getOverview());
        mWebsiteLabel.setOnClickListener(this);
        //mPhoneLabel.setOnClickListener(this);
       // mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.foxmovies.com/movies/"+mMovie.getTitle()));
            startActivity(webIntent);
        }


    }
}