package com.fernandocasco.recycled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies;
    private Context context;
    private LayoutInflater inflater;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_movies, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
        holder.tvTitle.setText(movies.get(position).getTitle());
        holder.tvYear.setText(String.valueOf((movies.get(position).getYear())));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) context)
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView2,
                                new SecondFragment(movies.get(holder.getAdapterPosition())))
                        .addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvYear;


        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
        }
    }
}
