package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SignupPersonal<News> extends AppCompatActivity {
    private ImageView mFBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_personal);

        mFBtn = findViewById(R.id.iv_5);

        //利用intent 注册直接跳转
        mFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sc ="进入下一步";
                Intent intent = null;
                intent = new Intent(SignupPersonal.this,SignupPersonalSnd.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),sc,Toast.LENGTH_LONG).show();
            }
        });

    }
}
