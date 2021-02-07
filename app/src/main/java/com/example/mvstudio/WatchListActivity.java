package com.example.mvstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
//import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchListActivity extends AppCompatActivity {
   // @BindView(R.id.EditTextSearch) EditText EditTextSearch;
    //@BindView(R.id.imageButton) ImageButton imageButton;
    @BindView(R.id.rv) RecyclerView recyclerView;
    @BindView(R.id.searchView) SearchView searchView;
   // @BindView(R.id.listview) ListView myListView;


    List<Movies> moviesList;
    ArrayList<Movies> list;
   // RecyclerView recyclerView;

    DatabaseReference moviesdb;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        moviesList = new ArrayList<>();
        String uid = user.getUid();
        //moviesdb = FirebaseDatabase.getInstance().getReference("Movies");
        moviesdb = FirebaseDatabase.getInstance().getReference("Movies").child(uid);


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
                    startActivity (new Intent(WatchListActivity.this, MainActivity.class));

                }
                else if(item.getItemId()==R.id.nav_movie){
                    startActivity(new Intent(WatchListActivity.this,  PopularMovieListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_watchlist){
                    startActivity(new Intent(WatchListActivity.this, ListFormActivity.class));

                }
                else if(item.getItemId()==R.id.nav_form){
                    startActivity (new Intent(WatchListActivity.this, WatchListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_account){
                    startActivity (new Intent(WatchListActivity.this, ProfileActivity.class));

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });

       // Intent intent = getIntent();
       // String movieName = intent.getStringExtra("movieName");
       // String movieCategory = intent.getStringExtra("movieCategory");
      //  String movieDetail = intent.getStringExtra("movieDetail");
       // movienameTextView.setText("Here are all the movie: " + movieName);
       // moviecategoryTextView.setText("Here are all the Category: " + movieCategory);
      //  moviedetailTextView.setText("Here are all the Detail: " + movieDetail);

       /* moviesdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                moviesList.clear();
                for (DataSnapshot movieDatasnap : dataSnapshot.getChildren()){
                    Movies movies = movieDatasnap.getValue(Movies.class);
                    moviesList.add(movies);
                }

                ListAdapter2 adapter = new ListAdapter2(WatchListActivity.this,moviesList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(moviesdb != null){
            moviesdb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()){
                            list.add(studentDatasnap.getValue(Movies.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(WatchListActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }
        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return false;

                }
            });
        }

    }

    private void search(String str){
        ArrayList<Movies> myList = new ArrayList<>();
        for(Movies object : list)
        {
            if(object.getCategory().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);

    }



}