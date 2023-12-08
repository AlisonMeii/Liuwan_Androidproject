package com.example.liuwan

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import kotlinx.parcelize.Parcelize

lateinit var mydb:SQLiteDatabase

class HomePageActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var bottomsheet: BottomSheetBehavior<View>
    lateinit var slideMenu: SlideMenu
    lateinit var btn: ImageButton
    lateinit var b1: ImageButton
    lateinit var b2: ImageButton
    lateinit var b3: ImageButton
    lateinit var search_icon : ImageView
    lateinit var searchbox : EditText
    lateinit var park_result: List<Park>
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    private var mMapView: MapView? = null
    lateinit var mBaiduMap: BaiduMap
    lateinit var mLocationClient: LocationClient
    lateinit var locationMode: MyLocationConfiguration.LocationMode
    lateinit var bundle:Bundle

    var isFirstLocate = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        LocationClient.setAgreePrivacy(true);
        mMapView = findViewById<View>(R.id.bmapView) as MapView
        mBaiduMap = mMapView!!.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = LocationClient(this);
        var option = LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        var myLocationListener = MyLocationListene();
        mLocationClient.registerLocationListener(myLocationListener);
        mLocationClient.start();
        Log.d("HomePageActivity", "11111")
    }
    inner class MyLocationListene : BDAbstractLocationListener() {
         override fun onReceiveLocation(location: BDLocation) {
             if (location == null || mMapView == null) {
                 return;
             }
             var locData = MyLocationData.Builder()
                 .accuracy(location.radius)
                 .direction(location.direction).latitude(location.latitude)
                 .longitude(location.longitude).build()
             mBaiduMap.setMyLocationData(locData)
             val mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.location)
             locationMode = MyLocationConfiguration.LocationMode.NORMAL;
             val mLocationConfiguration =
                 MyLocationConfiguration(locationMode, true, mCurrentMarker)
             mBaiduMap.setMyLocationConfiguration(mLocationConfiguration);

             if (isFirstLocate) {
                 isFirstLocate = false;
                 var ll = LatLng(location.latitude, location.longitude)
                 val builder: MapStatus.Builder = MapStatus.Builder();
                 builder.target(ll).zoom(20.0f);

                 mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
             }

             val stringBuilder = StringBuilder()
             stringBuilder.append("\n经度：" + location.getLatitude());
             stringBuilder.append("\n纬度："+ location.getLongitude());
             stringBuilder.append("\n状态码："+ location.getLocType());
             stringBuilder.append("\n国家：" + location.getCountry());
             stringBuilder.append("\n城市："+ location.getCity());
             stringBuilder.append("\n区：" + location.getDistrict());
             stringBuilder.append("\n街道：" + location.getStreet());
             stringBuilder.append("\n地址：" + location.getAddrStr());
             Log.d("HomePageActivity", stringBuilder.toString())
         }
     }
    override fun onResume() {
        mMapView!!.onResume()
        super.onResume()
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
    }

    override fun onPause() {
        mMapView!!.onPause()
        super.onPause()
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
    }

    override fun onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView!!.onDestroy()
        mMapView = null;
        super.onDestroy()
    }

    fun init() {
        setContentView(R.layout.newhome)
        init_bottomsheet()
        init_btn()
        init_slide()
        init_searchbox()
        init_search_park()
        init_database()
        init_newhome()
/*
        显示地图
        initMap(savedInstanceState)
*/

/*        //显示地图
        initMap(savedInstanceState)*/

    }

    private fun init_newhome(){
        Log.d("DrawerLayout",R.id.mydrawer.toString())
        drawerLayout = findViewById(R.id.mydrawer)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.mine, R.string.out)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState() //这几行全是
    }

    private fun init_search_park(){
        search_icon = findViewById<ImageView>(R.id.search_icon)
        Log.d("click_search_box","init")
        search_icon.setOnClickListener(this)
    }

    private fun init_searchbox(){
        searchbox = findViewById(R.id.search_box)
    }

    private fun init_slide() {
        slideMenu = View.inflate(this, R.layout.activity_silde, null).findViewById(R.id.sd_1)
    }

    private fun init_bottomsheet() {
        bottomsheet = BottomSheetBehavior.from(findViewById<View>(R.id.ll_content_bottom_sheet))
    }

    private fun init_database() {
        val database_path =getDatabasePath("memo.db").toString()
        mydb =SQLiteDatabase.openDatabase(database_path,null,SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING)
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
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Running", "onResume")
        bottomSheetCtrl(bottomsheet)
    }

