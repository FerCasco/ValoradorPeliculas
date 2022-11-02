package com.fernandocasco.recycled;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private ArrayList<Movie> movies;
    MovieViewModel model;
    public MainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Recycler sample", "Fragmento creado");
        loadViewModel();
        if(movies==null) {
            populateMovies();
        }
        loadRecyclerMovies();
    }

    private void loadRecyclerMovies() {
        //Conectamos los datos con el adaptador
        RecyclerView rvMovies = getView().findViewById(R.id.rv_movies);
        rvMovies.setAdapter(new MoviesAdapter(getContext(), movies));
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    private void loadViewModel() {
        model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }


    private void populateMovies() {
        movies = model.getMovies();
        String[] titles = getResources().getStringArray(R.array.titles);
        int[] years = getResources().getIntArray(R.array.years);
        String[] directors = getResources().getStringArray(R.array.directors);
        String[] links = getResources().getStringArray(R.array.webs);
        String[] trailers = getResources().getStringArray(R.array.trailers);
        for(int i=0;i<4;i++){
            movies.add(new Movie(titles[i],years[i],directors[i],links[i],trailers[i]));
        }



    }
}