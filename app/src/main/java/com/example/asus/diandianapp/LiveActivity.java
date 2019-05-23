package com.example.asus.diandianapp;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;

import com.bumptech.glide.util.Util;
import com.example.asus.diandianapp;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import static android.app.PendingIntent.getActivity;



public class LiveActivity extends AppCompatActivity {

    private PlayerView bofang;
    private Button quanping,fanghui;
    private RelativeLayout jiemian;
    private TextView biaoti;
//    Uri uri = Uri.parse(MovieLab.get().getUrl(intent.getIntExtra("pos",1 )));

//    String qwe = intent.getStringExtra("uri");
//    Uri uri = Uri.parse(intent.getStringExtra("uri"));

    SimpleExoPlayer player;
    static Boolean  aBoolean = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//状态栏隐藏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        //设置屏幕为横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//全屏
        //
//        getSupportActionBar().hide();//隐藏标题栏

        setContentView(R.layout.activity_live);

        jiemian = findViewById(R.id.jiemian);

        fanghui = findViewById(R.id.fanghui);
        fanghui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        quanping = findViewById(R.id.quanping);
        quanping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aBoolean == false) {
                    player.stop();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//全屏

                    aBoolean=true;
                }
                Log.d("LifeCycle",aBoolean.toString());
            }
        });

        if (aBoolean == true){
            quanping.setVisibility(View.GONE);
            jiemian.setVisibility(View.GONE);


        }

        Intent intent = getIntent();
        Uri uri = Uri.parse(intent.getStringExtra("uri"));
        String moviename = intent.getStringExtra("name");


        biaoti = findViewById(R.id.movieName);
        biaoti.setText(moviename);



        bofang = (PlayerView) findViewById(R.id.palyerview);

        player = ExoPlayerFactory.newSimpleInstance(this);
        //创建播放器
//        ExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        player.setPlayWhenReady(true);
        bofang.setPlayer(player);
        //
        DataSource.Factory factory = new DefaultDataSourceFactory(this, "asd");
//        new HlsMediaSource.Factory(factory).createMediaSource(this,);

        HlsMediaSource source = new HlsMediaSource.Factory(factory).createMediaSource(uri);
        player.prepare(source);

//        Toast.makeText(LiveActivity.this,intent.getStringExtra("uri"),Toast.LENGTH_LONG).show();

    }

    protected void onDestroy() {
        super.onDestroy();
        //销毁

        Log.d("LifeCycle", "--onDestroy--");
        if (player != null) {
            player.stop();
        }

    }

    protected void onPause() {
        super.onPause();
//        暂停
        Log.d("LifeCycle", "--onPause--");

        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
        }
    }

    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle", "--onStart--");

        if (player == null) {
            player.setPlayWhenReady(true);
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (aBoolean == false) super.onBackPressed();
        else {
            aBoolean = false;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
            Log.d("LifeCycle", aBoolean.toString());
        }
        Log.d("LifeCycle", "--onBackPressed--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        player.setPlayWhenReady(true);
        Log.d("LifeCycle", "--onRestart--");
    }
}
