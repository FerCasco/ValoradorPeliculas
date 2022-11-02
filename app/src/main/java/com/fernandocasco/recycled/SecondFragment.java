package com.fernandocasco.recycled;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class SecondFragment extends Fragment {
    private ArrayList<Movie> movies;
    MovieViewModel model;
    Movie m;
    public SecondFragment(Movie movie) {
        m = movie;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadViewModel();
        movies = model.getMovies();
        TextView tvTitle = getView().findViewById(R.id.tv_titleInfo);
        TextView tvYear = getView().findViewById(R.id.tv_yearInfo);
        TextView tvDirector = getView().findViewById(R.id.tv_directorInfo);
        TextView tvLink = getView().findViewById(R.id.tv_linkInfo);
        WebView wv = getView().findViewById(R.id.webView);
        wv.setVisibility(View.INVISIBLE);
        tvTitle.setText(m.getTitle());
        tvYear.setText(String.valueOf(m.getYear()));
        tvDirector.setText(m.getDirector());
        tvLink.setText(m.getLink());
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(m.getLink()));
                startActivity(browserIntent);
            }
        });
        Button btPlay = getView().findViewById(R.id.bt_trailer);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.setVisibility(View.VISIBLE);
                wv.setWebViewClient(new WebViewClient());
                wv.loadUrl(m.getTrailer());
                wv.setWebChromeClient(new WebChromeClient());
                WebSettings ws = wv.getSettings();
                ws.setJavaScriptEnabled(true);
                ws.setPluginState(WebSettings.PluginState.ON);
            }
        });
        RatingBar ratingBar = getView().findViewById(R.id.ratingBarMovie);
        ratingBar.setRating(m.getRating());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                m.setRating(v);
            }
        });



    }

    private void loadViewModel() {
        model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
    }

}