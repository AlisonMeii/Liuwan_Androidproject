package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignupInfoActivity extends AppCompatActivity {

    public static final int RESULT_CODE_REGISTER = 0;
    //声明控件
    private Button mBtnNext;
    private EditText mEtem;
    private EditText mEtuser;
    private EditText mEtps;
    private EditText mEtpps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_info);


        //找到控件
        ImageButton mBtnBack = (ImageButton) findViewById(R.id.ibtn_1);
        mBtnNext = findViewById(R.id.bt_1);
        mEtem = findViewById(R.id.et_1);
        mEtuser = findViewById(R.id.et_2);
        mEtps = findViewById(R.id.et_3);
        mEtpps = findViewById(R.id.et_4);

        //利用intent 注册直接跳转
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(SignupInfoActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        //利用intent 账号密码正确登录
        mBtnNext.setOnClickListener(this::onClick);
    }


    public void onClick(View view ) {
        String email = mEtem.getText().toString();
        String user =mEtuser.getText().toString();
        String password = mEtps.getText().toString();
        String _password = mEtpps.getText().toString();
        String iffirst ="yes";
        //toast弹出内容设置；
        String sc ="顺利进入下一步";
        String fl = "请您再看看信息输对了吗^ ^";

        if (_password.equals(password) && password.length()!=0 && _password.length()!=0 && email.length()!=0&& user.length()!=0) {
            Toast.makeText(getApplicationContext(),sc,Toast.LENGTH_LONG).show();

            ///////zqq-记住密码//////
            SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
            SharedPreferences.Editor edit = spf.edit();
            edit.putString("password",password);
            edit.putString("user",user);
            edit.putString("iffirst",iffirst);
            edit.apply();
            ///////zqq/////////////
            //zqq-数据回传
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putString("user",user);
            bundle.putString("password",password);
            bundle.putString("iffirst",iffirst);
            intent.putExtras(bundle);
            setResult(RESULT_CODE_REGISTER,intent);
            //zqq-传参
            intent.putExtra("user",user);
            intent.putExtra("iffirst",iffirst);
            intent.putExtra("password",password);

            //toast提示登入成功
            this.finish();
        }else{
            //toast提示检查账号密码
            Toast.makeText(getApplicationContext(),fl,Toast.LENGTH_LONG).show();
        }

    }


}






