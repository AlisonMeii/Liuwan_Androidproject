package com.example.liuwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchrstActivity extends AppCompatActivity {
    private View bottom_fliterwindow;
    private BottomSheetDialog mBottomSheetDialog;
    private ImageButton btn;
    private RelativeLayout park;
    private ArrayList<HomePageActivity.Park> parks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchrst);
        bottom_fliterwindow = LayoutInflater.from(this).inflate(R.layout.bottom_filterwindow, null);
        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(bottom_fliterwindow);

        park = findViewById(R.id.park_bar);
        park.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1)
            {
                Intent intent = new Intent(SearchrstActivity.this,SearhDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn = findViewById(R.id.fliter);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p1)
            {
                mBottomSheetDialog.show();
            }
        });

        getParks();

    }

    private void getParks(){
//        Intent intent = this.getIntent();
//        Bundle bundle = intent.getExtras();
//        HomePageActivity.Park p = (HomePageActivity.Park)bundle.getParcelable("park_info_0");
//        Log.d("park_info_0", bundle.getParcelable("park_info_0").toString());
//        int i = 0;
//        while(p != null) {
//            parks.add( p);
//            i+=1;
//            p =bundle.getParcelable("park_info_"+i);
//        }
//
//        for(HomePageActivity.Park park:parks)
//            if(park!=null) {
//                park.parkString();
////                Log.d("park_from_search", park.toString());
//
//            }
    }

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