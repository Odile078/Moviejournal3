package com.example.mvstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mvstudio.adapters.MovieListAdapter;
import com.example.mvstudio.models.MoviedbPopularResponse;
import com.example.mvstudio.models.Result;
import com.example.mvstudio.network.MoviedbApi;
import com.example.mvstudio.network.MoviedbClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovieListActivity extends AppCompatActivity {
    private static final String TAG = PopularMovieListActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private MovieListAdapter mAdapter;
    public List<Result> movies;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie_list);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        MoviedbApi client = MoviedbClient.getClient();
        Call<MoviedbPopularResponse> call = client.getMovies();
        call.enqueue(new Callback<MoviedbPopularResponse>() {
            @Override
            public void onResponse(Call<MoviedbPopularResponse> call, Response<MoviedbPopularResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    movies = response.body().getResults();
                    mAdapter = new MovieListAdapter(PopularMovieListActivity.this, movies);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PopularMovieListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRestaurants();

                } else {
                    showUnsuccessfulMessage();
                }

            }

            @Override
            public void onFailure(Call<MoviedbPopularResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();

            }
        });

        NavigationView navigationView = findViewById((R.id.navi_view));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    startActivity (new Intent(PopularMovieListActivity.this, MainActivity.class));

                }
                else if(item.getItemId()==R.id.nav_movie){
                    startActivity(new Intent(PopularMovieListActivity.this, PopularMovieListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_watchlist){
                    startActivity(new Intent(PopularMovieListActivity.this, ListFormActivity.class));

                }
                else if(item.getItemId()==R.id.nav_form){
                    startActivity (new Intent(PopularMovieListActivity.this, WatchListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_account){
                    startActivity (new Intent(PopularMovieListActivity.this, ProfileActivity.class));

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    /* private void showRestaurants() {
         mListView.setVisibility(View.VISIBLE);
         mLocationTextView.setVisibility(View.VISIBLE); }*/
    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}