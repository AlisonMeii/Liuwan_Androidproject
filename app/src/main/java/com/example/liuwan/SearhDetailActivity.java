package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SearhDetailActivity extends AppCompatActivity {
    private Button mFBtn;
    private Button mFBtn_1;
    private Button mBtn;
    private ImageView mFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searh_detail);

        mFBtn = findViewById(R.id.btn1);
        mFBtn_1 = findViewById(R.id.btn2);
        mFim = findViewById(R.id.imageView12);
        mBtn = findViewById(R.id.btn);

        //利用intent 直接跳转
        mFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearhDetailActivity.this,HomePageActivity.class); //这里应该是回到搜索页
                startActivity(intent);
            }
        });

        //利用intent 直接跳转
        mFBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearhDetailActivity.this,SearchDetailActivity2.class);
                startActivity(intent);
            }
        });

        //利用intent 直接跳转
        mFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearhDetailActivity.this,TipActivity.class);
                startActivity(intent);
            }
        });

        //利用intent 直接跳转
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearhDetailActivity.this,BookActivity.class);
                startActivity(intent);
            }
        });

    }

    //全屏显示
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}