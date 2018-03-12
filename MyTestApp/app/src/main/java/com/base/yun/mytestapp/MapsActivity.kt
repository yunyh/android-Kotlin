package com.base.yun.mytestapp

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.base.yun.mytestapp.model.MapsDetailViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    private val mLocationRequest by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        onCreateLocationRequest()
    }

    private val mLocationCallback by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
//                super.onLocationResult(locationResult)
                locationResult?.let { updateMapMarker(it) }
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        startLocationUpdate()
        // Add a marker in Sydney and move the camera
        /* val sydney = LatLng(-34.0, 151.0)
         mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
         mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        val builder = LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest)
        val locationSettingRequest = builder.build()

        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this@MapsActivity)
        settingsClient.checkLocationSettings(locationSettingRequest)
        LocationServices.getFusedLocationProviderClient(this@MapsActivity).requestLocationUpdates(mLocationRequest,
                mLocationCallback, Looper.getMainLooper())

    }

    private fun updateMapMarker(locationResult: LocationResult) {
        mMap.let {
            with(locationResult.lastLocation) {
                /* val sydney = LatLng(-34.0, 151.0)
                mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
                val myLocation = LatLng(latitude, longitude)
                mMap.addMarker(MarkerOptions().position(myLocation).title("Mark Current Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
            }
        }
        val detailFragment = supportFragmentManager.findFragmentById(R.id.map_detail)
        val viewModel = ViewModelProviders.of(detailFragment).get(MapsDetailViewModel::class.java)
        viewModel

    }

    private fun onCreateLocationRequest(): LocationRequest {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationRequest.interval = 10 * 1000
        locationRequest.fastestInterval = 2000
        return locationRequest
    }
}
