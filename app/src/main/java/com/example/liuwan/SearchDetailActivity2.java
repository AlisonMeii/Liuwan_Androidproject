package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SearchDetailActivity2 extends AppCompatActivity {
    private Button mFBtn;
    private ImageView mFim;
    private ImageView mFim_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail2);

        mFBtn = findViewById(R.id.btn);
        mFim = findViewById(R.id.imageView12);
        mFim_1 = findViewById(R.id.iv1);

        //利用intent 直接跳转
        mFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearchDetailActivity2.this,SearhDetailActivity.class);
                startActivity(intent);
            }
        });

        //利用intent 直接跳转
        mFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearchDetailActivity2.this,TipActivity.class);
                startActivity(intent);
            }
        });

        //利用intent 直接跳转
        mFim_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SearchDetailActivity2.this,TipActivity.class); //这里应该是进入探索页
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