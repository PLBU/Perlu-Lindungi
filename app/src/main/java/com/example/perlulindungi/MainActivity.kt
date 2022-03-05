package com.example.perlulindungi

import android.Manifest
import android.content.pm.PackageManager
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.perlulindungi.databinding.ActivityMainBinding
import com.example.perlulindungi.ui.scan.ScanActivity
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import android.os.HandlerThread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationReq: LocationRequest
    private lateinit var locationCB: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setDefaultNightMode(MODE_NIGHT_NO)
        setTheme(R.style.Theme_PerluLindungi)
        setContentView(binding.root)

        //navigation settings
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        setupWithNavController(navView, navController)

        navView.itemIconTintList = null
        navView.setupWithNavController(navController)

        //floating button settings
        val button : FloatingActionButton = binding.scanBtn
        button.setOnClickListener {
            val scanIntent = Intent(this, ScanActivity::class.java)
            startActivity(scanIntent)
        }

        //location settings
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        refreshLocation()
    }

    private fun refreshLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationReq = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 500
            smallestDisplacement = 170f //distance
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCB = object : LocationCallback() {
            override fun onLocationResult(res: LocationResult) {
                res ?: return

                if (res.locations.isNotEmpty()) {
                    userLocation = res.lastLocation
                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission (
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                44
            )
            return
        }

        val locationThread = HandlerThread("locationThread")
        locationThread.start()

        fusedLocationProviderClient.requestLocationUpdates(
            locationReq,
            locationCB,
            locationThread.looper
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCB)
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    fun getLocation(): Location {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return userLocation;
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                44
            )
        }
        return userLocation;
    }
}