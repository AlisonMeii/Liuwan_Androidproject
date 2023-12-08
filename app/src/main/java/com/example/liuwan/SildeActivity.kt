package com.example.liuwan

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.example.liuwan.SlideMenu
import android.os.Bundle
import android.util.Log
import com.example.liuwan.R
import android.widget.ImageButton
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SildeActivity : AppCompatActivity(), View.OnClickListener {
    //和主页结合 声明点入我的那个button
    lateinit var slideMenu: SlideMenu
    lateinit var bottomsheet: BottomSheetBehavior<View>
    lateinit var btn: ImageButton
    lateinit var b1: ImageButton
    lateinit var b2: ImageButton
    lateinit var b3: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        init()

        setContentView(R.layout.activity_silde)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Running", "onResume")
        bottomSheetCtrl(bottomsheet)
    }

    fun init() {
        setContentView(R.layout.activity_silde)
        init_bottomsheet()
        init_btn()
        init_slide()
/*
        显示地图
        initMap(savedInstanceState)
*/
    }

    private fun init_bottomsheet() {
        bottomsheet = BottomSheetBehavior.from(findViewById<View>(R.id.ll_content_bottom_sheet))
    }

    private fun init_btn() {
        btn = findViewById(R.id.homepage_logo_btn)
        btn.background.alpha = 0
        btn.setOnClickListener(this)
        b1 = findViewById<ImageButton>(R.id.homepage_my_btn)
        b2 = findViewById<ImageButton>(R.id.homepage_release)
        b3 = findViewById<ImageButton>(R.id.homepage_explore)
        b1.background.alpha = 0
        b2.background.alpha = 0
        b3.background.alpha = 0
    }


    private fun init_slide() {
        slideMenu = findViewById(R.id.sd_1)
    }

    //全屏显示
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            if (p0.background.alpha >= 200) {
                if (p0 == btn) {
                    if (b1.background.alpha == 0) {
                        var objectAnimator1 = ObjectAnimator.ofInt(b1.background, "alpha", 0, 255);
                        objectAnimator1.setDuration(300);
                        objectAnimator1.start();
                        var objectAnimator2 = ObjectAnimator.ofInt(b2.background, "alpha", 0, 255);
                        objectAnimator2.setDuration(300);
                        objectAnimator2.start();
                        var objectAnimator3 = ObjectAnimator.ofInt(b3.background, "alpha", 0, 255);
                        objectAnimator3.setDuration(300);
                        objectAnimator3.start();
                    } else if (b1.background.alpha == 255) {
                        Log.e("alpha", btn.background.alpha.toString())
                        var objectAnimator1 = ObjectAnimator.ofInt(b1.background, "alpha", 255, 0);
                        objectAnimator1.setDuration(300);
                        objectAnimator1.start();
                        var objectAnimator2 = ObjectAnimator.ofInt(b2.background, "alpha", 255, 0);
                        objectAnimator2.setDuration(300);
                        objectAnimator2.start();
                        var objectAnimator3 = ObjectAnimator.ofInt(b3.background, "alpha", 255, 0);
                        objectAnimator3.setDuration(300);
                        objectAnimator3.start();
                    }
                } else if (p0 == b1) {
//                    slideMenu.switchMenu()
                }
            }
        }
    }

    private fun bottomSheetCtrl(bottomsheet: BottomSheetBehavior<View>) {
        Log.d("Running", bottomsheet.state.toString())
        bottomsheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED ->
                        Log.e("bottomSheet_stateChanged", "onStateChanged: STATE_COLLAPSED")
                    BottomSheetBehavior.STATE_DRAGGING ->
                        Log.e("bottomSheet_stateChanged", "onStateChanged: STATE_DRAGGING")
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        btn.background.alpha = 255
                        Log.e("bottomSheet_stateChanged", "onStateChanged: STATE_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED ->
                        Log.e("bottomSheet_stateChagned", "onStateChanged: STATE_HALF_EXPANDED")
                    BottomSheetBehavior.STATE_SETTLING ->
                        Log.e("bottomSheet_stateChanged", "onStateChanged: SSTATE_SETTLING")
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        TODO()
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.e("bottomSheet_slide", slideOffset.toString())
                btn.background.alpha = (slideOffset * 255).toInt()
                if (b1.background.alpha != 0) {
                    b1.background.alpha = (slideOffset * 255).toInt()
                    b2.background.alpha = (slideOffset * 255).toInt()
                    b3.background.alpha = (slideOffset * 255).toInt()
                }
            }
        })

    }
}