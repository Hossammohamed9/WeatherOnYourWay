package com.example.weatherontheway.ui.map


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherontheway.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapActivityViewModel: MapActivityViewModel
    private lateinit var latLng: LatLng
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        type=intent.getStringExtra("type").toString()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mapActivityViewModel= ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(
                application
            )
        )[MapActivityViewModel::class.java]


        mapActivityViewModel.saveLatLng.observe(this, {
            if (it) {
                mapActivityViewModel.saveLocationSetting(latLng)
                mapActivityViewModel.saveLatLng.value = false

            }
        })

        mapActivityViewModel.saveFav.observe(this, { it ->
            if (it) {
                mapActivityViewModel.getSettnig().observe(this, {
                    mapActivityViewModel.saveFav(
                        latLng.latitude.toString(),
                        latLng.longitude.toString(),
                        it.lang,
                        it.units
                    )
                })
                mapActivityViewModel.saveFav.value = false

            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mMap.uiSettings.isZoomControlsEnabled = true

        mMap.setOnMapClickListener {
            mMap.clear()

            // Creating MarkerOptions
            val options1 = MarkerOptions()
            options1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            mMap.addMarker(options1.position(it))
            Log.d("TAG", "${it.latitude}.....${it.longitude}")
            latLng=it
            if (type=="setting"){
                mapActivityViewModel.showLocationSavingAlarm(this)
            }else {
                mapActivityViewModel.showAlarm(this)
            }
            Log.d("TAG", "${latLng.latitude}.....${type}")
            Log.d("TAG type", "")
        }

    }

}