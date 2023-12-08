package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class explore1 extends AppCompatActivity implements View.OnClickListener {

    public ArrayList<HomePageActivity.Park> parks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore1);
        //获取按钮
        findViewById(R.id.imageButton2_1).setOnClickListener(this);
        findViewById(R.id.imageButton2).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton2:
                startActivity(new Intent(this,HomePageActivity.class));
                finish();
            case R.id.imageButton2_1:
                startActivity(new Intent(this,explore2.class));
                finish();
        }
    }
}

