package com.example.liuwan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    //////zqq-注册信息声明//////
    private static final String TAG ="tag" ;
    public static final int REQUEST_CODE_REGISTER = 1;
    //声明控件
    private Button mBtnLogin;
    private Button mBtnSign;
    private EditText mEtUser;
    private EditText mEtps;
    private RadioButton qagree;

    //zqq-先假定一个密码和账号
    private String useName=".";
    private String pass="123";

    //第一次创建
    private String first;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //zqq-数据存储
        initData();

//        //更改账户名或密码
//        private String change_account=null;
//        private String change_password=null;
//        SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
//        SharedPreferences.Editor edit = spf.edit();
//        edit.putString("password",change_account);
//        edit.putString("user",change_password);
//        edit.apply();
//
//        //调用用户名以及密码
//        SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
//        String account= spf.getString("user","");
//        String password= spf.getString("user","");


        //找到控件
        mBtnLogin = findViewById(R.id.bt_1);
        mBtnSign = findViewById(R.id.bt_2);
        mEtUser = findViewById(R.id.et_1);
        mEtps = findViewById(R.id.et_2);
        qagree=findViewById(R.id.checkBox);




            //利用intent 账号密码正确登录
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                ////////zqq-密码判断///////
                //获取账户名和密码
                String account = mEtUser.getText().toString();
                String password = mEtps.getText().toString();
                String firster="no";

                Log.d(TAG, "输入账户: "+account+"-------注册账户："+useName);
                Log.d(TAG, "输入密码: "+password+"-------注册密码："+pass);
                Log.d(TAG, "默认: "+firster+"-------存入："+first);


                //判断有没有注册
                if(TextUtils.equals(account,useName)==false){
                    Toast.makeText(LoginActivity.this,"还没注册呢~",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.equals(account,useName)){
                    if(TextUtils.equals(password,pass)){

                        if(qagree.isChecked()){
                            Toast.makeText(LoginActivity.this,"恭喜你，登录成功！",Toast.LENGTH_LONG).show();
                            if(!TextUtils.equals(firster,first)){
                                intent = new Intent(LoginActivity.this,SignupPersonal.class);
                                startActivity(intent);
                                //存入参数
                                SharedPreferences spf=getSharedPreferences("spfRecorid",MODE_PRIVATE);
                                SharedPreferences.Editor edit = spf.edit();
                                edit.putString("password",password);
                                edit.putString("user",account);
                                edit.putString("iffirst",firster);
                                edit.apply();
                            }else {
                                intent = new Intent(LoginActivity.this,HomePageActivity.class);
                                startActivity(intent);
                            }






                        }else {
                            Toast.makeText(LoginActivity.this,"请同意用户服务协议及隐私条款",Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"用户名错误",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void initData() {
        SharedPreferences spf =getSharedPreferences("spfRecorid",MODE_PRIVATE);
        String user = spf.getString("user", "");
        String password = spf.getString("password", "");
        String iffirst = spf.getString("iffirst", "");

        useName=user;
        pass=password;
        first=iffirst;

    }


    public void toRegister(View view) {
        Intent intent = new Intent(LoginActivity.this,SignupInfoActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_REGISTER&&resultCode==SignupInfoActivity.RESULT_CODE_REGISTER&&data!=null);{
            Bundle extras = data.getExtras();
            String user = extras.getString("user","");
            String password = extras.getString("password","");
            String iffirst = extras.getString("iffirst","");

            mEtUser.setText(user);
            mEtps.setText(password);

            first=iffirst;
            useName=user;
            pass=password;
        }
    }
}