package com.greedisland.gpsinfo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

//lateinit 延后初始化，定义了只能在onCreate中初始化的全局变量
@SuppressLint("StaticFieldLeak")
private lateinit var textView: TextView
private lateinit var locationManager: LocationManager

//定义一个权限COde，用来识别Location权限
private val LOCATION_PERMISSION = 1

//使用匿名内部类创建了LocationListener的实例
val locationListener = object : LocationListener {
    override fun onProviderDisabled(provider: String) {
        toast("关闭了GPS")
        textView.text = "关闭了GPS"
    }

    override fun onProviderEnabled(provider: String) {
        toast("打开了GPS")
        showLocation(textView, locationManager)
    }

    override fun onLocationChanged(location: Location?) {
        toast("变化了")
        showLocation(textView, locationManager)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //对lateinit的变量进行初始化
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        textView = find(R.id.text)

        //如果手机的SDK版本使用新的权限模型，检查是否获得了位置权限，如果没有就申请位置权限，如果有权限就刷新位置
        val hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
            //requestPermissions是异步执行的
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION)
        }
        else {
            showLocation(textView, locationManager)
        }
    }

    override fun onPause() {
        super.onPause()
        val hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if ((locationManager != null) && ((hasLocationPermission == PackageManager.PERMISSION_GRANTED))) {
            locationManager.removeUpdates(locationListener)
        }
    }

    override fun onResume() {
        //挂上LocationListener, 在状态变化时刷新位置显示，因为requestPermissionss是异步执行的，所以要先确认是否有权限
        super.onResume()
        val hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        if ((locationManager != null) && ((hasLocationPermission == PackageManager.PERMISSION_GRANTED))) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0F, locationListener)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,0F, locationListener)
            showLocation(textView, locationManager)
        }
    }
}