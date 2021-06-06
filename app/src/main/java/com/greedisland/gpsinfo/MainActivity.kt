package com.greedisland.gpsinfo

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGPS || hasNetwork) {

        }else{

        }
    }

}