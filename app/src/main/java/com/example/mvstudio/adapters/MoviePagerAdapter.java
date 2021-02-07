package com.example.mvstudio.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mvstudio.MovieDetailFragment;
import com.example.mvstudio.models.Result;

import java.util.List;

public class MoviePagerAdapter  extends FragmentPagerAdapter {
    private List<Result> mMovies;

    public MoviePagerAdapter(FragmentManager fm, int behavior, List<Result> movies) {
        super(fm, behavior);
        mMovies = movies;
    }

    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mMovies.get(position));
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMovies.get(position).getTitle();
    }
}