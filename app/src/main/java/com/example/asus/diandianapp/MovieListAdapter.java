package com.example.asus.diandianapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.asus.diandianapp;




public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHoler> {



    private Context mContext;
    private OnItemClickListener mListener;

    public MovieListAdapter(Context context, OnItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }


    @Override
    public MovieViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view,parent,false);

        return new MovieViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHoler holder, final int position) {

        String name = MovieLab.get().getMovie(position);
        holder.bind(name);
        //
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MovieLab.get().getSize();
    }

    public class MovieViewHoler extends RecyclerView.ViewHolder{

        private TextView movieName;

        public MovieViewHoler(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
        }

        public  void  bind(String movieName){
            this.movieName.setText(movieName);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
