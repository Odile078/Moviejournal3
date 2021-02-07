package com.example.mvstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MovieActivity extends AppCompatActivity {
    int[] images = {R.drawable.cenolaholmes, R.drawable.charvie, R.drawable.christmaschrinicals, R.drawable.cmagicmoonlight, R.drawable.cmagictoafrica, R.drawable.cnightmuseum, R.drawable.cspiderwickk, R.drawable.ctheoldguard, R.drawable.chisdarkmaterials, R.drawable.cgrownish, R.drawable.csuits, R.drawable.ctinyprettythings, R.drawable.cemilyinparis, R.drawable.cgodmothered};

    String[] movie = {"Enola Holmes", "Harvie", "Christmas Chronicals", "MAgic Moonlight", "Magic Of Africa", "Night Museum", "Spider Wick", "The Old Guard", "His Dark Materials", "Grownish", "Suits", "Tiny Pretty Things", "Emily In PAris", "Godmothered"};

    String[] genre = {"Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama", "Adventure,Drama,Dance", "Adventure,Drama", "Adventure,fantasy"};

    ListView lView;

    ListAdapter lAdapter;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        NavigationView navigationView = findViewById((R.id.navi_view));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    startActivity (new Intent(MovieActivity.this, MainActivity.class));

                }
                else if(item.getItemId()==R.id.nav_movie){
                    startActivity(new Intent(MovieActivity.this,  PopularMovieListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_watchlist){
                    startActivity(new Intent(MovieActivity.this, ListFormActivity.class));

                }
                else if(item.getItemId()==R.id.nav_form){
                    startActivity (new Intent(MovieActivity.this, WatchListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_account){
                    startActivity (new Intent(MovieActivity.this, ProfileActivity.class));

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });

        lView = (ListView) findViewById(R.id.androidList);

        lAdapter = new ListAdapter(MovieActivity.this, movie, genre, images);

        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MovieActivity.this, movie[i]+" "+genre[i], Toast.LENGTH_SHORT).show();

            }
        });
    }
}