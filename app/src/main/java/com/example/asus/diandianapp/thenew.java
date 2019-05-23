package com.example.asus.diandianapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.asus.diandianapp;


public class thenew extends AppCompatActivity {

    private TextView mTV1;
    private String channelName;
    private String getChannelID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thenew);

        Intent intent = getIntent();

        mTV1 = (TextView) findViewById(R.id.tv_title);
        mTV1.setText(intent.getStringExtra("传递了"+"name"));

    }






}
