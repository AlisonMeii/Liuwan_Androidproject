package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class explore2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore2);
        findViewById(R.id.BACK2).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.BACK2){
            startActivity(new Intent(this,HomePageActivity.class));
            finish();
        }
    }
}