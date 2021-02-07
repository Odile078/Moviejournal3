package com.example.mvstudio;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter2  extends ArrayAdapter {
    private Activity mcontext;
    List<Movies> moviesList;
    public ListAdapter2(Activity mcontext, List<Movies> moviesList){
        super(mcontext,R.layout.list_item,moviesList);
        this.mcontext = mcontext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mcontext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);

        TextView viewname= listItemView.findViewById(R.id.viewname);
        TextView viewcategory = listItemView.findViewById(R.id.viewcategory);
        TextView viewdetail = listItemView.findViewById(R.id.viewdetail);

        Movies movies = moviesList.get(position);

        viewname.setText(movies.getName());
        viewcategory.setText(movies.getCategory());
        viewdetail.setText(movies.getDetail());

        return listItemView;


    }
}
