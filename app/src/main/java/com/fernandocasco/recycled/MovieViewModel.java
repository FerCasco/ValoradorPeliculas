package com.fernandocasco.recycled;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class MovieViewModel extends AndroidViewModel {
    ArrayList<Movie> movies;
    public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public ArrayList<Movie> getMovies() {
        if(movies==null){
            movies = new ArrayList<>();
        }
        return movies;

    }
}
