package com.greedisland.gpsinfo

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        //使用匿名内部类创建了LocationListener的实例
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                var latitute = location!!.latitude
                var longitute = location!!.longitude
            }

            fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            fun onProviderEnabled(provider: String?) {
            }

            fun onProviderDisabled(provider: String?) {
            }
        }

    }

}