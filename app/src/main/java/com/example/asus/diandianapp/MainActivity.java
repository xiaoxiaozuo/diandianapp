package com.example.asus.diandianapp;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.asus.diandianapp;


public class MainActivity extends AppCompatActivity {

    private RecyclerView movieList;
    private MovieListAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = (RecyclerView) findViewById(R.id.movielist);
//        movieAdapter = new MovieListAdapter();

        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(new MovieListAdapter(MainActivity.this, new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                String movieName = MovieLab.get().getMovie(pos);
                String movieUrl = MovieLab.get().getUrl(pos);
//                Toast.makeText(MainActivity.this,"点击"+movieUrl,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,LiveActivity.class);
                intent.putExtra("uri",movieUrl);
                intent.putExtra("name",movieName);
                startActivity(intent);
            }
        }));

//        movieList.setAdapter(movieAdapter);
        movieList.addItemDecoration(new MyDecoration());//在每个元素下面添加分割线
//        movieList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }


    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}