/*    //显示地图
    fun initMap(savedInstanceState: Bundle?){
        var context: Context = this
        MapsInitializer.updatePrivacyShow(context, true, true)
        MapsInitializer.updatePrivacyAgree(context, true)
        val mapView = findViewById<View>(R.id.amapView) as TextureMapView
        mapView.onCreate(savedInstanceState)
    }*/

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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        try {
            if (hasFocus) {
                var decorView = window.decorView
                var uiOption =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                decorView.setSystemUiVisibility(uiOption)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0){
                search_icon -> {
                    Log.d("click_search_icon","click")
                    park_result = getPark(searchbox.text.toString())

                    for(p in park_result)
                        Log.d("park_indices", p.toString())

                    intent = Intent(this,SearchrstActivity::class.java)
                    bundle = Bundle()
                    for(i in 0 until 5) {
                        bundle.putParcelable("park_info_" + i, park_result.get(i))
                        Log.d("park_info_"+i, park_result.get(i).toString())
                    }
                    intent.putExtras(bundle)
                    startActivity(intent);
                }
                else -> {
                    if (p0.background.alpha >= 200) {
                        when(p0) {
                            btn -> if (b1.background.alpha == 0) {
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
                            b2 -> if(b2.background.alpha >=200) {
                                startActivity(Intent(this,fatie::class.java))
                                finish()
                            }
                            b3 -> if(b3.background.alpha >= 200){
                                startActivity(Intent(this,explore1::class.java))
                                finish()
                            }
                            searchbox -> {
                                startActivity(Intent(this,SearchrstActivity::class.java))
                                finish()
                            }
                    }
                }
                Log.e("click_id",p0.id.toString())
                }
            }

        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    @Parcelize
    class Park():Parcelable {
        var name: String? = null
        var unit: String? = null
        var type: String? = null
        var level: String? = null
        var fee: String? = null
        var info: String? = null
        var url: String? = null
        var facilities: String? = null

        fun parkString(){
            var s = name+unit+type+level+fee+info+url+facilities;
            if(s==null)
                Log.d("parkString","None")
            else
                Log.d("parkString",s)
        }

    }

    @SuppressLint("Range")
    fun getPark(key: String): List<Park>{
        val list: MutableList<Park> = ArrayList()
        //            Cursor cursor = mydb.query("test", null, null, null, null, null, null);
        val cursor: Cursor = mydb.rawQuery("select * from test where name like '%$key%'", null)
        if (cursor.count > 0) {
            cursor.moveToFirst() //将cursor移动到第一个光标上
            val count = cursor.count
            //将cursor中的每一条记录生成一个question对象，并将该question对象添加到list中
            for (i in 0 until count) {
                cursor.moveToPosition(i)
                var park = Park()
                park.name = cursor.getString(cursor.getColumnIndex("name"))
                park.unit = cursor.getString(cursor.getColumnIndex("unit"))
                park.type = cursor.getString(cursor.getColumnIndex("type"))
                park.level = cursor.getString(cursor.getColumnIndex("level"))
                park.fee = cursor.getString(cursor.getColumnIndex("fee")).substring(9)
                park.info =
                    cursor.getString(cursor.getColumnIndex("info")).split(";;;;;;;;;;;;;")
                        .toTypedArray()[1].substring(5)
                park.url = "http://yllhj.beijing.gov.cn/ggfw/" + cursor.getString(
                    cursor.getColumnIndex("url")
                ).substring(6)
                park.facilities = cursor.getString(cursor.getColumnIndex("facilities"))
                list.add(park)
            }
            for (i in list.indices) {
                Log.d("database_park", "-----------------")
                Log.d("database_park", "名称: " + list[i].name)
                Log.d("database_park", "链接: " + list[i].url)
                Log.d("database_park", "公园等级: " + list[i].level)
                Log.d("database_park", "是否需要预约: " + list[i].fee)
                Log.d("database_park", "所属单位: " + list[i].unit)
                Log.d("database_park", "公园类别: " + list[i].type)
                Log.d("database_park", "对外服务车位资源: " + list[i].facilities)
                Log.d("database_park", "公园简介: " + list[i].info)
            }
        }
        return list
    }
}